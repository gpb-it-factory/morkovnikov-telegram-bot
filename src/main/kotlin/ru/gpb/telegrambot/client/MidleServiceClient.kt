package ru.gpb.telegrambot.client

interface MiddleServiceClient {
    fun registerUser(telegramUserId: Long, name: String): String
}