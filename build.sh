#!/bin/bash

# Install OpenJDK (Java) if necessary
if ! type java >/dev/null 2>&1; then
    apt-get update
    apt-get install -y openjdk-11-jdk
fi

# Check if Maven is installed
if ! type mvn >/dev/null 2>&1; then
    # Download Maven
    curl -O https://apache.osuosl.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz

    # Extract Maven
    tar -xzf apache-maven-3.8.4-bin.tar.gz

    # Add Maven to PATH
    export PATH=$PWD/apache-maven-3.8.4/bin:$PATH
fi

# Run Maven build
mvn package
