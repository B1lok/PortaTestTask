FROM openjdk:17
COPY src src
COPY target/PortaTestTask.jar PortaTestTask.jar
COPY docker-startup.sh docker-startup.sh
RUN mkdir -p /app/data
RUN chmod +x docker-startup.sh
CMD ["./docker-startup.sh"]