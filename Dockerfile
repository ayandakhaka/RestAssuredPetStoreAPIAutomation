# Base image: Maven + JDK 17
FROM maven:3.9.4-eclipse-temurin-17

# Working directory inside container
WORKDIR /app

# Copy pom.xml and download dependencies (cached for speed)
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY testng.xml ./

# copy extent report
COPY extent-config.xml ./

# Copy project source
COPY src ./src

# Create directory for test reports
RUN mkdir -p /app/test-reports

# Run tests and save reports to /app/test-reports
CMD ["mvn", "test", "-Dsurefire.reportFormat=xml", "-Dsurefire.useFile=true", "-Dsurefire.reportDirectory=/app/test-reports"]
