FROM maven:3.9-eclipse-temurin-21-alpine

RUN apt-get update && \
    apt-get install -y --no-install-recommends make && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
