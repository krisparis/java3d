set CURR_DIR=%~dp0

call mvn install:install-file -Dfile=%CURR_DIR%/../java3d_jars/vecmath-1.5.2.jar -DgroupId=java3d -DartifactId=vecmath -Dversion=1.5.2 -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true;

call mvn install:install-file -Dfile=%CURR_DIR%/../java3d_jars/j3dcore-1.5.2.jar -DgroupId=java3d -DartifactId=j3d-core -Dversion=1.5.2 -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true;

call mvn install:install-file -Dfile=%CURR_DIR%/../java3d_jars/j3dutils-1.5.2.jar -DgroupId=java3d -DartifactId=j3d-core-utils -Dversion=1.5.2 -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true;