#!/bin/bash

# Install OpenJDK (Java) if necessary
if ! type java >/dev/null 2>&1; then
    apt-get update
    apt-get install -y openjdk-11-jdk
fi

# Install Maven if necessary
if ! type mvn >/dev/null 2>&1; then
    apt-get install -y maven
fi

# Run Maven build
mvn package
