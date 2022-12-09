FROM openjdk:11-jre-slim
EXPOSE 8080
ADD target/meetcat-chat-docker.jar meetcat-chat-docker.jar
ENTRYPOINT ["java","-jar","meetcat-chat-docker.jar"]