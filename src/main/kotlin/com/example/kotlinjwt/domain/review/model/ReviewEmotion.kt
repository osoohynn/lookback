package com.example.kotlinjwt.domain.review.model

import com.example.kotlinjwt.domain.review.model.enums.Emotion
import jakarta.persistence.*

@Entity
@Table(name = "review_emotion")
class ReviewEmotion (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    val review: Review,

    @Enumerated(EnumType.STRING)
    val emotion: Emotion,
)