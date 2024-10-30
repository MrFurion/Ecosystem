@echo off
REM
mvn clean install

REM
java -cp target\Ecosystem-1.0-SNAPSHOT.jar by.trubetski.Main
pause