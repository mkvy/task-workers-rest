## Описание

Сервис реализует REST методы для работы с задачами и исполнителями

`Tasks: id, title, description, time, status, performer(исполнитель задачи)`

`Workers: id, name, position, avatar (img)`

Сервер доступен по адресу `localhost:8080`

Сервер складывает задачи в очередь, после чего считывает по 3 задачи с очереди и добавляет в БД их несколькими потоками.

Запросы к БД реализованы через jdbcTemplate.

В качестве БД используется postgres, для поднятия БД необходимо ввести `docker-compose up`.

Над сущностями task и worker доступны CRUD-операции.

## Запросы к серверу

---
### Запросы к таблице Workers

---

#### Добавление исполнителя

#### Request

`POST /api/v1/worker`

Содержит json-body со следующими строками:
- `name_w` - строка, имя исполнителя
- `position_p` - строка, позиция исполнителя
- `avatar (img)` - строка(массив байт), изображение

Пример:
```json
{
    "name_w":"Test Name",
    "position_p":"pos pos",
    "avatar_image":"YWJj"
}
```

#### Response 

Ответ возвращает статус 201 при успешном добавлении.

---
#### Получение исполнителей


#### Request

`GET /api/v1/worker`


#### Response

Ответ возвращает статус 200 при успешном получении и JSON со списком исполнителей.

---
#### Получение исполнителей с назначенными задачами

#### Request

`GET /api/v1/worker/tasks`

#### Response

Ответ возвращает статус 200 и JSON со списком исполнителей и назначенных им задач. Данные об исполнителе содержатся в `workerInfo`, данные о тасках в `taskInfo`

Пример:

```json
[
    {
        "workerInfo": {
            "id": 3,
            "name_w": "Testov Name",
            "position_p": "develop",
            "avatar_image": "YWJj"
        },
        "taskInfo": {
            "taskId": 24,
            "title": "Test title",
            "status": "work"
        }
    }
]
```

---
#### Получение исполнителя по id

#### Request

`GET /api/v1/worker/{id}`

`id` - id исполнителя в базе данных

#### Response

Ответ возвращает статус 200 при успешном получении и JSON со исполнителем. Если не найдено, возвращает код 404.

---
#### Обновление исполнителя по id


#### Request

`PUT /api/v1/worker/{id}`

Содержит в себе json-body с новыми данными. `id` - id пользователя в БД. 

Пример:
```json
{
    "name_w":"New Name",
    "position_p":"New pos",
    "avatar_image":"YWJj"
}
```

#### Response

Ответ возвращает статус 200 и число обновленных строк. Если не найдено, возвращает код 404.

---
#### Удаление исполнителя по ID

#### Request

`DELETE /api/v1/worker/{id}`

id` - id пользователя в БД.

#### Response

Ответ возвращает статус 204 и число удаленных строк. Если не найдено, возвращает код 404.

---

### Запросы к таблице Tasks

---

#### Создание задачи

#### Request

`POST /api/v1/task`

Добавляет задачу в очередь на создание. Задачи добавляются в БД из очереди по 3 штуки.

Содержит json-body со следующими строками:
- `title` - строка, название задачи
- `description` - строка, описание задачи
- `time_t` - timestamp, время
- `status` - строка, статус задачи
- `performer_id` - число, id исполнителя из таблицы workers

Пример:
```json
{
  "title":"As de",
  "description":"Desc test",
  "time_t":"2020-10-19T01:19:56.534+0000",
  "status":"working",
  "performer_id":3
}
```

#### Response

Ответ возвращает статус 200.

---

#### Обновление задачи

#### Request

`PUT /api/v1/task/{id}`

Обновляет задачу по ID. Содержит в себе JSON-body с новыми данными.

Пример:
```json
{
  "title": "New data",
  "description": "New desc",
  "status": "not work",
  "time_t": "2021-10-19T01:19:56.534+00:00"
}
```

#### Response

Ответ возвращает статус 200 и число обновленных строк. Если запись не найдена, возвращает статус 404.

---

#### Назначение исполнителя у задачи

#### Request

`PATCH /api/v1/task/{id}`

`id` - id задачи в БД.

Меняет performer_id исполнителя задачи c заданным `id`. Содержит в себе JSON-body с новым id

Пример:
```json
{
  "performer_id":1
}
```

#### Response

Ответ возвращает статус 200 и число обновленных строк. Если запись не найдена, возвращает статус 404.

---
#### Получение задачи по id

#### Request

`GET /api/v1/task/{id}`

`id` - id задачи в базе данных

#### Response

Ответ возвращает статус 200 при успешном получении и JSON со задачей. Если не найдено, возвращает код 404.

Пример:

```json
{
    "id": 22,
    "title": "3testConc",
    "description": "asd",
    "status": "work",
    "time_t": "2020-10-19T01:19:56.534+00:00",
    "performer_id": 3
}
```

---
#### Получение всех задач с кратким описанием

#### Request

`GET /api/v1/task`


#### Response

Ответ возвращает статус 200 при успешном получении и JSON со задачами. Содержит поля `taskId`, `title`, `status`. Если не найдено, возвращает код 404.

Пример:
```json
[
    {
        "taskId": 1,
        "title": "First task",
        "status": "pending"
    },
    {
        "taskId": 2,
        "title": "Second task",
        "status": "working"
    }
]
```

#### Удаление задачи по id

#### Request

`DELETE /api/v1/task/{id}`

`id` - id задачи в базе данных.

#### Response

Ответ возвращает статус 204 при успешном удалении и число удаленных строк. Если не найдено, возвращает код 404.
