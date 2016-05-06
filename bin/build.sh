#!/bin/sh

cd ..
echo build the release package
mvn -Prelease clean package
echo package end