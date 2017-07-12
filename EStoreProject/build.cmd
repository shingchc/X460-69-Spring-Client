CLS
SET JAVA_HOME="C:\Program Files\Java\jdk1.7.0_76"
SET SPRING_LIB=.\WebContent\WEB-INF\lib
SET WORKING_AREA=.
SET CLASSPATH=.;"%SPRING_LIB%\*";
echo %CLASSPATH%

REM builds all java files
%JAVA_HOME%\bin\javac -classpath %CLASSPATH% -d .\classes .\src\com\uciext\springfw\hw\users\model\*.java .\src\com\uciext\springfw\hw\users\service\impl\*.java .\src\com\uciext\springfw\hw\users\service\*.java .\src\com\uciext\springfw\hw\users\model\*.java .\src\com\uciext\springfw\hw\users\dao\jdbc\*.java .\src\com\uciext\springfw\hw\users\dao\*.java .\src\com\uciext\springfw\hw\catalogs\dao\*.java .\src\com\uciext\springfw\hw\catalogs\dao\jdbc\*.java .\src\com\uciext\springfw\hw\catalogs\model\*.java .\src\com\uciext\springfw\hw\exception\*.java  .\src\com\uciext\springfw\hw\common\*.java .\src\com\uciext\springfw\hw\catalogs\service\*.java .\src\com\uciext\springfw\hw\catalogs\service\impl\*.java .\src\com\uciext\springfw\hw\*.java


