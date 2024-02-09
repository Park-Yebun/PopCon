# 1단계: Vue.js 앱을 빌드합니다.
FROM node:20.11.0-alpine3.18 AS build-stage

WORKDIR /app

# package.json 및 package-lock.json을 복사하여 종속성을 설치합니다.
COPY . .
RUN npm install
RUN npm install @webzlodimir/vue-bottom-sheet 
# 소스 코드를 복사하여 앱을 빌드합니다.
# COPY . .
RUN npm run build

# 2단계: Nginx로 Vue.js 앱을 호스팅합니다.
FROM nginx:stable-alpine as production-stage

COPY --from=build-stage /app/dist /usr/share/nginx/html

COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]