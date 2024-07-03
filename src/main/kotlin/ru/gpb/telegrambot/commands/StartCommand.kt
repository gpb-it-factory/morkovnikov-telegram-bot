package ru.gpb.telegrambot.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.gpb.telegrambot.uitl.createMessage

@Component
class StartCommand : BotCommand("start", "start work") {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        absSender.execute(createMessage(chat.id, "Добро пожаловать!"))
    }

}