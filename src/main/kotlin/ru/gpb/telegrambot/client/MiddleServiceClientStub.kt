package ru.gpb.telegrambot.client

class MiddleServiceClientStub: MiddleServiceClient {
    override fun registerUser(telegramUserId: Long): String {
        return "Пользователь ${telegramUserId} успешно зарегестрирован"
    }

}