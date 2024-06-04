package ru.gpb.telegrambot.client

import org.springframework.stereotype.Component
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientResponseException
import ru.gpb.telegrambot.config.RestConfig
import ru.gpb.telegrambot.uitl.createMessage

@Component
class MiddleServiceClient(
    private val restClient: RestClient
) {
    fun registerUser(telegramUserId: Long): String {
        val registerEndpoint = "/register"
        val requestBody = mapOf("telegramUserId" to telegramUserId)

        return try{
            val response = restClient.post()
                .uri(registerEndpoint)
                .body(requestBody)
                .retrieve()
                .body(String::class.java)
            response ?: ""
        }
        catch (e: ResourceAccessException) {
           "Извините, сервис сейчас не работает"
        }
        catch (e: RestClientResponseException){
            "Ошибка регистрации пользователя"
        }
        catch (e: Exception){
            "Вероятно произошел конец света"
        }
    }
}