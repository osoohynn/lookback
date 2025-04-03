package com.example.kotlinjwt.domain.review.repository

import com.example.kotlinjwt.domain.review.model.ReviewEmotion
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewEmotionRepository : JpaRepository<ReviewEmotion, Long> {
}