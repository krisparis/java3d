set CURR_DIR=%~dp0
cd %CURR_DIR%/../game3d

call mvn compile
call mvn exec:java -Dexec.mainClass="com.krisparis.game3d.App"