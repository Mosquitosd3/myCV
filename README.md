🧩 Simple User-Skill API (Spring Boot Demo)

Это демонстрационный REST API-проект, реализованный на Spring Boot, в котором представлены базовые CRUD-операции для управления пользователями и их навыками. Он создан с целью показать работу различных компонентов Spring и практики построения слоистой архитектуры.

🧠 Описание проекта

Проект демонстрирует:

* ✅ Разделение на слои: Controller, Service, Repository, DTO, Util.
* 📚 Работа с Spring Data JPA.
* 🔄 Пагинация на GET-запросах (Pageable).
* 💾 Кеширование (@Cacheable) пользователей.
* 🔐 JWT-аутентификация.
* 📄 Swagger-документация для всех публичных эндпоинтов.
* 🧪 Покрытие Service-слоя unit-тестами с использованием Mockito.
* 🛡 Простая авторизация из памяти (in-memory) — для демонстрации (в реальных проектах — из БД).
* 🔑 Секрет ключ (jwt.secret) хранится в application.properties — в демонстрационных целях (в реальных проектах — в .env или менеджерах секретов).
---
🧱 Стек технологий

Java 17+
Spring Boot
Spring Security + JWT
Spring Data JPA (Hibernate)
ModelMapper
Swagger / OpenAPI
H2 Database (in-memory)
JUnit + Mockito
Spring Cache
---
📁 Структура проекта
* src
* └── main
* ├── controller         // REST API
* ├── service            // Логика
* ├── repository         // Работа с БД
* ├── dto                // DTO-объекты
* ├── entity             // JPA-сущности
* ├── util               // Вспомогательные классы (Mapper и т.п.)
* └── config             // Безопасность, конфигурации
---
🔒 Авторизация

Для демонстрации реализована аутентификация по JWT. Пользователи создаются в памяти.

Пример пользователя:

Логин	Пароль	Роль
admin	12345	ROLE_USER
---

🚀 Запуск проекта

1. Клонировать репозиторий
   git clone https://github.com/Mosquitosd3/myCV.git
   cd demo-user-skill-api
2. Собрать и запустить
   ./mvnw spring-boot:run
   Либо через IDE (например, DemoApplication.java → Run)
---
🔑 Получение токена

Для доступа к защищённым эндпоинтам необходимо получить JWT:

POST /api/auth

* Тело запроса:

{
"username": "admin",
"password": "12345"
}
* Ответ:

{
"token": "eyJhbGciOiJIUzI1NiIsIn..."
}
Затем добавляйте его в Authorization Header:
---
📘 Swagger UI

После запуска доступна документация:
http://localhost:8080/swagger-ui.html

🔍 Основные эндпоинты

Метод	URL	Описание
* GET	/api/users	Получить список пользователей (с пагинацией)
* GET	/api/users/{id}	Получить пользователя по ID
* POST	/api/users	Создать пользователя
* DELETE	/api/users/{id}	Удалить пользователя
* GET	/api/skills	Получить все навыки
* POST	/api/skills?userId={id}	Добавить навык пользователю
* DELETE	/api/skills/{id}	Удалить навык

---
🧪 Тесты

Сервисный слой покрыт unit-тестами:

UserServiceImplTest
SkillServiceImplTest
Тестируются сценарии:

Успешного возврата данных
Обработка ошибок (например, NotFound)
Работа с моками репозиториев и утилит
Запуск тестов:
---

📌 Замечания

⚠️ Внимание: проект носит демонстрационный характер. Для продакшена необходимо:
Хранить секреты в .env, использовать Vault или AWS Secrets Manager.
Реализовать авторизацию из БД.
Использовать полноценную базу данных (PostgreSQL, MySQL и т.д.).
Внедрить логгирование, мониторинг, валидации на уровне контроллеров.
---
🤝 Контакты:

* Автор: Николай Горбунков
* Email: nikolay.gorbunkov@yandex.ru
* Telegram: @MsOogway

