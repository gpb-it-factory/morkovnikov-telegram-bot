package ru.gpb.telegrambot.bot

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Update
import ru.gpb.telegrambot.uitl.createMessage

@Service
class TelegramBot(
    commands: Set<BotCommand>,
    @Value("\${telegram.token:}")
    token: String
) : TelegramLongPollingCommandBot(token) {

    init {
        registerAll(*commands.toTypedArray())
    }

    @Value("\${telegram.botName}")
    private val botName: String = ""

    override fun getBotUsername(): String = botName
    override fun processNonCommandUpdate(update: Update) {
        execute(createMessage(update.message.chatId, "Извините, вы ввели не команду :("))
    }
}