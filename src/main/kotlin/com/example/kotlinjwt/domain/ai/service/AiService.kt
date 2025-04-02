package com.example.kotlinjwt.domain.ai.service

import com.example.kotlinjwt.domain.ai.dto.request.MessageAnalyzeRequest
import com.example.kotlinjwt.domain.ai.dto.response.AnalysisResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class AiService (
    private val webClient: WebClient
) {
    fun analyzeMessage(message: MessageAnalyzeRequest): Mono<AnalysisResponse> {
        return webClient.post()
            .uri("/analyze")
            .bodyValue(message)
            .retrieve()
            .onStatus({ status -> status.isError }, { response ->
                response.bodyToMono(String::class.java)
                    .flatMap { errorBody ->
                        Mono.error(RuntimeException("AI 서버 오류: $errorBody"))
                    }
            })
            .bodyToMono(AnalysisResponse::class.java)
    }
}