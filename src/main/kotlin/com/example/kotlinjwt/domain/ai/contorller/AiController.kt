package com.example.kotlinjwt.domain.ai.contorller

import com.example.kotlinjwt.domain.ai.dto.request.MessageAnalyzeRequest
import com.example.kotlinjwt.domain.ai.service.AiService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ai")
class AiController (
    private val aiService: AiService
) {
    @PostMapping("/emotion")
    fun analyze(@RequestBody messageAnalyzeRequest: MessageAnalyzeRequest)
    = aiService.analyzeMessage(messageAnalyzeRequest)
}