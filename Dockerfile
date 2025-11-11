FROM mcr.microsoft.com/devcontainers/java:1-21-bullseye

WORKDIR /app

# Copy the entire source code
COPY . .
# Copy Maven wrapper and pom.xml first for caching dependencies
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline


# Set JAVA_TOOL_OPTIONS environment variable to limit heap
ENV JAVA_TOOL_OPTIONS="-Xmx512m"

# OLD Default command to run the Spring Boot app in dev mode with hot reload 
# CMD ["./mvnw", "spring-boot:run"]

CMD [ "sleep", "infinity" ]

# Default command: Force a clean build/package, skip tests, AND THEN run the application
#CMD ["/bin/sh", "-c", "./mvnw clean package -DskipTests && ./mvnw spring-boot:run"]

EXPOSE 8080
