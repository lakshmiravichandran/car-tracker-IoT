FROM openjdk:8
MAINTAINER Lakshmi

COPY . /usr/src/app/
WORKDIR /usr/src/app/
EXPOSE 8085
RUN ./gradlew clean build
RUN mv build/libs/car-tracker-0.0.1-SNAPSHOT.jar cartrackerapp.jar
COPY prop.env /prop.env
ENTRYPOINT ["/bin/bash" , "-c", "source /prop.env && printenv && java -jar cartrackerapp.jar"]
#ENTRYPOINT java -jar cartrackerapp.jar