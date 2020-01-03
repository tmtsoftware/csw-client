#!/usr/bin/env bash

scriptsDir="$(
  cd "$(dirname "$0")" || exit
  pwd -P
)"

rootDir="$(dirname "$scriptsDir")"
targetDir=$rootDir/target/coursier/stage/"$1"/

if [ "$#" -ne 0 ]; then
  mkdir -p "$targetDir"

  "$scriptsDir"/coursier bootstrap -r jitpack com.github.tmtsoftware:csw-client_2.13:"$1" \
    -M client.Main \
    -f -o "$targetDir"/csw-client

  echo "========================================================="
  echo "[INFO] Successfully generated executable at path: [$targetDir]"
  echo "========================================================="
else
  echo "[ERROR] Provide version ID as argument"
fi
