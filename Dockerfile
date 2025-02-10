# Build stage

FROM gradle:7.6.0-jdk17-alpine AS builder

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test


# Run stage

FROM amazoncorretto:17

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENV JVM_OPTIONS="-Xms128m -Xmx384m"

ENTRYPOINT ["sh", "-c", "java \
  ${JVM_OPTIONS} \
  -jar app.jar \
  --spring.profiles.active=${PROFILE} \
  -DRDS_URL=${RDS_URL} \
  -DRDS_USERNAME=${RDS_USERNAME} \
  -DRDS_PASSWORD=${RDS_PASSWORD} \
  -DREDIS_HOST=${REDIS_HOST} \
  -DREDIS_PASSWORD=${REDIS_PASSWORD} \
  -DJWT_SECRET=${JWT_SECRET} \
  -DEMAIL_URL=${EMAIL_URL}"]
