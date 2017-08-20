1. Run the tests/Build the project:

cd <your_workspace>/BookDb
mvn clean install

It will 
* remove any previous builds, 
* compile the source code, 
* run the unit tests,
* build an executable jar and 
* copy the sample data csv file to the /target dir

In case of any compilation issues, please check that JAVA_HOME is pointing to jdk 1.8

--------------

2. Run the project:
cd target
java -jar C:\Users\Yana\workspace\BookDb\target\BookDb-1.0-SNAPSHOT.jar

--------------

3. Command line interface:
lt <author> - list titles by author
la <title> - list authors by title
a <title> <author> [<co-author> <co-author> <...>] - add book
rt <title> - remove book by title
ra <author> - remove book by author
q - quit
