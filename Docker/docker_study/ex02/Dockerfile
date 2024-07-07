FROM ubuntu

RUN apt-get update
# 이미지 만들다 용량을 물어보는 부분때문에 -y 옵션을 붙인다.
RUN apt-get install -y nginx
# 이곳으로 바로 이동
WORKDIR /var/www/html

COPY ./webapp/index.html ./index.nginx-debian.html
# -g => 데몬을 끄고 실행하여라 백그라운드로 실행하지 말고 포그라운드로 실행
# 이래야 컨테이너가 안멈춤..
ENTRYPOINT ["nginx", "-g", "daemon off;"]