# nginx 이미지 사용 
# builder 라는 이름의 temporary image 
FROM node:lts-alpine as builder

# work dir 고정
WORKDIR /app

COPY package*.json ./

RUN npm install 

COPY . .

RUN npm run build


FROM nginx:stable-alpine

RUN rm -rf /etc/nginx/conf.d/default.conf

# nginx.conf 파일 복사 
# here should be the same name as the nginx configuration file in the project
COPY --from=builder ./nginx.conf /etc/nginx/conf.d/default.conf


RUN rm -rf /usr/share/nginx/html/*

# 임시로 만든 build 이미지를 가져와서 그 파일을 nginx/html 에 복사 
COPY --from=builder /app/dist /usr/share/nginx/html

# 80 포트 개방
EXPOSE 80

# container 실행 시 자동으로 실행할 command. nginx 시작함
CMD ["nginx", "-g", "daemon off;"]