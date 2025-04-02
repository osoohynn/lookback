package com.example.kotlinjwt.domain.ai.dto.response

data class AnalysisResponse(
    val translatedText: String,
    val summary: String,
    val emotions: List<EmotionResponse>
)