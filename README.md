:

🐾 Pet Store API Automation Project

This project demonstrates API test automation for the Swagger Petstore API
 using Java, Rest Assured, and TestNG.
It is fully Dockerized and integrated with CI/CD pipelines for easy execution in any environment.

🚀 Features

✅ Automated API testing for CRUD operations on Pets, Stores, and Users

✅ Framework built using Java + Rest Assured + TestNG

✅ Reporting with Extent Reports (HTML reports)

✅ Retry logic to handle flaky tests

✅ Runs inside Docker containers for consistent execution

✅ Integrated with Azure DevOps pipelines for CI/CD

✅ Configurable via testng.xml and property files

🛠 Tech Stack

Java 17

Maven

Rest Assured

TestNG

Extent Reports

Docker

Azure DevOps Pipelines

📂 Project Structure
petstore-api-automation/
│── src/test/java/api/     # API test classes
│── src/test/java/util/    # Utilities (listeners, config, retry)
│── pom.xml                # Maven dependencies
│── testng.xml             # Test suite configuration
│── Dockerfile             # Docker build instructions
│── extent-config.xml      # Report configuration
│── README.md              # Project documentation

▶️ Running Tests
🔹 Run Locally (Maven)
mvn clean test

🔹 Run with TestNG suite
mvn clean test -DsuiteXmlFile=testng.xml

🔹 Run Inside Docker

Build image:

docker build -t petstore-tests .


Run tests:

docker run --rm petstore-tests

📊 Reports

After test execution, Extent Reports are generated in:

/app/test-reports/ExtentReport.html


Open the HTML report in your browser for detailed results.

🔄 CI/CD Integration

The project is configured to run in Azure DevOps pipelines with the following steps:

Build Docker image

Push to Docker Hub

Run tests in a container

Publish reports

🌟 Future Enhancements

Add performance testing with JMeter/Gatling

Integrate with Allure Reports

Run parallel tests in Docker using Docker Compose