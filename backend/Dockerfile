# FROM - skip colon and version to pull the latest jdk version
# Use the OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set directory, in a container, for executing future commands
WORKDIR /app

# Copy the app files from host machine to image filesystem
# Skopiowanie pliku JAR do kontenera i nadanie mu nazwy nazwa.jar
# COPY - the first param applies to a host, the second param applies to an image
#COPY target/foodtoeat-0.0.1-SNAPSHOT.jar foodToEat.jar
COPY target/waste-no-food-0.0.1-SNAPSHOT.jar waste-no-food.jar

EXPOSE 8081

# Ustawienie komendy startowej kontenera, która uruchomi aplikację
ENTRYPOINT ["java", "-jar", "waste-no-food.jar"]