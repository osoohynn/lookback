package com.example.kotlinjwt.domain.ai.error

import com.example.kotlinjwt.global.exception.CustomError

enum class AiError(override val status: Int, override val message: String) : CustomError {
    AI_SERVER_NO_RESPONSE(500, "AI 서버에서 응답이 없습니다."),
    AI_SERVER_ERROR(500, "AI 서버에서 에러 발생, %s")
}