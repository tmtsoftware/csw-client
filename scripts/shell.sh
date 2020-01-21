#!/usr/bin/env bash

scriptsDir="$(
  cd "$(dirname "$0")" || exit
  pwd -P
)"

rootDir="$(dirname "$scriptsDir")"
targetDir=$rootDir/target/coursier/stage/"$1"/
executable=$targetDir/csw-shell
mainClass="shell.Main"
shellDep="com.github.tmtsoftware:csw-shell_2.13"
shellVersion="master-SNAPSHOT"

if [ "$#" -ne 0 ]; then
  shellVersion=$1
fi

coursierExecutable="$scriptsDir"/coursier
if [[ ! -f "$coursierExecutable" ]] && hash coursier 2>/dev/null; then
  coursierExecutable=coursier
fi

mkdir -p "$targetDir"

$coursierExecutable bootstrap -r jitpack $shellDep:"$shellVersion" -M $mainClass -f -o "$executable"

echo "========================================================="
echo "[INFO] Successfully generated executable at path: [$targetDir]"
echo "========================================================="

echo "[INFO] Starting shell with version $shellVersion ..."
echo "========================================================="
$executable
