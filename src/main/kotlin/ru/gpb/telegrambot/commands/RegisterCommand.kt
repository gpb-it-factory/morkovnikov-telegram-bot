package ru.gpb.telegrambot.commands

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.gpb.telegrambot.enums.CommandEnum
import ru.gpb.telegrambot.uitl.createMessage

@Component
class RegisterCommand : BotCommand(CommandEnum.REGISTER.command, CommandEnum.REGISTER.description) {

    @Value("\${midle-service.url}")
    private lateinit var url: String

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        val registerEndpoint = "$url/register"
        val requestBody = mapOf("telegramUserId" to user.id)
        val responseTemplate = RestTemplate()
        try {
            val response: ResponseEntity<String> = responseTemplate.postForEntity(registerEndpoint, requestBody, String::class)
            absSender.execute(createMessage(chat.id, "${response.body}"))
        }catch ( e: ResourceAccessException){
            println(e.message)
            absSender.execute(createMessage(chat.id, "Извините, сервис сейчас не работает"))
        }


    }

}