package ai.spring.imageaudiospringai.service

import ai.spring.imageaudiospringai.service.model.AnswerResponse
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions
import org.springframework.ai.openai.api.OpenAiAudioApi
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class SpeechToTextService(
        private val transcriptClient : OpenAiAudioTranscriptionModel
) {
    fun transcript(
            file : MultipartFile,
    ) : AnswerResponse = OpenAiAudioTranscriptionOptions
            .builder()
            .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.VERBOSE_JSON)
            .language("en")
            .temperature(0f)
            .build()
            .let { options ->
                AnswerResponse(
                        transcriptClient
                                .call(
                                        AudioTranscriptionPrompt(
                                                file.resource,
                                                options
                                        )
                                )
                                .result
                                .output)
            }
}
