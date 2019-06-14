#!/usr/bin/env bash

if [ "$#" -ne 0 ]
then
mkdir -p ../target/coursier/stage/"$1"

./coursier bootstrap -r jitpack com.github.tmtsoftware.csw-client:csw-client_2.12:$1 \
    -M csw.client.Main \
    -f -o ../target/coursier/stage/"$1"/csw-client

echo "Artifacts successfully generated"
else
echo "[ERROR] Provide version ID as argument"
fi
