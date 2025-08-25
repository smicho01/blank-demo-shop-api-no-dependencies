# Use Eclipse Temurin 17 JDK as base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for better caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make Maven wrapper executable
RUN chmod +x ./mvnw

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Create a new stage for the runtime
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage (specific name based on pom.xml)
COPY --from=0 /app/target/blank-demo-api-0.0.1-SNAPSHOT.jar app.jar

# Create non-root user for security
RUN addgroup -g 1001 -S appuser && \
    adduser -S -D -H -u 1001 -s /sbin/nologin -G appuser appuser
USER appuser

# Expose port 8080
EXPOSE 8080

# Set JVM options for containerized environment
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/customer || exit 1

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]