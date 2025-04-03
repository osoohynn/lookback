package com.example.kotlinjwt.domain.ai.service

import com.example.kotlinjwt.domain.ai.dto.request.MessageAnalyzeRequest
import com.example.kotlinjwt.domain.ai.dto.response.AnalysisResponse
import com.example.kotlinjwt.domain.ai.error.AiError
import com.example.kotlinjwt.global.exception.CustomException
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class AiService (
    private val webClient: WebClient
) {
    fun analyzeMessage(message: MessageAnalyzeRequest): AnalysisResponse {
        val response = webClient.post()
            .uri("/analyze")
            .bodyValue(message)
            .retrieve()
            .onStatus({ status -> status.isError }, { response ->
                response.bodyToMono(String::class.java)
                    .flatMap { errorBody ->
                        Mono.error(CustomException(AiError.AI_SERVER_ERROR, errorBody))
                    }
            })
            .bodyToMono(AnalysisResponse::class.java)
            .block() ?: throw CustomException(AiError.AI_SERVER_NO_RESPONSE)

        return response
    }
}