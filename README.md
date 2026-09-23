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

### 2. Inquiry Customer Details
* **URL:** `GET /api/v1/account/{customerNumber}`
* **Success Response (`302 FOUND`):**
  ```json
  {
    "customerNumber": 1,
    "customerName": "Test User",
    "customerMobile": "09081234567",
    "customerEmail": "test12345@gmail.com",
    "address1": "Test Address",
    "address2": "Test Address 2",
    "savings": [
        {
            "accountNumber": 1,
            "accountType": "Savings",
            "availableBalance": 0
        }
    ],
    "transactionStatusCode": 302,
    "transactionStatusDescription": "Customer Account found"
}


## 🛠️ How to Run Locally

### 1. Clone the repository.
### 2. Open the project in IntelliJ IDEA.
### 3. Locate RtdAssessmentApplication.java under src/main/java/com/mbtc/rtd_assessment.
### 4. Click the green Play button to run the application.
### 5. Access the H2 Database console at: http://localhost:8080/h2-console
* **JDBC URL:** `jdbc:h2:mem:mbtcdb`
* **User Name:** `sa`
* **JDBC URL:** `(leave blank)`
