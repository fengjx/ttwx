#!/bin/sh

cd ..
echo build the test package
mvn -Ptest clean package
echo package end