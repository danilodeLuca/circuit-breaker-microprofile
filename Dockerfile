FROM icr.io/appcafe/open-liberty:kernel-slim-java17-openj9-ubi

RUN ./mvnw package

COPY --chown=1001:0 /src/main/liberty/config /config

RUN features.sh

COPY --chown=1001:0 /target/circuit-breaker.war /config/apps

RUN configure.sh
