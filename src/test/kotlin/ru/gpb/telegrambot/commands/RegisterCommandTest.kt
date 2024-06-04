package ru.gpb.telegrambot.commands
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientResponseException
import ru.gpb.telegrambot.client.MiddleServiceClient
import kotlin.random.Random

class RegisterCommandTest {
    private val restClient = mock(RestClient::class.java)
    private val middleServiceClient = MiddleServiceClient(restClient)

    @Test
    @DisplayName("Успешная регистрация пользователя")
    fun successRegisterUser() {
        val telegramUserId = Random.nextLong()
        val requestBody = mapOf("telegramUserId" to telegramUserId)

        val requestSpec = mock(RestClient.RequestBodyUriSpec::class.java)
        val responseSpec = mock(RestClient.ResponseSpec::class.java)

        `when`(restClient.post()).thenReturn(requestSpec)
        `when`(requestSpec.uri("/register")).thenReturn(requestSpec)
        `when`(requestSpec.body(requestBody)).thenReturn(requestSpec)
        `when`(requestSpec.retrieve()).thenReturn(responseSpec)
        `when`(responseSpec.body(String::class.java)).thenReturn("Пользователь ${telegramUserId} успешно зарегестрирован")

        val result = middleServiceClient.registerUser(telegramUserId)
        assertEquals("Пользователь ${telegramUserId} успешно зарегестрирован", result)
    }

    @Test
    @DisplayName("Не удалось подключиться к сервису")
    fun connectServiceErrorTest() {
        val telegramUserId = Random.nextLong()
        val requestBody = mapOf("telegramUserId" to telegramUserId)

        val requestSpec = mock(RestClient.RequestBodyUriSpec::class.java)
        val responseSpec = mock(RestClient.ResponseSpec::class.java)

        `when`(restClient.post()).thenReturn(requestSpec)
        `when`(requestSpec.uri("/register")).thenReturn(requestSpec)
        `when`(requestSpec.body(requestBody)).thenReturn(requestSpec)
        `when`(requestSpec.retrieve()).thenReturn(responseSpec)
        `when`(responseSpec.body(String::class.java)).thenThrow(ResourceAccessException::class.java)

        val result = middleServiceClient.registerUser(telegramUserId)
        assertEquals("Извините, сервис сейчас не работает", result)
    }
    @Test
    @DisplayName("Получение ошибки по вине клиента или сервера")
    fun clientResponseExceptionTest() {
        val telegramUserId = Random.nextLong()
        val requestBody = mapOf("telegramUserId" to telegramUserId)

        val requestSpec = mock(RestClient.RequestBodyUriSpec::class.java)
        val responseSpec = mock(RestClient.ResponseSpec::class.java)

        `when`(restClient.post()).thenReturn(requestSpec)
        `when`(requestSpec.uri("/register")).thenReturn(requestSpec)
        `when`(requestSpec.body(requestBody)).thenReturn(requestSpec)
        `when`(requestSpec.retrieve()).thenReturn(responseSpec)
        `when`(responseSpec.body(String::class.java)).thenThrow(RestClientResponseException::class.java)

        val result = middleServiceClient.registerUser(telegramUserId)
        assertEquals("Ошибка регистрации пользователя", result)
    }

}

