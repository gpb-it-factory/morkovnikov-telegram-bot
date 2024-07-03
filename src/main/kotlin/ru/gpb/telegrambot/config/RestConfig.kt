package ru.gpb.telegrambot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate
@Configuration
class RestConfig(
    @Value("\${middle-service.url}")
    private val baseUrl: String
) {
    @Bean
    fun restClient(): RestClient {
        return RestClient
            .builder()
            .baseUrl(baseUrl)
            .build()
    }
}