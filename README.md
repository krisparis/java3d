#Project game3d#

##Development platform##

- **OS:** 
	- *name:* windows 7
	- *version:* 6.1
	- *architecture*: amd64
- **JDK:** Java SE 7 Update 60 (64 bits)
- **Maven:** Apache Maven 3.2.1
- **JAVA 3D librairies:** *version*: 1.5.2

##Project architecture##
The structure of the directory `game3d/project/` is described below:

- **/batch:** BATCH files to execute commands useful for this project.
-   **/game3d:** The Maven project
-   **/java3d_jars**: JAVA 3D Jar files
-   **/java3d_dlls**: JAVA 3D Dll files

##Instructions##

1. Ensure the variables `JAVA_HOME` and `M2_HOME` are properly set.
2. Execute the file `/batch/mvn-install-java3d-jars.bat`. Running this executable will install Jar files located in directory `/java3d_jars` into the Maven local repository.
3. Defines the environment variable `JAVA_3D_DLLS` that contains the absolute path to the directory `/java3d_dlls`
4. Add the variable `JAVA_3D_DLLS` to the `PATH` variable.
5. Run the application by executing the file `/batch/mvn-exec-app.bat`.
 




 

