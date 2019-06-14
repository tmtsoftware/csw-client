#!/usr/bin/env bash

if [ "$#" -ne 0 ]
then
./coursier launch -r jitpack com.github.tmtsoftware.csw-client:csw-client_2.12:$1 -M csw.client.Main -- "${@:2}"
fi
