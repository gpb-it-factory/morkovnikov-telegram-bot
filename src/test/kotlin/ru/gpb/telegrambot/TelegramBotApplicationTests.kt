package ru.gpb.telegrambot

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import ru.gpb.telegrambot.config.BotConfig

@SpringBootTest
class TelegramBotApplicationTests {

	@MockBean
	lateinit var telegramBot: BotConfig

	@Test
	fun contextLoads() {
	}
}
