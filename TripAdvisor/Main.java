import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/* ***************************************
*
*  Plagiarism Detection
*
* ****************************************/

public class Main {
    public static void main(String[] args) {
        ParseInput pi = new ParseInput(args);
        DetectPlagiarism dp = new DetectPlagiarism(pi.getSynFile(), pi.getFirstFile(), pi.getSecondFile());
        dp.readFile();
        float similarityRate = dp.getDetectionRate();
        System.out.printf("Total Same Percentage:  %.3f%%" , similarityRate);
    }
}