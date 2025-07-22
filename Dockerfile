# Build do front-end
FROM node:18 AS frontend-build
WORKDIR /app-front

COPY app-front/package*.json ./
RUN npm install

COPY app-front/ .

ARG VITE_API_BASE_URL
ENV VITE_API_BASE_URL=$VITE_API_BASE_URL

RUN echo "VITE_API_BASE_URL=$VITE_API_BASE_URL" > .env.production

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

COPY --from=backend-build /app/target/*.jar app.jar
COPY --from=frontend-build /app-front/dist ./static

ENV SPRING_WEB_RESOURCES_STATIC_LOCATIONS=file:/app/static/

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]