Проект виртуальной стажировки: Микросервисы
Обзор
Этот репозиторий содержит проект на основе микросервисов, разработанный в рамках виртуальной стажировки. Проект состоит из трех основных приложений: insurance-calculator-app, doc-generator-app и black-list-app. Для обмена сообщениями используется RabbitMQ, для хранения данных — MySQL, а для контейнеризации — Docker. Проект демонстрирует обработку сообщений с использованием механизма Dead Letter Queue (DLQ) для обработки неудачных сообщений.
Технологический стек

Языки программирования: Java (Spring Boot)
Брокер сообщений: RabbitMQ
База данных: MySQL
Контейнеризация: Docker, Docker Compose
Другие инструменты: Gradle (для сборки проекта)

Структура проекта

insurance-calculator-app/: Приложение на Spring Boot, которое принимает запросы от клиента и отправляет сообщения в RabbitMQ для обработки.
doc-generator-app/: Приложение на Spring Boot, которое генерирует документы с предложениями и сохраняет их в указанную директорию. Слушает сообщения из RabbitMQ и реализует механизм DLQ для обработки неудачных сообщений.
black-list-app/: Приложение на Spring Boot, которое проверяет запросы на наличие в черном списке, используя базу данных MySQL.
docker-compose.yml: Файл конфигурации Docker Compose для запуска всех сервисов.
wait-for-it.sh: Скрипт для ожидания готовности зависимостей (например, RabbitMQ).
mysql-database-init/: Скрипты или файлы для инициализации базы данных MySQL.

Скриншоты сервисов
Ниже приведены изображения, демонстрирующие работу сервисов. Вы можете добавить свои скриншоты в директорию screenshots/ и обновить ссылки ниже.

Интерфейс RabbitMQЗдесь будет скриншот интерфейса RabbitMQ (например, страница очередей q.proposal-generation и q.proposal-generation-dlq).

Логи doc-generator-containerЗдесь будет скриншот логов doc-generator-container, показывающий обработку сообщения или ошибку.

Результат работы black-list-appЗдесь будет скриншот результата работы black-list-app (например, API-ответ или логи, показывающие проверку черного списка).


Требования

Установленные Docker и Docker Compose.
Java 21 (для локальной разработки, если не используется Docker).
Gradle (для локальной сборки проекта).

Установка и запуск проекта
1. Клонируйте репозиторий
git clone https://github.com/JediAnaki/intership_virtual.git
cd intership_virtual

2. Сборка и запуск с помощью Docker Compose
Проект использует Docker Compose для управления сервисами.
docker-compose down
docker-compose build --no-cache
docker-compose up

Это запустит:

rabbitmq-container (RabbitMQ с интерфейсом управления на http://localhost:15672, логин: guest/guest)
mysql-container (база данных MySQL)
black-list-container (black-list-app)
insurance-calc-container (insurance-calculator-app)
doc-generator-container (doc-generator-app)

3. Проверка сервисов

Откройте интерфейс RabbitMQ по адресу http://localhost:15672 (логин: guest/guest).
Проверьте логи контейнеров, чтобы убедиться, что все сервисы работают:docker logs black-list-container
docker logs insurance-calc-container
docker logs doc-generator-container



Тестирование
Проект поддерживает тестирование различных сценариев обработки сообщений. Для проверки работы механизма обработки сообщений и Dead Letter Queue (DLQ):

Откройте интерфейс RabbitMQ (http://localhost:15672).
Перейдите в очередь q.proposal-generation.
Отправьте тестовое сообщение в формате JSON, чтобы проверить обработку.
Проверьте логи doc-generator-container и состояние очередей в RabbitMQ для анализа результатов.
Убедитесь, что black-list-container корректно фильтрует запросы (проверьте логи или базу данных).

Устранение неполадок

Сервис не запускается: Проверьте логи (docker logs <container-name>).
Сообщения не обрабатываются: Убедитесь, что RabbitMQ доступен, и проверьте логи doc-generator-container на наличие ошибок.
Применяется старая конфигурация: Используйте --no-cache при пересборке, чтобы избежать кэширования.

Вклад в проект
Если хотите внести изменения, создайте форк репозитория и отправьте pull request. Для крупных изменений сначала откройте issue, чтобы обсудить их.
Контакты
По любым вопросам обращайтесь через GitHub Issues.
