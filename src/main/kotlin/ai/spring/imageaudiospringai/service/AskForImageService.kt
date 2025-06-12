package ai.spring.imageaudiospringai.service

import ai.spring.imageaudiospringai.service.model.QuestionRequest
import org.springframework.ai.image.ImagePrompt
import org.springframework.ai.openai.OpenAiImageModel
import org.springframework.ai.openai.OpenAiImageOptions
import org.springframework.stereotype.Service
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
class AskForImageService(
        private val imageModel : OpenAiImageModel,
) {

    @OptIn(ExperimentalEncodingApi::class)
    fun getImage(
            question : QuestionRequest,
    ) : ByteArray = OpenAiImageOptions
            .builder()
            .width(1792)
            .height(1024)
            .responseFormat("b64_json")
            .model("dall-e-3")
            .quality("hd")
            .style("natural")
            .build()
            .let { imageOptions ->
                imageModel
                        .call(
                                ImagePrompt(
                                        question.question,
                                        imageOptions
                                )
                        )
                        .let { response ->
                            Base64.decode(response.result.output.b64Json)
                        }
            }
}
