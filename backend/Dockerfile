FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copia o pom.xml
COPY pom.xml .

# Baixa dependências
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Gera o .jar
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia o .jar gerado
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Inicializa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]