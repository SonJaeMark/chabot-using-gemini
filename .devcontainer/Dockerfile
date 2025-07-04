# Base Java image
FROM eclipse-temurin:21-jdk

# Install Git + bash (and optionally Maven)
RUN apt-get update && apt-get install -y \
    git \
    bash \
    maven \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/*
