package ru.gpb.telegrambot.uitl

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

fun createMessage(chatId:Long,messageText:String) =
     SendMessage(chatId.toString(),messageText)