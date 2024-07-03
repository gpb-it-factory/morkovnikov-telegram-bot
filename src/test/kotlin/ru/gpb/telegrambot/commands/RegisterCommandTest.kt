package ru.gpb.telegrambot.commands
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.gpb.telegrambot.client.MiddleServiceClientStub
import kotlin.random.Random

class RegisterCommandTest {
    private val middleServiceClient = MiddleServiceClientStub()
    private val  registerCommand = RegisterCommand(middleServiceClient)

    @Test
    @DisplayName("Успешная регистрация пользователя")
    fun successRegisterUser() {
        val telegramUserId = Random.nextLong()
        val telegramUserName = "pepega1"
        val chatId = 67890L

        val absSender: AbsSender =  mock()
        val user = User().apply {
            id = telegramUserId
            userName = telegramUserName
        }
        val chat = Chat()
        chat.id = chatId

        registerCommand.execute(absSender, user, chat, emptyArray())
        val argumentCaptor = ArgumentCaptor.forClass(SendMessage::class.java)
        Mockito.verify(absSender).execute(argumentCaptor.capture())

        val capturedMessage = argumentCaptor.value

        assertEquals("Пользователь ${user.id} успешно зарегестрирован", capturedMessage.text)
    }

}

