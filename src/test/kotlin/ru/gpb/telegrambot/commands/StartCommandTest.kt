package ru.gpb.telegrambot.commands

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender


class StartCommandTest {
    @Test
    fun `test StartCommand`(){
        val absSender = Mockito.mock(AbsSender::class.java)
        val user = User()
        val chat = Chat()
        chat.id = 70701L

        val command = StartCommand()

        command.execute(absSender, user, chat, arrayOf())

        val argumentCaptor = ArgumentCaptor.forClass(SendMessage::class.java)
        Mockito.verify(absSender).execute(argumentCaptor.capture())

        val capturedMessage = argumentCaptor.value
        assertEquals("70701", capturedMessage.chatId)
        assertEquals("Добро пожаловать!", capturedMessage.text)

    }
}