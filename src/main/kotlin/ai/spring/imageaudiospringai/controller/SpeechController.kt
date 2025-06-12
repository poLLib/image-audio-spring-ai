package ai.spring.imageaudiospringai.controller

import ai.spring.imageaudiospringai.service.SpeechToTextService
import ai.spring.imageaudiospringai.service.TextToSpeechService
import ai.spring.imageaudiospringai.service.model.AnswerResponse
import ai.spring.imageaudiospringai.service.model.TextToSpeechRequest
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class SpeechController(
        private val textToSpeechService : TextToSpeechService,
        private val speechToTextService : SpeechToTextService,
) {

    @PostMapping(
            value = ["/speaker"],
            consumes = [APPLICATION_JSON_VALUE],
            produces = ["audio/mpeg"]
    )
    fun speak(
            @RequestBody request : TextToSpeechRequest,
    ) : ResponseEntity<ByteArray> = ResponseEntity.ok(textToSpeechService.speak(request))

    @PostMapping(
            value = ["/transcripter"],
            consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
            produces = [APPLICATION_JSON_VALUE]
    )
    fun transcript(
            @Validated @RequestParam file : MultipartFile,
    ) : ResponseEntity<AnswerResponse> = ResponseEntity.ok(speechToTextService.transcript(file))
}