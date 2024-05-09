# Телеграм бот приложения "Мини-банк"
Данный сервис представляет собой frontend - часть приложения "Мини-банк"

## Что делает
![img.png](img.png)

## TO BE
```plantuml
@startuml
actor Client
participant TelegramBot
participant MiddleService

Client -> TelegramBot: Команда
activate TelegramBot
alt Необходимо обращение к MiddleService
    TelegramBot --> MiddleService: http-запрос
    activate MiddleService
    MiddleService --> TelegramBot: http-ответ
    deactivate MiddleService
end
TelegramBot -> Client: Ответ
deactivate TelegramBot
@enduml
```

## Участники
В случае вопросов обращатся [Морковникову Виктору](https://github.com/RojoBlanco).