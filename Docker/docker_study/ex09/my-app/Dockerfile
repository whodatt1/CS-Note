FROM node:appine as build
WORKDIR /app
COPY package.json /app
# 라이브러리 설치 노드 모듈이 내부에 생김
RUN npm install --silent
# 있는거 제외하고 복사
COPY . /app
RUN npm run build

FROM nginx
# 위에서 build 별칭으로 부터 빌드 된 것을 복사
COPY --from=build /app/build /usr/share/nginx/html
ENTRYPOINT [ "nginx", "-g", "daemon off;" ]