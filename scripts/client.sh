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
clientDep="com.github.tmtsoftware:csw-client_2.13"
clientVersion="master-SNAPSHOT"

if [ "$#" -ne 0 ]; then
  clientVersion=$1
fi

mkdir -p "$targetDir"

$coursierExecutable bootstrap -r jitpack $clientDep:"$clientVersion" -M $mainClass -f -o "$executable"

echo "========================================================="
echo "[INFO] Successfully generated executable at path: [$targetDir]"
echo "========================================================="

echo "[INFO] Starting client with version $clientVersion ..."
echo "========================================================="
$executable
