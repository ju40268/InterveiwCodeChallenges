# Plagiarism Detector

# Project Objective

Implement a plagiarism detection algorithm using a N-tuple comparison allowing for synonyms.

# Parameters
3 required arguments, 1 optional argument.

+ File name for a list of synonyms
+ First File Name
+ Second File Name
+ N-Gram Size [Default = 3]

Input file may contain punctuations and special characters.
Same words with different upper case, lower case will be classified in same group. (ex, Dog, dog, dOg are consider the same)
Sample input file are included. (file1.txt, file2.txt, syns.txt)

# Algorithm Overview
Include Rabin-Karp Search algorithm for fast string pattern searching. [Rabin-Karp Search] (https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm)

Include Union Find data structure for collecting similar words groups. (ex. run, jog, sprint -> same group with same parent.)

# Output
Program outputs the percent of tuples in file1 which appear in file2.

# Dependencies :
[Maven] (https://maven.apache.org/download.cgi "Maven Build")

[Java 7] (http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html "JDK 7")

# Usage
Before each build
```
mvn clean package
```
Compile and execute

+ Make sure the files are under whole project folder. Same level with Main.java.
```
~$ javac Main.java
~$ java Main -f1 file1.txt -f2 file2.txt -s syns.txt
```
Sample Command Line arguments
```
java -jar $JAR.jar $FIRST_FILE $SECOND_FILE $SYN_FILE $NUM_TUPLE
```
## License
Chia-Ju, Chen [2018]