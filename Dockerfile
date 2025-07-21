# Build do front-end
FROM node:18 AS frontend-build
WORKDIR /app-front
COPY app-front/package*.json ./
RUN npm install
COPY app-front/ .
RUN npm run build

# Build do back-end
FROM maven:3.9.4-eclipse-temurin-17 AS backend-build
WORKDIR /app
COPY vendasonline/mvnw .
COPY vendasonline/.mvn/ .mvn/
COPY vendasonline/pom.xml .
COPY vendasonline/src ./src
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Imagem final
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o .jar gerado do Spring Boot
COPY --from=backend-build /app/target/*.jar app.jar

# Copia os arquivos do front-end compilado para pasta estática do backend
COPY --from=frontend-build /app-front/dist ./static

# Configura o Spring para servir os arquivos estáticos em /static
ENV SPRING_WEB_RESOURCES_STATIC_LOCATIONS=file:/app/static/

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
