# ✅ Base image: Maven + JDK 17
FROM maven:3.9.4-eclipse-temurin-17

# ✅ Set working directory inside container
WORKDIR /app

# ✅ Copy your project into container
COPY . .

# ✅ Pre-download all Maven dependencies for faster builds
RUN mvn dependency:go-offline

# ✅ Default command when container runs
CMD ["mvn", "clean", "test", "-DsuiteXmlFile=testng.xml"]
