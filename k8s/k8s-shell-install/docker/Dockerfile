# 
FROM openjdk:8-jdk

LABEL maintainer=suyunhong


#docker run -e PARAMS="--server.port 9090"
ENV PARAMS="--server.port=8080"
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

COPY target/*.jar /demo-swagger.jar
EXPOSE 8080

#
ENTRYPOINT ["/bin/sh","-c","java -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar demo-swagger.jar ${PARAMS}"]