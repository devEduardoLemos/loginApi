FROM openjdk:17

COPY target/login_api.jar /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","login_api.jar"]