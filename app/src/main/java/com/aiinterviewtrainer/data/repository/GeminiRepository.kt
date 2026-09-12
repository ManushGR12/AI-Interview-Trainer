package com.aiinterviewtrainer.data.repository

import android.graphics.Bitmap
import android.util.Log
import com.aiinterviewtrainer.BuildConfig
import com.aiinterviewtrainer.data.model.Difficulty
import com.aiinterviewtrainer.data.model.FeedbackResult
import com.aiinterviewtrainer.data.model.InterviewCategory
import com.aiinterviewtrainer.data.model.Question
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

data class ResumeAnalysis(
    val skills: List<String> = emptyList(),
    val experienceLevel: String = "fresher",
    val recommendedRoles: List<String> = listOf(
        "Software Developer",
        "Data Analyst",
        "Web Developer",
        "Android Developer",
        "QA Engineer"
    ),
    val education: String = "",
    val strengths: List<String> = emptyList(),
    val summary: String = ""
)

@Singleton
class GeminiRepository @Inject constructor() {

    private val textModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY,
        generationConfig = generationConfig {
            temperature = 0.4f
            maxOutputTokens = 2048
        }
    )

    private val visionModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY,
        generationConfig = generationConfig {
            temperature = 0.2f
            maxOutputTokens = 2048
        }
    )

    suspend fun analyzeResume(resumeBitmap: Bitmap?): Result<ResumeAnalysis> {
        return try {
            if (resumeBitmap != null) {
                val inputContent = content {
                    image(resumeBitmap)
                    text(
                        """
                        You are an expert resume analyst.

                        Analyze this resume image and extract:
                        1. Candidate skills
                        2. Experience level (fresher/junior/mid/senior)
                        3. Top 5 recommended job roles
                        4. Education details
                        5. Key strengths
                        6. A short 2-line summary

                        Return STRICT JSON only.
                        No markdown. No code fences. No explanation outside JSON.

                        JSON format:
                        {
                          "skills": ["skill1", "skill2"],
                          "experienceLevel": "fresher",
                          "recommendedRoles": ["Role1", "Role2", "Role3", "Role4", "Role5"],
                          "education": "B.Tech Computer Science",
                          "strengths": ["strength1", "strength2"],
                          "summary": "Brief 2-line summary"
                        }
                        """.trimIndent()
                    )
                }

                val response = visionModel.generateContent(inputContent)
                val raw = response.text?.trim().orEmpty()

                Log.d("GeminiResume", "Raw response: $raw")

                if (raw.isBlank()) {
                    return Result.failure(Exception("Empty AI response"))
                }

                if (raw.contains("\"error\"", ignoreCase = true)) {
                    return Result.failure(Exception("AI error"))
                }

                Result.success(parseResumeAnalysis(raw))
            } else {
                Result.success(
                    ResumeAnalysis(
                        recommendedRoles = listOf(
                            "Software Developer",
                            "Data Analyst",
                            "Web Developer",
                            "Android Developer",
                            "QA Engineer"
                        ),
                        skills = listOf("Java", "Python", "SQL"),
                        experienceLevel = "fresher",
                        strengths = listOf("Problem Solving", "Communication"),
                        summary = "Resume analysis unavailable. Showing default suggestions."
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun parseResumeAnalysis(json: String): ResumeAnalysis {
        return try {
            val clean = json
                .replace("```json", "")
                .replace("```", "")
                .trim()

            val obj = JSONObject(clean)

            ResumeAnalysis(
                skills = if (obj.has("skills")) {
                    obj.getJSONArray("skills").let { arr ->
                        (0 until arr.length()).map { arr.optString(it) }.filter { it.isNotBlank() }
                    }
                } else emptyList(),
                experienceLevel = obj.optString("experienceLevel", "fresher"),
                recommendedRoles = if (obj.has("recommendedRoles")) {
                    obj.getJSONArray("recommendedRoles").let { arr ->
                        (0 until arr.length()).map { arr.optString(it) }.filter { it.isNotBlank() }
                    }
                } else emptyList(),
                education = obj.optString("education", ""),
                strengths = if (obj.has("strengths")) {
                    obj.getJSONArray("strengths").let { arr ->
                        (0 until arr.length()).map { arr.optString(it) }.filter { it.isNotBlank() }
                    }
                } else emptyList(),
                summary = obj.optString("summary", "")
            )
        } catch (e: Exception) {
            ResumeAnalysis()
        }
    }

    suspend fun generateQuestions(
        role: String,
        difficulty: Difficulty,
        count: Int,
        interviewCategory: InterviewCategory = InterviewCategory.MIXED,
        skills: List<String> = emptyList(),
        previousQuestions: List<String> = emptyList()
    ): Result<List<Question>> {
        return try {
            val skillsText = if (skills.isNotEmpty()) {
                "Candidate skills: ${skills.joinToString()}."
            } else {
                "Candidate skills are not provided."
            }

            val previousText = if (previousQuestions.isNotEmpty()) {
                "Do NOT repeat or rephrase any of these questions: ${previousQuestions.joinToString(" | ")}."
            } else {
                "Ensure all generated questions are unique and not repetitive."
            }

            val prompt = """
You are a professional technical interviewer.

Generate exactly $count interview questions for the role: $role.

Difficulty level: ${difficulty.name}
Interview category: ${interviewCategory.name}

Difficulty rules:
- EASY → Basic definitions, fundamentals, beginner-level interview questions.
- MEDIUM → Practical scenarios, problem solving, real-world project questions.
- HARD → System design, architecture, optimization, debugging, trade-offs, advanced concepts.

Category rules:
- TECHNICAL → Only technical questions.
- BEHAVIORAL → Only HR/behavioral questions.
- MIXED → Combination of technical and behavioral questions.

$skillsText
$previousText

Important rules:
1. All questions must be UNIQUE.
2. HARD questions must be clearly harder than EASY questions.
3. Use the role "$role" in the questions.
4. Questions must sound like real interview questions.
5. Include 3–4 key points expected in the answer.
6. Return STRICT JSON only.
7. No markdown. No code fences.

JSON format:
{
  "questions": [
    {
      "id": 1,
      "text": "Question text here",
      "category": "Technical",
      "difficulty": "${difficulty.name}",
      "expectedPoints": ["point1", "point2", "point3"]
    }
  ]
}
""".trimIndent()

            val response = textModel.generateContent(prompt)
            val raw = response.text?.trim().orEmpty()

            Log.d("GeminiQuestions", "Raw response: $raw")

            if (raw.isBlank()) {
                return Result.success(
                    getFallbackQuestions(
                        role = role,
                        count = count,
                        difficulty = difficulty,
                        interviewCategory = interviewCategory
                    )
                )
            }

            val parsed = parseQuestions(raw)

            if (parsed.isNotEmpty()) {
                Result.success(parsed.take(count))
            } else {
                Result.success(
                    getFallbackQuestions(
                        role = role,
                        count = count,
                        difficulty = difficulty,
                        interviewCategory = interviewCategory
                    )
                )
            }
        } catch (e: Exception) {
            Result.success(
                getFallbackQuestions(
                    role = role,
                    count = count,
                    difficulty = difficulty,
                    interviewCategory = interviewCategory
                )
            )
        }
    }

    private fun parseQuestions(json: String): List<Question> {
        return try {
            val clean = json
                .replace("```json", "")
                .replace("```", "")
                .trim()

            val obj = JSONObject(clean)
            val arr = obj.getJSONArray("questions")

            (0 until arr.length()).map { i ->
                val q = arr.getJSONObject(i)
                val pts = if (q.has("expectedPoints")) {
                    q.getJSONArray("expectedPoints").let { points ->
                        (0 until points.length()).map { points.optString(it) }.filter { it.isNotBlank() }
                    }
                } else {
                    emptyList()
                }

                Question(
                    id = q.optInt("id", i + 1),
                    text = q.optString("text", ""),
                    category = q.optString("category", "General"),
                    difficulty = q.optString("difficulty", "Medium"),
                    expectedPoints = pts
                )
            }.filter { it.text.isNotBlank() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun evaluateAnswer(
        question: Question,
        transcribedAnswer: String,
        role: String
    ): Result<FeedbackResult> {
        return try {
            val safeAnswer = transcribedAnswer.trim().ifEmpty { "(No answer provided)" }

            val prompt = """
You are an expert interviewer for the role: $role.

Evaluate the candidate answer strictly and realistically.

Question: ${question.text}
Category: ${question.category}
Difficulty: ${question.difficulty}
Expected points: ${question.expectedPoints.joinToString()}

Candidate answer:
"$safeAnswer"

Scoring rules:
- 0 to 20 = irrelevant / nonsense / no answer
- 21 to 40 = very weak / mostly incorrect
- 41 to 60 = partially correct but incomplete
- 61 to 80 = mostly correct and relevant
- 81 to 100 = strong, clear, complete, and well-structured

Important:
- Do NOT always give 50.
- If the answer is unrelated, score below 30.
- If the answer is decent but incomplete, score 40–65.
- If the answer is strong and relevant, score above 70.
- Return STRICT JSON only.
- No markdown. No code fences. No explanation outside JSON.

Return this exact structure:
{
  "score": 0,
  "strengths": ["point 1", "point 2"],
  "improvements": ["point 1", "point 2"],
  "modelAnswer": "Ideal answer here",
  "detailedFeedback": "2 to 3 sentence feedback here"
}
""".trimIndent()

            val response = textModel.generateContent(prompt)
            val raw = response.text?.trim().orEmpty()

            Log.d("GeminiEval", "Raw response: $raw")

            if (raw.isBlank()) {
                Result.success(buildFallbackEvaluation(question, safeAnswer))
            } else {
                Result.success(parseFeedback(raw))
            }
        } catch (e: Exception) {
            Log.e("GeminiEval", "Evaluation failed", e)
            Result.success(buildFallbackEvaluation(question, transcribedAnswer))
        }
    }

    private fun parseFeedback(json: String): FeedbackResult {
        return try {
            val clean = json
                .replace("```json", "")
                .replace("```", "")
                .trim()

            val obj = JSONObject(clean)

            val strengths = mutableListOf<String>()
            if (obj.has("strengths")) {
                val arr = obj.getJSONArray("strengths")
                for (i in 0 until arr.length()) {
                    val item = arr.optString(i)
                    if (item.isNotBlank()) strengths.add(item)
                }
            }

            val improvements = mutableListOf<String>()
            if (obj.has("improvements")) {
                val arr = obj.getJSONArray("improvements")
                for (i in 0 until arr.length()) {
                    val item = arr.optString(i)
                    if (item.isNotBlank()) improvements.add(item)
                }
            }

            val parsedScore = if (obj.has("score")) obj.optInt("score", -1) else -1
            val safeScore = if (parsedScore in 0..100) parsedScore else 50

            FeedbackResult(
                score = safeScore,
                strengths = strengths,
                improvements = improvements,
                modelAnswer = obj.optString("modelAnswer", ""),
                feedback = obj.optString(
                    "detailedFeedback",
                    obj.optString("feedback", "Answer evaluated with limited analysis.")
                )
            )
        } catch (e: Exception) {
            buildParseFallback(json)
        }
    }

    private fun buildFallbackEvaluation(
        question: Question,
        answer: String
    ): FeedbackResult {
        val safeAnswer = answer.trim()

        if (safeAnswer.isBlank() || safeAnswer.equals("(No answer provided)", ignoreCase = true)) {
            return FeedbackResult(
                score = 10,
                strengths = emptyList(),
                improvements = listOf("Provide an answer relevant to the question."),
                modelAnswer = buildModelAnswerFromQuestion(question),
                feedback = "No meaningful answer was provided."
            )
        }

        val answerLower = safeAnswer.lowercase()

        val expectedKeywords = question.expectedPoints
            .flatMap { it.lowercase().split(Regex("[^a-z0-9]+")) }
            .filter { it.length > 2 }
            .distinct()

        val questionKeywords = question.text
            .lowercase()
            .split(Regex("[^a-z0-9]+"))
            .filter { it.length > 3 }
            .distinct()

        val matchedExpected = expectedKeywords.count { answerLower.contains(it) }
        val matchedQuestion = questionKeywords.count { answerLower.contains(it) }

        val lengthScore = when {
            safeAnswer.length < 10 -> 8
            safeAnswer.length < 25 -> 18
            safeAnswer.length < 50 -> 30
            safeAnswer.length < 100 -> 40
            else -> 48
        }

        val expectedScore = if (expectedKeywords.isNotEmpty()) {
            ((matchedExpected.toFloat() / expectedKeywords.size) * 35).toInt()
        } else {
            18
        }

        val relevanceScore = if (questionKeywords.isNotEmpty()) {
            ((matchedQuestion.toFloat() / questionKeywords.size.coerceAtLeast(1)) * 17).toInt()
        } else {
            10
        }

        val finalScore = (lengthScore + expectedScore + relevanceScore).coerceIn(0, 95)

        val strengths = mutableListOf<String>()
        val improvements = mutableListOf<String>()

        if (safeAnswer.length >= 25) strengths.add("You attempted a descriptive answer.")
        if (matchedExpected > 0) strengths.add("Your answer includes some relevant concepts.")
        if (matchedQuestion > 0) strengths.add("Your answer is related to the question topic.")

        if (safeAnswer.length < 25) improvements.add("Add more explanation and detail.")
        if (matchedExpected == 0) improvements.add("Include more key concepts expected in the answer.")
        if (finalScore < 60) improvements.add("Make the answer clearer and more structured.")

        if (strengths.isEmpty()) strengths.add("You attempted the question.")
        if (improvements.isEmpty()) improvements.add("Add one practical example for a stronger answer.")

        return FeedbackResult(
            score = finalScore,
            strengths = strengths,
            improvements = improvements,
            modelAnswer = buildModelAnswerFromQuestion(question),
            feedback = when {
                finalScore >= 75 -> "Your answer appears relevant and reasonably complete."
                finalScore >= 50 -> "Your answer is partially correct but needs more depth."
                else -> "Your answer needs improvement in relevance and explanation."
            }
        )
    }

    private fun buildParseFallback(raw: String): FeedbackResult {
        val extractedScore = Regex("\"score\"\\s*:\\s*(\\d+)")
            .find(raw)
            ?.groupValues
            ?.getOrNull(1)
            ?.toIntOrNull()
            ?.coerceIn(0, 100)

        val fallbackScore = extractedScore ?: 50

        return FeedbackResult(
            score = fallbackScore,
            strengths = emptyList(),
            improvements = listOf("AI response could not be fully parsed. Try answering with more detail."),
            modelAnswer = "",
            feedback = "Answer evaluated with limited analysis."
        )
    }

    private fun buildModelAnswerFromQuestion(question: Question): String {
        return if (question.expectedPoints.isNotEmpty()) {
            "A strong answer should include: ${question.expectedPoints.joinToString(", ")}."
        } else {
            "A strong answer should stay relevant to the question, explain the concept clearly, and include one practical example."
        }
    }

    suspend fun generateSessionSummary(
        role: String,
        scores: List<Int>,
        overallScore: Int
    ): Result<String> {
        return try {
            val prompt = """
Generate a brief, encouraging performance summary for a candidate who completed
a $role interview practice session.

Overall score: $overallScore%.
Individual scores: ${scores.joinToString()}.

Write 3 to 4 sentences:
- acknowledge performance
- highlight key improvement area
- motivate the candidate

No markdown. No bullet points. Plain text only.
""".trimIndent()

            val response = textModel.generateContent(prompt)
            Result.success(response.text?.trim() ?: "Good effort! Keep practicing to improve.")
        } catch (e: Exception) {
            Result.success("Session completed with $overallScore%. Keep practicing!")
        }
    }

    private fun getFallbackQuestions(
        role: String,
        count: Int,
        difficulty: Difficulty,
        interviewCategory: InterviewCategory
    ): List<Question> {
        val technicalEasy = listOf(
            Question(1, "What does a $role typically work on in a project?", "Technical", "Easy"),
            Question(2, "Which basic tools, languages, or frameworks are important for a $role?", "Technical", "Easy"),
            Question(3, "Explain one core concept related to $role in simple terms.", "Technical", "Easy"),
            Question(4, "How would you start learning practical skills needed for a $role?", "Technical", "Easy"),
            Question(5, "What is the difference between theory and implementation in a $role task?", "Technical", "Easy")
        )

        val technicalMedium = listOf(
            Question(1, "How would you approach solving a practical problem as a $role?", "Technical", "Medium"),
            Question(2, "Describe how you would structure a real-world workflow for a common $role task.", "Technical", "Medium"),
            Question(3, "How do you debug issues and verify correctness in a $role project?", "Technical", "Medium"),
            Question(4, "What trade-offs would you consider while choosing tools or approaches for a $role project?", "Technical", "Medium"),
            Question(5, "How would you improve the performance or maintainability of a system related to $role?", "Technical", "Medium")
        )

        val technicalHard = listOf(
            Question(1, "How would you design a scalable and maintainable solution for a complex $role use case?", "Technical", "Hard"),
            Question(2, "Explain the trade-offs you would evaluate when optimizing architecture in a $role project.", "Technical", "Hard"),
            Question(3, "How would you handle edge cases, failures, and long-term maintainability in an advanced $role system?", "Technical", "Hard"),
            Question(4, "Describe how you would diagnose and fix a difficult production issue in a $role environment.", "Technical", "Hard"),
            Question(5, "How would you balance scalability, security, performance, and developer productivity in a senior-level $role task?", "Technical", "Hard")
        )

        val behavioralEasy = listOf(
            Question(1, "Tell me about yourself.", "Behavioral", "Easy"),
            Question(2, "Why are you interested in the role of $role?", "Behavioral", "Easy"),
            Question(3, "How do you usually learn a new skill or technology?", "Behavioral", "Easy"),
            Question(4, "What are your strengths as a learner or team member?", "Behavioral", "Easy"),
            Question(5, "How do you prepare yourself before starting a new project?", "Behavioral", "Easy")
        )

        val behavioralMedium = listOf(
            Question(1, "Describe a time you worked with others to complete a project successfully.", "Behavioral", "Medium"),
            Question(2, "Tell me about a challenge you faced and how you handled it.", "Behavioral", "Medium"),
            Question(3, "How do you prioritize your work when you have multiple deadlines?", "Behavioral", "Medium"),
            Question(4, "Describe a situation where you had to quickly learn something important to finish a task.", "Behavioral", "Medium"),
            Question(5, "Tell me about a time you received feedback and how you responded to it.", "Behavioral", "Medium")
        )

        val behavioralHard = listOf(
            Question(1, "Describe a conflict within a team and how you resolved it constructively.", "Behavioral", "Hard"),
            Question(2, "Tell me about a time you made a difficult decision under pressure.", "Behavioral", "Hard"),
            Question(3, "Describe a situation where you had to influence others without formal authority.", "Behavioral", "Hard"),
            Question(4, "Tell me about a time a project did not go as planned and what you did next.", "Behavioral", "Hard"),
            Question(5, "Describe a time you took ownership in an ambiguous or high-pressure situation.", "Behavioral", "Hard")
        )

        val base = when (interviewCategory) {
            InterviewCategory.TECHNICAL -> when (difficulty) {
                Difficulty.EASY -> technicalEasy
                Difficulty.MEDIUM -> technicalMedium
                Difficulty.HARD -> technicalHard
            }
            InterviewCategory.BEHAVIORAL -> when (difficulty) {
                Difficulty.EASY -> behavioralEasy
                Difficulty.MEDIUM -> behavioralMedium
                Difficulty.HARD -> behavioralHard
            }
            InterviewCategory.MIXED -> when (difficulty) {
                Difficulty.EASY -> technicalEasy.take(3) + behavioralEasy.take(2)
                Difficulty.MEDIUM -> technicalMedium.take(3) + behavioralMedium.take(2)
                Difficulty.HARD -> technicalHard.take(3) + behavioralHard.take(2)
            }
        }

        return if (base.size >= count) {
            base.take(count).mapIndexed { index, question ->
                question.copy(id = index + 1)
            }   
        } else {
            generateSequence { base }
                .flatten()
                .take(count)
                .mapIndexed { index, question ->
                    question.copy(id = index + 1)
                }
                .toList()
        }
    }
}