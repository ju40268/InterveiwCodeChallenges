import org.junit.Test;

import static org.junit.Assert.*;

public class DetectPlagiarismTest {
    String synFile = System.getProperty("user.dir") + "/src/syns.txt";
    String firstFile = System.getProperty("user.dir") + "/src/file1.txt";
    String secondFile = System.getProperty("user.dir") + "/src/file2.txt";
    DetectPlagiarism dp = new DetectPlagiarism(synFile, firstFile, secondFile);

    @Test
    public void getDetectionRate() throws Exception {
        float rate = dp.getDetectionRate();
        dp.readFile();
        assertEquals(0.75, rate, 0.001);
    }

}