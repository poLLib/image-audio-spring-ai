package ai.spring.imageaudiospringai.controller

import ai.spring.imageaudiospringai.service.AskForImageService
import ai.spring.imageaudiospringai.service.AskForImageVisionService
import ai.spring.imageaudiospringai.service.model.AnswerResponse
import ai.spring.imageaudiospringai.service.model.QuestionRequest
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.IMAGE_PNG_VALUE
import org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class ImageController(
        private val askForImageService : AskForImageService,
        private val askForImageVisionService : AskForImageVisionService,
) {

    @PostMapping(
            value = ["/image"],
            consumes = [APPLICATION_JSON_VALUE],
            produces = [IMAGE_PNG_VALUE]
    )
    fun askForImage(
            @RequestBody request : QuestionRequest,
    ) : ResponseEntity<ByteArray> = ResponseEntity.ok(askForImageService.getImage(request))

    @PostMapping(
            value = ["/vision"],
            consumes = [MULTIPART_FORM_DATA_VALUE],
            produces = [APPLICATION_JSON_VALUE]
    )
    fun askForImageVision(
            @Validated @RequestParam("file") file : MultipartFile,
    ) : ResponseEntity<AnswerResponse> = ResponseEntity.ok(askForImageVisionService.getDescription(file))
}