package com.aiinterviewtrainer.data.model

data class Session(
    val sessionId: String = "",
    val userId: String = "",
    val role: String = "",
    val type: SessionType = SessionType.PRACTICE,
    val difficulty: Difficulty = Difficulty.MEDIUM,
    val interviewCategory: InterviewCategory = InterviewCategory.MIXED,
    val questionCount: Int = 10,
    val timePerQuestionSec: Int = 90,
    val overallScore: Int = 0,
    val questionScores: List<Int> = emptyList(),
    val feedbackSummary: String = "",
    val durationSeconds: Long = 0L,
    val questionDetails: List<QuestionAttempt> = emptyList(),
    val date: Long = System.currentTimeMillis()
)

data class QuestionAttempt(
    val questionId: Int = 0,
    val questionText: String = "",
    val category: String = "",
    val difficulty: String = "",
    val answerText: String = "",
    val score: Int = 0,
    val feedback: String = ""
)

enum class SessionType { PRACTICE, MOCK }
enum class Difficulty { EASY, MEDIUM, HARD }
enum class InterviewCategory { TECHNICAL, BEHAVIORAL, MIXED }