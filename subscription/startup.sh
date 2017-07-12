#!/bin/bash
mvn clean install -Pdev -DskipTests
java -Xmx256m -Xms256m -jar target/subscription-0.0.1-SNAPSHOT.war


