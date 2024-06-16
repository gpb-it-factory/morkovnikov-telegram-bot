package ru.gpb.telegrambot.commands
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.gpb.telegrambot.client.MiddleServiceClientStub
import kotlin.random.Random

class RegisterCommandTest {
    private val middleServiceClient = MiddleServiceClientStub()

    @Test
    @DisplayName("Успешная регистрация пользователя")
    fun successRegisterUser() {
        val telegramUserId = Random.nextLong()
        val userName = "pepega1"

        val result = middleServiceClient.registerUser(telegramUserId, userName)

        assertEquals("Пользователь ${telegramUserId} успешно зарегестрирован", result)
    }

}

