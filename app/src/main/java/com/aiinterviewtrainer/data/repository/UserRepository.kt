package com.aiinterviewtrainer.data.repository

import com.aiinterviewtrainer.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val usersCollection = firestore.collection("users")

    suspend fun saveUser(user: User): Result<Unit> {
        return try {
            usersCollection.document(user.uid).set(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUser(uid: String): Result<User> {
        return try {
            val doc = usersCollection.document(uid).get().await()
            val user = doc.toObject(User::class.java) ?: User(uid = uid)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUser(uid: String, updates: Map<String, Any>): Result<Unit> {
        return try {
            usersCollection.document(uid).update(updates).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateSelectedRoles(uid: String, roles: List<String>): Result<Unit> {
        return updateUser(uid, mapOf("selectedRoles" to roles))
    }

    suspend fun updateProfileInfo(
        uid: String,
        name: String,
        college: String,
        graduationYear: String,
        degree: String,
        photoPath: String
    ): Result<Unit> {
        return updateUser(
            uid,
            mapOf(
                "name" to name,
                "college" to college,
                "graduationYear" to graduationYear,
                "degree" to degree,
                "profilePhotoPath" to photoPath
            )
        )
    }

    suspend fun updateResumePath(uid: String, resumePath: String): Result<Unit> {
        return updateUser(uid, mapOf("resumePath" to resumePath))
    }

    suspend fun saveResumeAnalysis(uid: String, analysis: ResumeAnalysis): Result<Unit> {
        return updateUser(
            uid,
            mapOf(
                "extractedSkills" to analysis.skills,
                "suggestedRoles" to analysis.recommendedRoles,
                "experienceLevel" to analysis.experienceLevel,
                "summary" to analysis.summary,
                "education" to analysis.education,
                "strengths" to analysis.strengths
            )
        )
    }
}