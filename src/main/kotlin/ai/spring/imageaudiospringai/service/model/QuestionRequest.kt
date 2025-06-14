package ai.spring.imageaudiospringai.service.model

import com.fasterxml.jackson.annotation.JsonClassDescription

@JsonClassDescription("Request text as a user question")
data class QuestionRequest(
        val question : String,
)
