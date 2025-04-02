package com.example.kotlinjwt.domain.review.repository

import com.example.kotlinjwt.domain.review.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long> {
}