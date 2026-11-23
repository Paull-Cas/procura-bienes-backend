# Usamos Java 17 como base
FROM eclipse-temurin:17-jdk-alpine

# Directorio dentro del contenedor
WORKDIR /app

# Copiamos el JAR generado al contenedor
COPY target/*.jar app.jar

# Instala curl (requerido para healthchecks)
RUN apk add --no-cache curl

# Puerto que expone el servicio
EXPOSE 8002

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
