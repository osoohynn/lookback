package com.example.kotlinjwt.domain.review.error

import com.example.kotlinjwt.global.exception.CustomError

enum class ReviewError(override val status: Int, override val message: String) : CustomError {
    REVIEW_NOT_FOUND(404, "회고를 찾을 수 없습니다."),
}