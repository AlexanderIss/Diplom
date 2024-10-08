# Планирование автоматизации тестирования веб-сервиса по покупке тура
## Перечень автоматизируемых сценариев

Предварительные условия: открыть в браузере *http://localhost:8080/*

### 1. Покупка тура дебетовой картой с валидным заполнением формы и с одобрением банка
1) Кликнуть на кнопку: *Купить*
2) Заполнить форму:
   - Номер карты: *4444 4444 4444 4441*
   - Месяц: *текущий месяц +1*
   - Год: *текущий год +1*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомления с текстом:  *"Успешно Операция одобрена Банком."*

### 2. Отказ в покупке тура дебетовой картой с валидным заполнением формы без одобрения банка
1) Кликнуть на кнопку: *Купить*
2) Заполнить форму:
   - Номер карты: *4444 4444 4444 4442*
   - Месяц: *текущий месяц +1*
   - Год: *текущий год +1*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомления с текстом: *"Ошибка! Банк отказал в проведении операции."*

### 3. Отказ в покупке тура дебетовой картой с валидным заполнением формы незарегистрированной картой
1) Кликнуть на кнопку: *Купить*
2) Заполнить форму:
   - Номер карты: *Случайна комбинация 16 цифр*
   - Месяц: *текущий месяц +1*
   - Год: *текущий год +1*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомления с текстом: *"Ошибка! Банк отказал в проведении операции."*

### 4. Покупка тура кредитной картой с валидным заполнением формы и с одобрением банка
1) Кликнуть на кнопку: *Купить в кредит*
2) Заполнить форму:
   - Номер карты: *4444 4444 4444 4441*
   - Месяц: *текущий месяц +1*
   - Год: *текущий год +1*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомления с текстом:  *"Успешно Операция одобрена Банком."*

### 5. Отказ в покупке тура кредитной картой с валидным заполнением формы без одобрения банка
1) Кликнуть на кнопку: *Купить в кредит*
2) Заполнить форму:
   - Номер карты: *4444 4444 4444 4442*
   - Месяц: *текущий месяц +1*
   - Год: *текущий год +1*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомления с текстом: *"Ошибка! Банк отказал в проведении операции."*

### 6. Отказ в покупке тура кредитной картой с валидным заполнением формы незарегистрированной картой
1) Кликнуть на кнопку: *Купить в кредит
2) Заполнить форму:
   - Номер карты: *Случайна комбинация 16 цифр*
   - Месяц: *текущий месяц +1*
   - Год: *текущий год +1*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомления с текстом: *"Ошибка! Банк отказал в проведении операции."*

### 7. Вывод уведомлений об ошибке при отправке формы с незаполненными полями
1) Кликнуть на кнопку: *Купить*
2) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомлений с текстом под полями для ввода: *"Неверный формат"*

### 8. Вывод уведомления об ошибке при отправке формы с невалидным номером карты
1) Кликнуть на кнопку: *Купить в кредит
2) Заполнить форму:
   - Номер карты: *Буквы*
   - Месяц: *текущий месяц +1*
   - Год: *текущий год +1*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомлений с текстом под полями для ввода: *"Неверный формат"*

### 9. Вывод уведомления об ошибке при отправке формы с истекшим сроком обслуживания карты 
1) Кликнуть на кнопку: *Купить в кредит
2) Заполнить форму:
   - Номер карты: *Случайна комбинация 16 цифр*
   - Месяц: *текущий месяц -1*
   - Год: *текущий год - 1*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

### 10. Вывод уведомления об ошибке при отправке формы с 00 месяцем
1) Кликнуть на кнопку: *Купить в кредит
2) Заполнить форму:
   - Номер карты: *Случайна комбинация 16 цифр*
   - Месяц: *00*
   - Год: *до 24 или от 30*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

### 11. Вывод уведомления об ошибке при отправке формы с невалидным владельцем
1) Кликнуть на кнопку: *Купить в кредит
2) Заполнить форму:
   - Номер карты: *Случайна комбинация 16 цифр*
   - Месяц: *текущий месяц +1*
   - Год: *текущий год +1*
   - Владелец: *Имя и фамилия на кириллице*
   - СVC/CVV: *Трехзначное число*
3) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомлений с текстом под полями для ввода: *"Поле обязательно для заполнения"*

### 12. Вывод уведомления об ошибке при отправке формы с невалидным CVC/CVV
1) Кликнуть на кнопку: *Купить в кредит
2) Заполнить форму:
   - Номер карты: *Случайна комбинация 16 цифр*
   - Месяц: *текущий месяц +1*
   - Год: *текущий год +1*
   - Владелец: *Имя и фамилия на латинице*
   - СVC/CVV: *Две цифры*
3) Кликнуть на кнопку: *Продолжить*

**Ожидаемый результат:** появление уведомлений с текстом под полями для ввода: *"Неверный формат"*

## Перечень используемых инструментов

- **IntelliJ IDEA** - среда разработки. Нужна для написания автотестов
- **Java** - язык программирования. Нужен для написания автотестов
- **Git** - система управления версиями. Нужена для сохранения версий автотестов
- *GitHub** - система управления версиями. Нужена для дистанционного доступа к проекту
- **Gradle** - система управления проектами. Нужна для автоматической загрузки библиотек и установления зависимостей
- **JUnit-5** - фреймфорк для модульного тестирования ПО. Нужен для написания автотестов и их запусков
- **Selenide** - фреймворк для автоматизированного тестирования веб-приложений. Нужен автоматизации тестов
- **Lombok** - библиотека позволяющая сократить шаблонный код. Нужна для более короткого написания тестов
- **Faker** - библиотека для генерации. Нужна для генерации тестовых данных
- **Docker** – приложение для создания и запуска контейнеров. Нужно для запуска контенеров: MySQL, PostgreSQL и иэмулятора банковских сервисов
- **Allure** - фреймворк для визуализации отчетов. Нужен для визуализации отчета по тестировнию

## Перечень возможных рисков
- Неполное покрытие тестами. Может привезти к пропуску ошибок
- Излишнее покрытие автотетами. Можно сделать большое колличесво эквивалентных тестов что потратит время как при написании так и последующем тестировании
- Отсутствие документации. Отсутствие точных инструкций. Неизвестно как должен повести себя сервис в той или иной ситуации
- Отсутствие опыта. Отсутствие опыта в написании автотестов может замедлить их написание

## Интервальная оценка с учётом рисков в часах

27 рабочих часов + 9 часов учитывая риски = 36 раб. часов

## План сдачи работ: когда будут готовы автотесты, результаты их прогона
Автотесты будут написаны к 10.09.2024

Отчет по автотестам к 12.09.2024
