package ru.gpb.telegrambot.client

class MiddleServiceClientStub: MiddleServiceClient {
    override fun registerUser(telegramUserId: Long, name: String): String {
        return "Пользователь ${telegramUserId} успешно зарегестрирован"
    }

}