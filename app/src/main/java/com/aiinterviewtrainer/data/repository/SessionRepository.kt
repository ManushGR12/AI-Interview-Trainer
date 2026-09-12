package com.aiinterviewtrainer.data.repository

import com.aiinterviewtrainer.data.model.Difficulty
import com.aiinterviewtrainer.data.model.InterviewCategory
import com.aiinterviewtrainer.data.model.QuestionAttempt
import com.aiinterviewtrainer.data.model.Session
import com.aiinterviewtrainer.data.model.SessionType
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val sessionsCollection = firestore.collection("sessions")

    suspend fun saveSession(session: Session): Result<String> {
        return try {
            val docRef = if (session.sessionId.isBlank()) {
                sessionsCollection.document()
            } else {
                sessionsCollection.document(session.sessionId)
            }

            val sessionWithId = session.copy(sessionId = docRef.id)
            docRef.set(sessionWithId.toMap()).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSessionsForUser(userId: String): Result<List<Session>> {
        return try {
            val snapshot = sessionsCollection
                .whereEqualTo("userId", userId)
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(50)
                .get()
                .await()

            val sessions = snapshot.documents.mapNotNull { it.toSession() }
            Result.success(sessions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSessionById(sessionId: String): Result<Session> {
        return try {
            val snapshot = sessionsCollection.document(sessionId).get().await()
            val session = snapshot.toSession()
            if (session != null) {
                Result.success(session)
            } else {
                Result.failure(Exception("Session not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun Session.toMap(): Map<String, Any> {
        return mapOf(
            "sessionId" to sessionId,
            "userId" to userId,
            "role" to role,
            "type" to type.name,
            "difficulty" to difficulty.name,
            "interviewCategory" to interviewCategory.name,
            "questionCount" to questionCount,
            "timePerQuestionSec" to timePerQuestionSec,
            "overallScore" to overallScore,
            "questionScores" to questionScores,
            "feedbackSummary" to feedbackSummary,
            "durationSeconds" to durationSeconds,
            "questionDetails" to questionDetails.map { attempt ->
                mapOf(
                    "questionId" to attempt.questionId,
                    "questionText" to attempt.questionText,
                    "category" to attempt.category,
                    "difficulty" to attempt.difficulty,
                    "answerText" to attempt.answerText,
                    "score" to attempt.score,
                    "feedback" to attempt.feedback
                )
            },
            "date" to date
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun DocumentSnapshot.toSession(): Session? {
        return try {
            val questionDetailsRaw = get("questionDetails") as? List<Map<String, Any>> ?: emptyList()

            val questionDetails = questionDetailsRaw.map { item ->
                QuestionAttempt(
                    questionId = (item["questionId"] as? Long)?.toInt() ?: 0,
                    questionText = item["questionText"] as? String ?: "",
                    category = item["category"] as? String ?: "",
                    difficulty = item["difficulty"] as? String ?: "",
                    answerText = item["answerText"] as? String ?: "",
                    score = (item["score"] as? Long)?.toInt() ?: 0,
                    feedback = item["feedback"] as? String ?: ""
                )
            }

            Session(
                sessionId = getString("sessionId") ?: id,
                userId = getString("userId") ?: "",
                role = getString("role") ?: "",
                type = SessionType.valueOf(getString("type") ?: "PRACTICE"),
                difficulty = Difficulty.valueOf(getString("difficulty") ?: "MEDIUM"),
                interviewCategory = InterviewCategory.valueOf(
                    getString("interviewCategory") ?: "MIXED"
                ),
                questionCount = (getLong("questionCount") ?: 10L).toInt(),
                timePerQuestionSec = (getLong("timePerQuestionSec") ?: 90L).toInt(),
                overallScore = (getLong("overallScore") ?: 0L).toInt(),
                questionScores = (get("questionScores") as? List<Long>)?.map { it.toInt() } ?: emptyList(),
                feedbackSummary = getString("feedbackSummary") ?: "",
                durationSeconds = getLong("durationSeconds") ?: 0L,
                questionDetails = questionDetails,
                date = getLong("date") ?: 0L
            )
        } catch (e: Exception) {
            null
        }
    }
}