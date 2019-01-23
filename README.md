# csw-client

This project implements interactive shell which can be used to communicate with an HCD (Hardware Control Daemon) and an Assembly using 
TMT Common Software ([CSW](https://github.com/tmtsoftware/csw)) APIs. 

## Build Instructions

The build is based on sbt and depends on libraries generated from the 
[csw](https://github.com/tmtsoftware/csw) project.

See [here](https://www.scala-sbt.org/1.0/docs/Setup.html) for instructions on installing sbt.

## Prerequisites for running Components

The CSW services need to be running before starting the components. 
This is done by starting the `csw-services.sh` script, which is installed as part of the csw build.
If you are not building csw from the sources, you can get the script as follows:

 - Download csw-apps zip from https://github.com/tmtsoftware/csw/releases.
 - Unzip the downloaded zip.
 - Go to the bin directory where you will find `csw-services.sh` script.
 - Run `./csw_services.sh --help` to get more information.
 - Run `./csw_services.sh start` to start the location service and config server.

## Building the interactive shell as an Application

 - Run `sbt csw-client-deploy/universal:packageBin`, this will create self contained zip in `csw-client-deploy/target/universal` directory
 - Unzip the generated zip and cd into the bin directory

Note: An alternative method is to run `sbt stage`, which installs the applications locally in ./target/universal/stage/bin