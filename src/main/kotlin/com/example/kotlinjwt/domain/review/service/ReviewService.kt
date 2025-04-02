package com.example.kotlinjwt.domain.review.service

import com.example.kotlinjwt.domain.ai.service.AiService
import com.example.kotlinjwt.domain.review.dto.request.CreateReviewRequest
import com.example.kotlinjwt.domain.review.model.Review
import com.example.kotlinjwt.domain.review.repository.ReviewRepository
import com.example.kotlinjwt.global.security.SecurityHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ReviewService (
    private val aiService: AiService,
    private val securityHolder: SecurityHolder,
    private val reviewRepository: ReviewRepository
) {
    fun chooseEmotion() {

    }

    fun createReview(req: CreateReviewRequest) {
        val user = securityHolder.user

        val review = Review(
            user = user,
            happen = null,
        )

        reviewRepository.save(review)
    }
}