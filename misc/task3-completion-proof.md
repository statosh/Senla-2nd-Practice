## Ниже представлены запросы и ответы к ним после выполнения задания номер 3

### POST http://localhost:8080/api/clients
```json
{
    "fullName": "Иван Петров",
    "phone": "+375291234567",
    "email": "ivan@example.com",
    "id": 1
}
```

---

### GET http://localhost:8080/api/clients
```json
[
    {
        "fullName": "Иван Петров",
        "phone": "+375291234567",
        "email": "ivan@example.com",
        "id": 1
    }
]
```

---

### POST http://localhost:8080/api/cars/client/1
```json
{
    "plateNumber": "A123BC",
    "brand": "Toyota",
    "model": "Camry",
    "productionYear": 2020,
    "vin": "VIN1234567890",
    "client": {
        "fullName": "Иван Петров",
        "phone": "+375291234567",
        "email": "ivan@example.com",
        "id": 1
    },
    "id": 1
}
```

---

### POST http://localhost:8080/api/mechanics
```json
{
    "fullName": "Сергей Сидоров",
    "specialization": "Ходовая часть",
    "phone": "+375293334455",
    "id": 1
}
```

---

### POST http://localhost:8080/api/orders/car/1?faultDescription=Стук в подвеске
```json
{
    "receptionDate": "2026-05-30",
    "faultDescription": "Стук в подвеске",
    "status": "ACCEPTED",
    "car": {
        "plateNumber": "A123BC",
        "brand": "Toyota",
        "model": "Camry",
        "productionYear": 2020,
        "vin": "VIN1234567890",
        "client": {
            "fullName": "Иван Петров",
            "phone": "+375291234567",
            "email": "ivan@example.com",
            "id": 1
        },
        "id": 1
    },
    "id": 1,
    "mechanic": null
}
```

---

### PUT http://localhost:8080/api/orders/1/assign/1
```json
{
    "receptionDate": "2026-05-30",
    "faultDescription": "Стук в подвеске",
    "status": "IN_PROGRESS",
    "car": {
        "plateNumber": "A123BC",
        "brand": "Toyota",
        "model": "Camry",
        "productionYear": 2020,
        "vin": "VIN1234567890",
        "client": {
            "fullName": "Иван Петров",
            "phone": "+375291234567",
            "email": "ivan@example.com",
            "id": 1
        },
        "id": 1
    },
    "id": 1,
    "mechanic": {
        "fullName": "Сергей Сидоров",
        "specialization": "Ходовая часть",
        "phone": "+375293334455",
        "id": 1
    }
}
```

---

### GET http://localhost:8080/api/orders
```json
[
    {
        "receptionDate": "2026-05-30",
        "faultDescription": "Стук в подвеске",
        "status": "IN_PROGRESS",
        "car": {
            "plateNumber": "A123BC",
            "brand": "Toyota",
            "model": "Camry",
            "productionYear": 2020,
            "vin": "VIN1234567890",
            "client": {
                "fullName": "Иван Петров",
                "phone": "+375291234567",
                "email": "ivan@example.com",
                "id": 1
            },
            "id": 1
        },
        "id": 1,
        "mechanic": {
            "fullName": "Сергей Сидоров",
            "specialization": "Ходовая часть",
            "phone": "+375293334455",
            "id": 1
        }
    }
]
```