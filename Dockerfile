# Fase 1: Compilación (Build)
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
# Copiamos tu pom.xml y el código fuente
COPY pom.xml .
COPY src ./src
# Ejecutamos el empaquetado saltando los tests para ir más rápido
RUN mvn clean package -DskipTests

# Fase 2: Ejecución (Runtime)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el .jar que Maven acaba de crear en la fase anterior
COPY --from=build /app/target/madridlink-0.0.1-SNAPSHOT.jar app.jar
# Exponemos el puerto estándar
EXPOSE 8082
# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-Dserver.port=8082", "-jar", "app.jar"]