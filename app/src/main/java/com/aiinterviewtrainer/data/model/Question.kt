package com.aiinterviewtrainer.data.model

data class Question(
    val id: Int = 0,
    val text: String = "",
    val category: String = "",
    val difficulty: String = "",
    val tips: String = "",
    val expectedPoints: List<String> = emptyList()
)