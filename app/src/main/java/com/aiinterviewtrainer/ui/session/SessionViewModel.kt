package com.aiinterviewtrainer.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiinterviewtrainer.data.model.Difficulty
import com.aiinterviewtrainer.data.model.FeedbackResult
import com.aiinterviewtrainer.data.model.InterviewCategory
import com.aiinterviewtrainer.data.model.Question
import com.aiinterviewtrainer.data.model.QuestionAttempt
import com.aiinterviewtrainer.data.model.Session
import com.aiinterviewtrainer.data.model.SessionType
import com.aiinterviewtrainer.data.repository.AuthRepository
import com.aiinterviewtrainer.data.repository.GeminiRepository
import com.aiinterviewtrainer.data.repository.SessionRepository
import com.aiinterviewtrainer.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SessionConfig(
    val role: String = "",
    val type: SessionType = SessionType.PRACTICE,
    val difficulty: Difficulty = Difficulty.MEDIUM,
    val interviewCategory: InterviewCategory = InterviewCategory.MIXED,
    val questionCount: Int = 10,
    val timePerQuestionSec: Int = 90
)

data class SessionUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val isLoading: Boolean = false,
    val faceWarning: String? = null,
    val sessionComplete: Boolean = false,
    val availableRoles: List<String> = emptyList(),
    val userSkills: List<String> = emptyList(),
    val feedback: FeedbackResult? = null,
    val scores: List<Int> = emptyList(),
    val summaryText: String = "",
    val selectedRole: String = "",
    val questionAttempts: List<QuestionAttempt> = emptyList(),
    val lastSavedSessionId: String? = null
)

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val geminiRepository: GeminiRepository
) : ViewModel() {

    private val askedQuestions = mutableListOf<String>()

    private val _uiState = MutableStateFlow(SessionUiState())
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    private var sessionConfig = SessionConfig()

    private val _speechState = MutableStateFlow(SpeechState())
    val speechState: StateFlow<SpeechState> = _speechState.asStateFlow()

    init {
        loadUserRoles()
    }

    private fun loadUserRoles() {
        viewModelScope.launch {
            val uid = authRepository.currentUser?.uid ?: return@launch
            userRepository.getUser(uid).onSuccess { user ->
                val roles = user.selectedRoles.ifEmpty {
                    listOf("Software Developer", "Data Analyst", "Web Developer", "Android Developer")
                }
                _uiState.value = _uiState.value.copy(
                    availableRoles = roles,
                    selectedRole = roles.firstOrNull() ?: "Software Developer",
                    userSkills = user.extractedSkills
                )
            }
        }
    }

    fun updateConfig(config: SessionConfig) {
        sessionConfig = config
        askedQuestions.clear()
        _uiState.value = _uiState.value.copy(
            selectedRole = config.role,
            questions = emptyList(),
            currentQuestionIndex = 0,
            feedback = null,
            faceWarning = null,
            sessionComplete = false,
            scores = emptyList(),
            questionAttempts = emptyList(),
            summaryText = "",
            lastSavedSessionId = null
        )
    }

    fun generateQuestions() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                questions = emptyList(),
                currentQuestionIndex = 0,
                scores = emptyList(),
                questionAttempts = emptyList(),
                sessionComplete = false,
                feedback = null,
                summaryText = "",
                lastSavedSessionId = null
            )

            val role = sessionConfig.role.ifEmpty { _uiState.value.selectedRole }

            geminiRepository.generateQuestions(
                role = role,
                difficulty = sessionConfig.difficulty,
                count = sessionConfig.questionCount,
                interviewCategory = sessionConfig.interviewCategory,
                skills = _uiState.value.userSkills,
                previousQuestions = askedQuestions
            ).onSuccess { questions ->
                askedQuestions.addAll(questions.map { it.text })

                _uiState.value = _uiState.value.copy(
                    questions = questions,
                    isLoading = false
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    questions = getFallbackQuestions(
                        role = role,
                        count = sessionConfig.questionCount,
                        difficulty = sessionConfig.difficulty,
                        category = sessionConfig.interviewCategory
                    ),
                    isLoading = false
                )
            }
        }
    }

    fun submitAnswer(answer: String) {
        viewModelScope.launch {
            val questions = _uiState.value.questions
            val index = _uiState.value.currentQuestionIndex

            if (questions.isEmpty()) return@launch
            val question = questions.getOrNull(index) ?: return@launch

            _uiState.value = _uiState.value.copy(isLoading = true)

            geminiRepository.evaluateAnswer(
                question = question,
                transcribedAnswer = answer,
                role = sessionConfig.role.ifEmpty { _uiState.value.selectedRole }
            ).onSuccess { result ->
                val newScores = _uiState.value.scores + result.score
                val newAttempt = QuestionAttempt(
                    questionId = question.id,
                    questionText = question.text,
                    category = question.category,
                    difficulty = question.difficulty,
                    answerText = answer,
                    score = result.score,
                    feedback = result.feedback
                )
                val newAttempts = _uiState.value.questionAttempts + newAttempt
                val isLast = index >= questions.size - 1

                _uiState.value = _uiState.value.copy(
                    feedback = result,
                    scores = newScores,
                    questionAttempts = newAttempts,
                    isLoading = false,
                    sessionComplete = isLast
                )

                if (isLast) generateSummary(newScores)
            }.onFailure {
                val fallbackScore = 50
                val fallbackFeedback = "Could not evaluate. Please try again."
                val newScores = _uiState.value.scores + fallbackScore
                val newAttempt = QuestionAttempt(
                    questionId = question.id,
                    questionText = question.text,
                    category = question.category,
                    difficulty = question.difficulty,
                    answerText = answer,
                    score = fallbackScore,
                    feedback = fallbackFeedback
                )
                val newAttempts = _uiState.value.questionAttempts + newAttempt
                val isLast = index >= questions.size - 1

                _uiState.value = _uiState.value.copy(
                    feedback = FeedbackResult(
                        score = fallbackScore,
                        feedback = fallbackFeedback
                    ),
                    scores = newScores,
                    questionAttempts = newAttempts,
                    isLoading = false,
                    sessionComplete = isLast
                )

                if (isLast) generateSummary(newScores)
            }
        }
    }

    fun nextQuestion() {
        val current = _uiState.value.currentQuestionIndex
        val total = _uiState.value.questions.size
        if (current < total - 1) {
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = current + 1,
                feedback = null,
                faceWarning = null
            )
        }
    }

    fun reportFaceWarning(message: String) {
        _uiState.value = _uiState.value.copy(faceWarning = message)
    }

    fun clearFaceWarning() {
        _uiState.value = _uiState.value.copy(faceWarning = null)
    }

    private fun generateSummary(scores: List<Int>) {
        viewModelScope.launch {
            val avg = if (scores.isNotEmpty()) scores.average().toInt() else 0
            val role = sessionConfig.role.ifEmpty { _uiState.value.selectedRole }

            geminiRepository.generateSessionSummary(role, scores, avg)
                .onSuccess { summary ->
                    _uiState.value = _uiState.value.copy(summaryText = summary)
                    saveSession(avg, scores, summary)
                }
                .onFailure {
                    val fallbackSummary =
                        "Session completed. Review your answers and continue practicing to improve."
                    _uiState.value = _uiState.value.copy(summaryText = fallbackSummary)
                    saveSession(avg, scores, fallbackSummary)
                }
        }
    }

    private suspend fun saveSession(
        avgScore: Int,
        scores: List<Int>,
        summary: String
    ) {
        val uid = authRepository.currentUser?.uid ?: return
        val role = sessionConfig.role.ifEmpty { _uiState.value.selectedRole }

        val session = Session(
            userId = uid,
            role = role,
            type = sessionConfig.type,
            difficulty = sessionConfig.difficulty,
            interviewCategory = sessionConfig.interviewCategory,
            questionCount = sessionConfig.questionCount,
            timePerQuestionSec = sessionConfig.timePerQuestionSec,
            overallScore = avgScore,
            questionScores = scores,
            feedbackSummary = summary,
            questionDetails = _uiState.value.questionAttempts
        )

        sessionRepository.saveSession(session).onSuccess { sessionId ->
            _uiState.value = _uiState.value.copy(lastSavedSessionId = sessionId)
        }
    }

    val overallScore: Int
        get() {
            val s = _uiState.value.scores
            return if (s.isNotEmpty()) s.average().toInt() else 0
        }
    val isMockMode: Boolean
        get() = sessionConfig.type == SessionType.MOCK

    val isLastQuestion: Boolean
        get() {
            val state = _uiState.value
            return state.currentQuestionIndex >= state.questions.size - 1
        }

    private fun getFallbackQuestions(
        role: String,
        count: Int,
        difficulty: Difficulty,
        category: InterviewCategory
    ): List<Question> {
        val technicalEasy = listOf(
            Question(1, "What is your understanding of the role of a $role?", "Technical", "Easy"),
            Question(2, "Which basic tools or technologies are commonly used in $role?", "Technical", "Easy"),
            Question(3, "Explain one concept related to $role in simple terms.", "Technical", "Easy")
        )

        val technicalMedium = listOf(
            Question(1, "How would you solve a real-world problem in a $role project?", "Technical", "Medium"),
            Question(2, "Describe a workflow or architecture you would use for a typical $role task.", "Technical", "Medium"),
            Question(3, "How do you debug and improve performance in your work as a $role?", "Technical", "Medium")
        )

        val technicalHard = listOf(
            Question(1, "How would you design a scalable solution for a complex $role use case?", "Technical", "Hard"),
            Question(2, "Explain trade-offs you would consider when optimizing a system in $role.", "Technical", "Hard"),
            Question(3, "How would you handle edge cases, reliability, and maintainability in an advanced $role project?", "Technical", "Hard")
        )

        val behavioralEasy = listOf(
            Question(1, "Tell me about yourself.", "Behavioral", "Easy"),
            Question(2, "Why are you interested in becoming a $role?", "Behavioral", "Easy"),
            Question(3, "How do you approach learning new skills?", "Behavioral", "Easy")
        )

        val behavioralMedium = listOf(
            Question(1, "Describe a time you worked in a team to complete a project.", "Behavioral", "Medium"),
            Question(2, "Tell me about a challenge you faced and how you handled it.", "Behavioral", "Medium"),
            Question(3, "How do you manage deadlines and multiple tasks?", "Behavioral", "Medium")
        )

        val behavioralHard = listOf(
            Question(1, "Describe a conflict in a team and how you resolved it.", "Behavioral", "Hard"),
            Question(2, "Tell me about a time you made a difficult decision under pressure.", "Behavioral", "Hard"),
            Question(3, "Describe a situation where you had to influence others without authority.", "Behavioral", "Hard")
        )

        val mixed = when (difficulty) {
            Difficulty.EASY -> technicalEasy + behavioralEasy
            Difficulty.MEDIUM -> technicalMedium + behavioralMedium
            Difficulty.HARD -> technicalHard + behavioralHard
        }

        val base = when (category) {
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
            InterviewCategory.MIXED -> mixed
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

data class SpeechState(
    val isListening: Boolean = false,
    val finalText: String = "",
    val partialText: String = ""
)