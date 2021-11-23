FROM openjdk:8-jre-slim
LABEL maintainer="LJE"
VOLUME /tmp
ARG JAR_FILE=./build/libs/*.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./uradom","-jar","/app.jar"]