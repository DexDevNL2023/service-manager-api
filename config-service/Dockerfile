FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Copiez le fichier Maven Wrapper et les fichiers nécessaires
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# Rendez le script Maven Wrapper exécutable
RUN chmod +x mvnw

# Exécutez les étapes Maven nécessaires
#RUN ./mvnw dependency:go-offline
#RUN ./mvnw clean package
RUN ./mvnw clean package -DskipTests

# Exposez le port nécessaire
EXPOSE 8888

# Commande pour démarrer l'application
# CMD ["./mvnw", "spring-boot:run"]
CMD ["java", "-jar", "target/configService.jar"]
