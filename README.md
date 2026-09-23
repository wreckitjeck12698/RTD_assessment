# RTD Assessment - Account Service

A robust Spring Boot microservice developed for the Rising Tide Digital (MBTC) assessment, implementing account creation and customer inquiry workflows with an in-memory H2 database.

## 🚀 Tech Stack
* **Java:** 1.8 (Zulu)
* **Framework:** Spring Boot 2.6.14
* **Database:** H2 In-Memory Database (`jdbc:h2:mem:mbtcdb`)
* **Build Tool:** Maven
* **Libraries:** Lombok, Spring Data JPA, Spring Validation

---

## 📌 API Endpoints

### 1. Create Customer Account
* **URL:** `POST /api/v1/account`
* **Success Response (`201 CREATED`):**
  ```json
  {
      "customerNumber": 1,
      "transactionStatusCode": 201,
      "transactionStatusDescription": "Customer account created"
  }
