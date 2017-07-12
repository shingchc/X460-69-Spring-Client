CLS
SET JAVA_HOME="C:\Program Files\Java\jdk1.7.0_76"
SET SPRING_LIB=.\lib
SET WORKING_AREA=.
SET CLASSPATH=.\classes;"%SPRING_LIB%\*";.\src\main\resources;.\src\main\resources\META-INF\spring;
echo %CLASSPATH%

%JAVA_HOME%\bin\java -classpath %CLASSPATH%  com.uciext.springfw.hw.EStoreApp


