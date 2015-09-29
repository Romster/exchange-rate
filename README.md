# exchange-rate
Simple Spring Boot application: REST-service to check exchange rates(RUB). It uses data of Central Bank of Russia 

## Запуск приложения

>mvn package && java -jar target/exchange-rate-1.0-SNAPSHOT.jar

## API Приложения

### Запрос

>GET /api/rate/{code}/{date}

#### Параметры

* code [строка 3 символа, обязателен] - трехсимвольный код валюты
* date [дата в формате yyyy-MM-dd, строка, опционален] - дата актуальности курса
* Если в запросе не указана дата актуальности курса, то такой датой считается следующий день от текущей даты осуществления запроса.

### Ответ

>{
    "code": "<Код валюты>",
    "rate": "<Курс>",
    "date": "<Дата актуальности>"
}

Ответ содержит следующие поля:

* code [строка 3 символа, обязателен] - трехсимвольный код валюты
* rate [десятичное число, строка, обязателен] - курс валюты
* date [дата в формате yyyy-MM-dd, строка, обязателен] - дата актуальности курса

### Примеры запросов

>GET http://example.com/currency/api/rate/USD

>GET http://example.com/currency/api/rate/USD/2015-09-24

Пример ответа

>{
    "code": "USD",
    "rate": "66.0410",
    "date": "2015-09-24"
}
