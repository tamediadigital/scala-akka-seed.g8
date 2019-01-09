#!/usr/bin/env bash
sbt universal:packageBin

cat target/universal/version.properties

docker build -t tamediadigital/$name$ .
docker run  -d --name tamediadigital-$name$ -p 9000:9000 tamediadigital/$name$
docker exec -it tamediadigital-$name$ bash