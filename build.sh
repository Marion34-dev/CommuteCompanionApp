#!/bin/bash

# Install Java (assuming OpenJDK)
sudo apt-get update
sudo apt-get install -y openjdk-11-jdk

# Install Maven
sudo apt-get install -y maven

# Run Maven build (assuming your Maven build command is 'mvn package')
mvn package
