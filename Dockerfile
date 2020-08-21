FROM openjdk:14-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/bank.war
ADD ${JAR_FILE} bank.war
ENTRYPOINT ["java","-jar","/bank.war"]