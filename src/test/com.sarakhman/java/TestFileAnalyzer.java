import org.junit.Test;


import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestFileAnalyzer {
    private FileAnalyzer fileAnalyzer;


    public void createFileAnalyzer(){
         fileAnalyzer = new FileAnalyzer();
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
    public void TestCheckingListByWord() throws IOException {
        createFileAnalyzer();
        String actualString = fileAnalyzer.readByPath("test2.txt");
        ArrayList actualList = fileAnalyzer.convertStringToList(actualString);
        String[] expected = new String[] {"Hello Java!", " Here we will say Java two times."};
        ArrayList <String> actual = fileAnalyzer.checkingListByWord(actualList, "D:/JavaProjects/test2.txt", "Java");
        assertEquals(expected[0],actual.get(0));
        assertEquals(expected[1],actual.get(1));

    }


    @Test
    public void TestReadByPath() throws IOException {
        createFileAnalyzer();
        String actual = fileAnalyzer.readByPath("test1.txt");
        String expected = "Hello Java!";
        assertEquals(expected,actual);
    }

    @Test
    public void TestConvertStringToStringArray() throws IOException {
        createFileAnalyzer();
        String text = fileAnalyzer.readByPath("test2.txt");
        ArrayList actual = fileAnalyzer.convertStringToList(text);
        ArrayList expected = new ArrayList ();
        expected.add("Hello Java!");
        expected.add(" Here we will say Java two times.");
        expected.add(" Good luck.");

        assertEquals(expected.get(0),actual.get(0));
        assertEquals(expected.get(1),actual.get(1));
        assertEquals(expected.get(2),actual.get(2));


    }

    @Test
    public void TestConvertStringToList() throws IOException {
        createFileAnalyzer();
        String text = fileAnalyzer.readByPath("test2.txt");
        String[] actual = fileAnalyzer.convertStringToStringArray(text);
        String[] expected = new String[] {"Hello", "Java!"};
        assertEquals(actual[0],expected[0]);
        assertEquals(actual[1],expected[1]);

    }


    @Test
    public void TestCountContainsWordThrowNullException(){
        createFileAnalyzer();
        assertThrows(NullPointerException.class, ()-> {
            fileAnalyzer.allStringsWithWord("test.txt", null);
        } );
    }

    @Test
    public void TestCountContainsWordReturnNumber() throws IOException {
        createFileAnalyzer();
        assertEquals(1,fileAnalyzer.countContainsWord("D:/JavaProjects/test1.txt", "Java!") );
    }

    @Test
    public void TestAllStringsWithWordReturnEmptyArray() throws IOException {
        createFileAnalyzer();
        assertEquals(0,fileAnalyzer.allStringsWithWord("D:/JavaProjects/test.txt", "Java").size());


    }

    @Test
    public void TestAllStringsWithWordThrowNullException(){
        createFileAnalyzer();
        assertThrows(NullPointerException.class, ()-> {
            fileAnalyzer.allStringsWithWord("D:/JavaProjects/test.txt", null);
        } );
    }

    @Test
    public void TestAllStringsWithWordReturnAllStrings() throws IOException {
        createFileAnalyzer();
        assertEquals("Hello Java!",fileAnalyzer.allStringsWithWord("D:/JavaProjects/test2.txt", "Java").get(0));
        assertEquals(" Here we will say Java two times.",fileAnalyzer.allStringsWithWord("D:/JavaProjects/test2.txt", "Java").get(1));

    }


}
