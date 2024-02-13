#!/bin/bash

# Check if Java is installed
if ! type java >/dev/null 2>&1; then
    # Download and extract Java
    wget -q -O /tmp/openjdk.tar.gz https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz
    mkdir -p /tmp/openjdk
    tar xfv /tmp/openjdk.tar.gz -C /tmp/openjdk --strip-components=1

    # Set JAVA_HOME environment variable
    export JAVA_HOME=/tmp/openjdk

    # Add Java binary directory to PATH
    export PATH=$JAVA_HOME/bin:$PATH
fi

# Check if Maven is installed
if ! type mvn >/dev/null 2>&1; then
    # Download and extract Maven
    wget -q -O /tmp/apache-maven.tar.gz https://apache.osuosl.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz
    mkdir -p /tmp/apache-maven
    tar xfv /tmp/apache-maven.tar.gz -C /tmp/apache-maven --strip-components=1

    # Add Maven binary directory to PATH
    export PATH=/tmp/apache-maven/bin:$PATH
fi

# Run Maven build
mvn package
