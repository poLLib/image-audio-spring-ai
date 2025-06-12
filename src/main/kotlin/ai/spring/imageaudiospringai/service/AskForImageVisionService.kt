package ai.spring.imageaudiospringai.service

import ai.spring.imageaudiospringai.service.model.AnswerResponse
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.content.Media
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.ai.openai.api.OpenAiApi
import org.springframework.stereotype.Service
import org.springframework.util.MimeTypeUtils
import org.springframework.web.multipart.MultipartFile

@Service
class AskForImageVisionService(
        private val chatModel : ChatModel,
) {

    fun getDescription(
            file : MultipartFile,
    ) : AnswerResponse {
        val openAiChatOptions = OpenAiChatOptions
                .builder()
                .model(OpenAiApi.ChatModel.GPT_4_1_MINI.value)
                .build()

        val userMessage = UserMessage
                .builder()
                .text("Explain what do you see in this picture!")
                .media(
                        Media(
                                MimeTypeUtils.IMAGE_JPEG,
                                file.resource
                        )
                )
                .build()

        val response = chatModel.call(
                Prompt(
                        listOf(userMessage),
                        openAiChatOptions
                )
        )
                .result
                .output
                .text!!

        return AnswerResponse(answer = response)
    }
}

