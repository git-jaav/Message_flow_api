FROM openjdk:8-jdk-alpine
LABEL maintainer="araucovillar@gmail.com"
VOLUME /tmp
WORKDIR /workspace
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENV PAR_VAL_CORREO_ACCOUNT="yimpu4@gmail.com"
ENV PAR_VAL_CORREO_ENVIA="yimpu4@gmail.com"
ENV PAR_VAL_CORREO_HOST="smtp.gmail.com"
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar -Dspring.profiles.active=prod  /workspace/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/workspace/app.jar"]
EXPOSE 8082