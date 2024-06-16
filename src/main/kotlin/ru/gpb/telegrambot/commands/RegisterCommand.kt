package ru.gpb.telegrambot.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.gpb.telegrambot.client.MiddleServiceRestClient
import ru.gpb.telegrambot.uitl.createMessage

@Component
class RegisterCommand(
    private val middleServiceClient: MiddleServiceRestClient,
) : BotCommand("register", "register new user") {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
            val response= middleServiceClient.registerUser(user.id)
            absSender.execute(createMessage(chat.id, response))
    }
}
