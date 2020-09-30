FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER 1196417540@qq.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /official

WORKDIR /official

#EXPOSE 4000

ADD ./target/cloud-0.0.1-SNAPSHOT.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar cloud-0.0.1-SNAPSHOT.jar