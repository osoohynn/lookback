package com.example.kotlinjwt.domain.review.service

import com.example.kotlinjwt.domain.ai.dto.request.MessageAnalyzeRequest
import com.example.kotlinjwt.domain.ai.dto.response.AnalysisResponse
import com.example.kotlinjwt.domain.ai.service.AiService
import com.example.kotlinjwt.domain.review.dto.request.ChooseEmotionRequest
import com.example.kotlinjwt.domain.review.dto.request.CreateReviewRequest
import com.example.kotlinjwt.domain.review.dto.response.Top3EmotionResponse
import com.example.kotlinjwt.domain.review.error.ReviewError
import com.example.kotlinjwt.domain.review.model.Review
import com.example.kotlinjwt.domain.review.model.ReviewEmotion
import com.example.kotlinjwt.domain.review.repository.ReviewEmotionRepository
import com.example.kotlinjwt.domain.review.repository.ReviewRepository
import com.example.kotlinjwt.global.exception.CustomException
import com.example.kotlinjwt.global.security.SecurityHolder
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ReviewService (
    private val aiService: AiService,
    private val securityHolder: SecurityHolder,
    private val reviewRepository: ReviewRepository,
    private val reviewEmotionRepository: ReviewEmotionRepository,
) {
    fun chooseEmotion(req: ChooseEmotionRequest, reviewId: Long) {
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw CustomException(ReviewError.REVIEW_NOT_FOUND)

        val emotions = req.emotions
        emotions.forEach { emotion ->
            val reviewEmotion = ReviewEmotion(
                review = review,
                emotion = emotion
            )
            reviewEmotionRepository.save(reviewEmotion)
        }
    }

    fun createReview(req: CreateReviewRequest): List<Top3EmotionResponse> {
        val user = securityHolder.user
        val response = aiService.analyzeMessage(MessageAnalyzeRequest(req.message))

        val review = Review(
            user = user,
            happen = response.happen,
        )
        reviewRepository.save(review)

        val top3 = response.emotions
            .sortedByDescending { it.score }.take(3)
            .map { emotion -> Top3EmotionResponse(emotion.label, String.format("%.2f", emotion.score).toDouble()) }

        return top3
    }
}