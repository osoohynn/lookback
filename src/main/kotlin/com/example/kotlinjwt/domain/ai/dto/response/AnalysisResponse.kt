package com.example.kotlinjwt.domain.ai.dto.response

data class AnalysisResponse(
    val translatedText: String?,
    val happen: String?,
    val emotions: List<EmotionResponse>
)