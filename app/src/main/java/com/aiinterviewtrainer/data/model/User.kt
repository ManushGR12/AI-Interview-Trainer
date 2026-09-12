package com.aiinterviewtrainer.data.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val college: String = "",
    val graduationYear: String = "",
    val phone: String = "",
    val selectedRoles: List<String> = emptyList(),
    val extractedSkills: List<String> = emptyList(),
    val profilePhotoPath: String = "",   // local path only
    val resumePath: String = "",          // local path only
    val resumeText: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
