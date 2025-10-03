# 🐾 Pet Store API Automation with Rest Assured

This project automates testing of the [Swagger Petstore API](https://petstore.swagger.io/) using **Java, Rest Assured, and TestNG**.  
It validates key functionalities of the API through automated tests for **Pets, Store, and Users**.

---

## 🚀 Features

- 🔹 API test automation for CRUD operations (Create, Read, Update, Delete)  
- 🔹 Built with **Java + Rest Assured + TestNG**  
- 🔹 **Maven** for dependency management & builds  
- 🔹 **Extent Reports** for detailed HTML reporting  
- 🔹 Retry mechanism for handling flaky tests  
- 🔹 Supports execution in **Docker** and **CI/CD pipelines**  

---

## 🛠️ Tech Stack

- **Language**: Java 17+  
- **Frameworks**: Rest Assured, TestNG  
- **Build Tool**: Maven  
- **Reporting**: Extent Reports  
- **Containerization**: Docker  

---

## 📂 Project Structure

```plaintext
petstore-restassured/
│
├── src
│   ├── main
│   │   └── java
│   │       ├── config/                 # Configuration classes (base URL, env configs)
│   │       ├── utils/                  # Utility classes (JSON readers, data generators)
│   │       └── models/                 # POJOs (Pet, Store, User request/response models)
│   │
│   └── test
│       └── java
│           ├── base/                   # Base test setup (RestAssured config, before/after)
│           ├── tests/                  # API test classes
│           │    ├── PetsTest.java
│           │    ├── StoreTest.java
│           │    └── UsersTest.java
│           ├── data/                   # Test data providers
│           └── assertions/             # Custom assertion helpers
│
├── resources/
│   ├── testdata/                       # JSON/CSV files with test data
│   └── config.properties               # Environment/test configuration file
│
├── reports/                            # Extent HTML reports generated after execution
│
├── pom.xml                             # Maven dependencies and plugins
├── testng.xml                          # TestNG suite configuration
├── Dockerfile                          # Docker image definition
├── .gitignore                          # Ignored files and folders
└── README.md                           # Project documentation



---

## ▶️ How to Run Tests

### Run with Maven
```bash
mvn clean test

mvn clean test -DsuiteXmlFile=testng.xml

docker build -t petstore-tests .

# Run the container
docker run --rm petstore-tests
# After execution, reports are generated in:

🧪 Test Coverage

- Pets
  - Add a new pet
  - Get pet by ID
  - Update pet details
  - Delete pet
- Store
  - Place an order
  - Get order details
  - Delete order
- Users
  - Create user
  - Get user by username
  - Update user
  - Delete user

  

