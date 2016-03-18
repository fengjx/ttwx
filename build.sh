#!/bin/sh 
echo build the release package
mvn -Prelease clean package
echo package end