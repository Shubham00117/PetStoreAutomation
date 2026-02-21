# 🐾 Pet Store API Automation — REST Assured Framework

A professional **API test automation framework** built with REST Assured for the **Swagger PetStore API**. Features a layered architecture with endpoints, payloads, utilities, data-driven testing, and Extent Reports.

---

## 📖 Overview

This project demonstrates a production-grade API testing framework using REST Assured. It tests full CRUD operations on the Swagger PetStore User API with proper separation of concerns — endpoints, payloads, test data, and utilities.

---

## 📂 Project Structure

```
PetStoreAutomation/
├── pom.xml                             # Maven build configuration
├── src/test/java/
│   ├── api/
│   │   ├── endpoints/
│   │   │   ├── Routes.java             # API route constants (from properties)
│   │   │   ├── UserEndPoints.java      # Endpoint methods (CRUD)
│   │   │   └── UserEndPoints2.java     # Alternate endpoint implementation
│   │   ├── playload/
│   │   │   └── User.java              # POJO for User data
│   │   ├── test/
│   │   │   ├── UserTests.java         # Core user CRUD tests
│   │   │   ├── UserTests2.java        # Tests with properties-based routes
│   │   │   └── DDTest.java            # Data-driven tests
│   │   └── utilities/
│   │       ├── DataProviders.java     # TestNG data providers (Excel)
│   │       ├── ExcelUtility.java      # Excel file reader
│   │       └── ExtentReportManager.java # HTML report generation
├── src/test/resources/
│   ├── routes.properties               # API route configuration
│   └── log4j2.xml                      # Logging configuration
└── testData/
    └── Userdata.xlsx                   # Test data for data-driven testing
```

---

## 🏗️ Framework Architecture

```
Routes (Properties) → EndPoints (API Methods) → Tests (Assertions)
                          ↑                          ↑
                      Payloads (POJO)         DataProviders (Excel)
                                                     ↑
                                              ExcelUtility + ExtentReports
```

---

## 🛠️ Tech Stack

| Technology | Purpose |
|-----------|---------|
| **Java** | Programming language |
| **REST Assured** | API testing library |
| **TestNG** | Test framework & data providers |
| **Extent Reports** | Rich HTML test reporting |
| **Apache POI** | Excel-based test data |
| **Log4j2** | Logging framework |
| **Maven** | Build & dependency management |

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 11+
- Maven 3.x

### Installation
```bash
git clone https://github.com/Shubham00117/PetStoreAutomation.git
cd PetStoreAutomation
mvn clean install
```

### Running Tests
```bash
mvn test
```

---

## 📜 License

This project is open source and available for educational purposes.
