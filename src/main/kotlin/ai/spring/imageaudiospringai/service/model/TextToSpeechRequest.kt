package ai.spring.imageaudiospringai.service.model

import com.fasterxml.jackson.annotation.JsonClassDescription

@JsonClassDescription("Requested text to be read")
data class TextToSpeechRequest(
        val text : String,
)
