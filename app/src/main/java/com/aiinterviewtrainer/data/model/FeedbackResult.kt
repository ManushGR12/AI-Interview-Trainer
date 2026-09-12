package com.aiinterviewtrainer.data.model

data class FeedbackResult(
    val score: Int = 0,
    val strengths: List<String> = emptyList(),
    val improvements: List<String> = emptyList(),
    val modelAnswer: String = "",
    val feedback: String = ""
)