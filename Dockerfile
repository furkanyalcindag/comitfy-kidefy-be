
#RUN apk add --update fontconfig freetype
FROM maven:3.8.1-openjdk-17-slim AS build



# Refer to Maven build -> finalName
ARG JAR_FILE=target/izbilir-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

COPY ./ /opt/app
RUN mvn clean install -DskipTests

# cp target/spring-boot-web.jar /opt/app/app.jar
#COPY ${JAR_FILE} app.jar

FROM eclipse-temurin:17-jdk-alpine
RUN apk --no-cache add msttcorefonts-installer fontconfig && update-ms-fonts && fc-cache -f
COPY --from=build /opt/app/target/*.jar app.jar



# java -jar /opt/app/app.jar
#ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar","app.jar"]

#ENTRYPOINT ["java",  " $JAVA_OPTS -Dfile.encoding=UTF-8 -jar","app.jar"]

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS}  -Dfile.encoding=UTF-8 -jar app.jar"]