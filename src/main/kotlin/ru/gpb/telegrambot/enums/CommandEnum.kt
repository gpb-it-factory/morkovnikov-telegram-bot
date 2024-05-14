package ru.gpb.telegrambot.enums

enum class CommandEnum(val command: String, val description: String) {
    START("start", "start work"),
    PING("ping" , "ping-pong"),
}