FROM openjdk:17-jdk-slim

WORKDIR /app

# COPY만 docker-compose 파일의 위치를 기반으로 작동함
COPY . .

# 개행문자 오류 해결 [unix와 window 시스템 차이]
RUN sed -i 's/\r$//' gradlew

# RUN은 현재 파일의 위치를 기반으로 작동함
RUN chmod +x ./gradlew
RUN ./gradlew clean build --info

# 임시 변수
ENV JAR_PATH=/app/build/libs

# JAR 파일을 이동
RUN mv ${JAR_PATH}/product-0.0.1-SNAPSHOT.jar /app/app.jar

# "-Dspring.profiles.active=prod",
ENTRYPOINT ["java", "-jar", "app.jar"]