FROM nginx:1.15
MAINTAINER 1196417540@qq.com
COPY ./client/dist/  /usr/share/nginx/html/
COPY ./nginx.conf /etc/nginx/nginx.conf
