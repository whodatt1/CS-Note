FROM openjdk:11-jdk-slim
# 진입 폴더 정하기
# Window에서 Docker 내부로 진입하게될때 / 부터 시작하게 된다. 터미널로 접근을 하게되면 /app 폴더 내부로 접근하게 된다.
WORKDIR /app
# 이 경로의 jar 파일을 application.jar 이름으로 카피한다.
# ADD 압축파일.zip ./ 이렇게 하면 압축이 풀린다.
COPY build/aws-v3-0.0.3.jar ./application.jar
# 실행할 명령어를 넣는 것 , 는 띄어쓰기 구분 dv는 5000번 포트로 돌지만 CMD로 3000로 포트로 돌게됨
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "application.jar"]

CMD ["--server.port=3000"]