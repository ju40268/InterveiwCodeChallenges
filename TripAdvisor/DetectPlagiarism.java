import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class for iterater to generate all the possible tuples with size n
 */

class NgramIterator implements Iterator<String> {

    String[] words;
    int pos = 0, n;

    public NgramIterator(int n, String str) {
        this.n = n;
        words = str.split(" ");
    }

    public boolean hasNext() {
        return pos < words.length - n + 1;
    }

    public String next() {
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < pos + n; i++)
            sb.append((i > pos ? " " : "") + words[i]);
        pos++;
        return sb.toString();
    }

    public void remove() {
        System.out.print("Not applicable.");
        throw new UnsupportedOperationException();
    }
}


/**
 * Class to including readline from file, parsing Synonyms Map for each line of Synonym file,
 * Finally calculate the whole similarity percent
 */

class DetectPlagiarism {

    private String firstFile;
    private String secondFile;
    private String synFile;
    private float compareResult;
    private int sameCount, totalCount;

    public DetectPlagiarism(String synFile, String firstFile, String secondFile) {
        this.firstFile = firstFile;
        this.secondFile = secondFile;
        this.synFile = synFile;
        this.sameCount = 0;
        this.totalCount = 0;
        this.compareResult = 0;
    }

    /**
     * Method to calculate all the similarity pairs.
     * including
     * 1. mapping to synomous group using union find approach
     * 2. storing into hashmap and looping through all tuples in second file
     * @param firstLine, secondLine from parsing input
     *
     */
    public void calcuateTotal(String firstLine, String secondLine) {

        SimilarityCheck sc = new SimilarityCheck();
        String[][] synGroup = this.generateSynWords(this.synFile);
        List<String[]> firstGram = generateNGrams(firstLine);
        List<String[]> secondGram = generateNGrams(secondLine);
        Set<String> wordSet = new HashSet<>();

        String firstPattern, secondPattern;

        for (String [] s : secondGram) {
            secondPattern = sc.checkSentencesSimilar(s, synGroup).toString();
            wordSet.add(secondPattern);
        }

        for (String[] f : firstGram) {
            firstPattern = sc.checkSentencesSimilar(f, synGroup).toString();
            if (wordSet.contains(firstPattern)) {
                this.sameCount++;
            }
            this.totalCount++;
        }
    }

    /**
     * Method to generate all N tuple pairs and store into List
     * @param line from each read in lines
     * @return List of string arrays parse into set length
     *
     */

    public List<String[]> generateNGrams(String line) {
        NgramIterator ngram = new NgramIterator(3, line);
        List<String[]> gramList = new LinkedList<>();
        while (ngram.hasNext()) {
            String n = ngram.next();
            String[] splited = n.toLowerCase().split("\\s+");
            gramList.add(splited);
        }
        return gramList;
    }


    /**
     * Method to generate all N tuple pairs and store into List
     * @param filename from synomous file
     * @return Array of array for all the Synonym pairs
     * ex. [[dog, cat], [run, jog, sprint]]
     *
     */


    public String[][] generateSynWords(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(this.synFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] lines = content.split("\\r?\\n");

        int maxInLine = 0;
        for (String x : lines) {
            String[] currentLine = x.split("\\s+");
            if (currentLine.length > maxInLine) {
                maxInLine = currentLine.length;
            }
        }
        String[][] finalSynArray = new String[lines.length][maxInLine];
        for (int i = 0; i < lines.length; i++) {
            String[] currentSplit = lines[i].split("\\s+"); // split on whitespace
            for (int j = 0; j < currentSplit.length; j++) {
                finalSynArray[i][j] = currentSplit[j];
            }
        }
        return finalSynArray;
    }

    /**
     * Method to read two files together and find similarity counts
     * @throws IOException
     *
     */
    public void readFile() {

        try {
            BufferedReader firstFileStream = new BufferedReader(new FileReader(this.firstFile));
            BufferedReader secondFileStream = new BufferedReader(new FileReader(this.secondFile));
            String firstLine;
            String secondLine;

            /* Regex for only alphanumeric values */

            while (((firstLine = firstFileStream.readLine()) != null) && ((secondLine = secondFileStream.readLine()) != null)) {
                calcuateTotal(firstLine.replaceAll("[^A-Za-z0-9]", " "), secondLine.replaceAll("[^A-Za-z0-9]", " "));
            }
            this.compareResult = 100 * (float) this.sameCount / this.totalCount;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to printing final calculated results
     * @return final similar rate
     */

    public float getDetectionRate() {
        return this.compareResult;
    }
}