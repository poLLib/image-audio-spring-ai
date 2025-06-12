package ai.spring.imageaudiospringai.service

import ai.spring.imageaudiospringai.service.model.TextToSpeechRequest
import org.springframework.ai.openai.OpenAiAudioSpeechModel
import org.springframework.ai.openai.OpenAiAudioSpeechOptions
import org.springframework.ai.openai.api.OpenAiAudioApi
import org.springframework.ai.openai.audio.speech.SpeechPrompt
import org.springframework.stereotype.Service

@Service
class TextToSpeechService(
        private val speechModel : OpenAiAudioSpeechModel,
) {

    fun speak(
            request : TextToSpeechRequest,
    ) : ByteArray = OpenAiAudioSpeechOptions
            .Builder()
            .voice(OpenAiAudioApi.SpeechRequest.Voice.SAGE)
            .speed(1.0f)
            .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
            .model(OpenAiAudioApi.TtsModel.TTS_1.value)
            .build()
            .let { options ->
                speechModel.call(
                        SpeechPrompt(
                                request.text,
                                options
                        )
                ).result.output
            }
}
