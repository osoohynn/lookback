package com.example.kotlinjwt.domain.review.model

import com.example.kotlinjwt.domain.review.model.enums.Emotion
import com.example.kotlinjwt.domain.user.domain.entity.User
import com.example.kotlinjwt.global.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "reviews")
class Review (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var happen: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
) : BaseEntity()