# Test Task Android

Android-приложение для просмотра списка курсов с авторизацией, избранным и сортировкой по дате добавления.

## Стек

- Kotlin
- Jetpack Compose
- Hilt
- Retrofit
- Room
- Coroutines / Flow
- Multi-module architecture

## Модули

- `:app` — точка входа, навигация, Application/MainActivity
- `:core:domain` — модели, repository interface, use cases
- `:core:data` — Retrofit, Room, repository implementation, DI
- `:core:ui` — тема, цвета, шрифты
- `:feature:auth` — экран авторизации
- `:feature:main` — главный экран, список курсов, избранное

## Возможности

- Авторизация с валидацией email
- Загрузка курсов из удалённого API
- Сортировка курсов по дате публикации
- Добавление и удаление курсов из избранного
- Сохранение избранного локально через Room
- Нижняя навигация между разделами

## Запуск

1. Открыть проект в Android Studio.
2. Выполнить Gradle Sync.
3. Запустить конфигурацию `app`.

Или из терминала:

```bash
./gradlew :app:assembleDebug

Для Windows:

```powershell
.\gradlew.bat :app:assembleDebug
```

## API

Базовый URL задаётся через `BuildConfig.BASE_URL` в модуле `:core:data`.

Endpoint для получения курсов вынесен в `ApiConstants`.

## Тесты

Unit-тесты:

```bash
./gradlew test
```

Для Windows:

```powershell
.\gradlew.bat test
```