Create a new project Registration
Import folders: lib, src, config, 
Go to Project Properties >> Java Build Path >> Libraries >> add jars > add jars from the lib folder


This readme file describes the Environment Variables
utilized for both "build.cmd" and "run.cmd" along 
with some general notes

General notes:
All the code is compiled using JDK 1.6

Environment Variables:
----------------------
JAVA_HOME points to install directory for JDK. 

SPRING_LIB points to the necessary Spring Framework 
libraries needed for successful build.
Please note, there is no need to list every single JAR
file since JDK 1.6 does not require it. You need to list
every JAR file if you decide to use earlier version of JDK

WORKING_AREA points to current directory where build.cmd
is located

CLASSPATH specify location of all the necessary resources

