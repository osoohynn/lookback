package com.example.kotlinjwt.domain.review.dto.request

import com.example.kotlinjwt.domain.review.model.enums.Emotion

data class ChooseEmotionRequest (
    val emotions : List<Emotion>,
)