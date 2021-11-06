import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestFileAnalyzer {
    private FileAnalyzer fileAnalyzer;


    public void createFileAnalyzer(){
         fileAnalyzer= new FileAnalyzer();
         try(FileOutputStream file = new FileOutputStream("test1.txt");
             FileOutputStream file2 = new FileOutputStream("test2.txt");
             FileOutputStream file3 = new FileOutputStream("test.txt");) {
             String toText = "Hello Java!";
             byte[] forWriting = toText.getBytes();
             file.write(forWriting);
             String toText2 = "Hello Java! Here we will say Java two times. Good luck.";
             byte[] forWriting2 = toText2.getBytes();
             file2.write(forWriting2);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }


    }

    @Test
    public void TestCountContainsWordReturnZero(){
        createFileAnalyzer();
        assertEquals(0,fileAnalyzer.CountContainsWord("D:/JavaProjects/test.txt", "Java") );
    }

    @Test
    public void TestCountContainsWordThrowNullException(){
        createFileAnalyzer();
        assertThrows(NullPointerException.class, ()-> {
            fileAnalyzer.allStringsWithWord("test.txt", null);
        } );
    }

    @Test
    public void TestCountContainsWordReturnNumber(){
        createFileAnalyzer();
        assertEquals(1,fileAnalyzer.CountContainsWord("D:/JavaProjects/test1.txt", "Java") );
    }

    @Test
    public void TestAllStringsWithWordReturnEmptyArray(){
        createFileAnalyzer();
        assertEquals(0,fileAnalyzer.allStringsWithWord("D:/JavaProjects/test.txt", "Java").length);


    }

    @Test
    public void TestAllStringsWithWordThrowNullException(){
        createFileAnalyzer();
        assertThrows(NullPointerException.class, ()-> {
            fileAnalyzer.allStringsWithWord("D:/JavaProjects/test.txt", null);
        } );
    }

    @Test
    public void TestAllStringsWithWordReturnAllStrings(){
        createFileAnalyzer();
        assertEquals("Hello Java!",fileAnalyzer.allStringsWithWord("D:/JavaProjects/test2.txt", "Java")[0]);
        assertEquals("Here we will say Java two times.",fileAnalyzer.allStringsWithWord("D:/JavaProjects/test2.txt", "Java")[1]);

    }


}
