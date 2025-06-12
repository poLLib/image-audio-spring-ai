package ai.spring.imageaudiospringai.controller

import ai.spring.imageaudiospringai.service.TextToSpeechService
import ai.spring.imageaudiospringai.service.model.TextToSpeechRequest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SpeechController(
        private val textToSpeechService : TextToSpeechService,
) {

    @PostMapping(
            value = ["/speaker"],
            consumes = [APPLICATION_JSON_VALUE],
            produces = ["audio/mpeg"]
    )
    fun speak(
            @RequestBody request : TextToSpeechRequest,
    ) : ResponseEntity<ByteArray> = ResponseEntity.ok(textToSpeechService.speak(request))
}