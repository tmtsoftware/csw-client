#!/usr/bin/env bash

scriptsDir="$(
  cd "$(dirname "$0")" || exit
  pwd -P
)"

rootDir="$(dirname "$scriptsDir")"

if [ "$#" -ne 0 ]; then
  mkdir -p ../target/coursier/stage/"$1"

  "$scriptsDir"/coursier bootstrap -r jitpack com.github.tmtsoftware:csw-client_2.13:"$1" \
    -M client.Main \
    -f -o "$rootDir"/target/coursier/stage/"$1"/csw-client

  echo "Artifacts successfully generated"
else
  echo "[ERROR] Provide version ID as argument"
fi
