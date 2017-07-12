CLS
SET JAVA_HOME="C:\Program Files\Java\jdk1.7.0_76"
SET SPRING_LIB=.\lib
SET WORKING_AREA=.
SET CLASSPATH=.;"%SPRING_LIB%\*";
echo %CLASSPATH%

REM builds all java files
%JAVA_HOME%\bin\javac -classpath %CLASSPATH% -d .\classes .\src\main\java\com\uciext\springfw\hw\catalogs\dao\*.java .\src\main\java\com\uciext\springfw\hw\catalogs\dao\jdbc\*.java .\src\main\java\com\uciext\springfw\hw\catalogs\model\*.java .\src\main\java\com\uciext\springfw\hw\exception\*.java  .\src\main\java\com\uciext\springfw\hw\common\*.java .\src\main\java\com\uciext\springfw\hw\catalogs\service\*.java .\src\main\java\com\uciext\springfw\hw\catalogs\service\impl\*.java .\src\main\java\com\uciext\springfw\hw\*.java


