package ru.gpb.telegrambot.commands

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.http.ResponseEntity
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender

class RegisterCommandTest {
    private val url = "http://pepega.ru"
    private val absSender = Mockito.mock(AbsSender::class.java)
    private val user = User().apply { id = 1402L }
    private val chat = Chat().apply { id = 70701L }
    private val restTemplate = Mockito.mock(RestTemplate::class.java)

    @Test
    fun successfulResponse(){
        val responseEntity = ResponseEntity.ok("Пользователь ${user.id} успешно зарегестрирован")

        Mockito.`when`(restTemplate.postForEntity("$url/register", mapOf("telegramUserId" to user.id), String::class.java))
            .thenReturn(responseEntity)

        val command = RegisterCommand(restTemplate, url)
        command.execute(absSender,user,chat, arrayOf())

        val argumentCaptor = ArgumentCaptor.forClass(SendMessage::class.java)
        Mockito.verify(absSender).execute(argumentCaptor.capture())

        val capturedMessage = argumentCaptor.value
        Assertions.assertEquals("70701", capturedMessage.chatId)
        Assertions.assertEquals("Пользователь ${user.id} успешно зарегестрирован", capturedMessage.text)
    }

    @Test
    fun `Failed to connect to the server` (){


        Mockito.`when`(restTemplate.postForEntity("$url/register", mapOf("telegramUserId" to user.id), String::class.java))
            .thenThrow(ResourceAccessException("Service unavailable"))

        val command = RegisterCommand(restTemplate, url)
        command.execute(absSender,user,chat, arrayOf())

        val argumentCaptor = ArgumentCaptor.forClass(SendMessage::class.java)
        Mockito.verify(absSender).execute(argumentCaptor.capture())

        val capturedMessage = argumentCaptor.value
        Assertions.assertEquals("70701", capturedMessage.chatId)
        Assertions.assertEquals("Извините, сервис сейчас не работает", capturedMessage.text)
    }
}