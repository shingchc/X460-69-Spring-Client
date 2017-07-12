CLS
SET JAVA_HOME="C:\Program Files\Java\jdk1.7.0_76"
SET SPRING_LIB=.\WebContent\WEB-INF\lib
SET WORKING_AREA=.
SET CLASSPATH=.\classes;"%SPRING_LIB%\*";.\src\resources;.\src\resources\META-INF\spring;
echo %CLASSPATH%

%JAVA_HOME%\bin\java -classpath %CLASSPATH%  com.uciext.springfw.hw.EStoreApp


