#!/usr/bin/env bash

scriptsDir="$(
  cd "$(dirname "$0")" || exit
  pwd -P
)"

rootDir="$(dirname "$scriptsDir")"
coursierExecutable="$scriptsDir"/coursier
targetDir=$rootDir/target/coursier/stage/"$1"/
executable=$targetDir/csw-client
mainClass="client.Main"
cswClientDep="com.github.tmtsoftware:csw-client_2.13"

if [ "$#" -ne 0 ]; then
  mkdir -p "$targetDir"

  $coursierExecutable bootstrap -r jitpack $cswClientDep:"$1" -M $mainClass -f -o "$executable"

  echo "========================================================="
  echo "[INFO] Successfully generated executable at path: [$targetDir]"
  echo "========================================================="

  echo "[INFO] Starting client ..."
  echo "========================================================="
  $executable
else
  echo "[ERROR] Provide version ID as argument"
fi
