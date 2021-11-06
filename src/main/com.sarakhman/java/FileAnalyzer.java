import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAnalyzer {



    public int CountContainsWord(String path, String lookingWord){
        if(lookingWord == null){
            throw new NullPointerException();
        }

        int count =0;
        byte[] content = new byte[10];


        try(FileInputStream fileInputStream = new FileInputStream(path)){

            int fileLength = (int) new File(path).length();
            content = new byte[fileLength];
            int index =0;
            int value;

            while (true){
                value = fileInputStream.read();
                if(value== -1){
                    break;
                }
                content[index] = (byte) value;
                index++;
            }


        }
         catch (IOException e) {
             e.printStackTrace();
        }

            char[] forWorking = new char[content.length];
        for (int i = 0; i < forWorking.length; i++) {
                forWorking[i] = (char) content[i];
        }

        for (int i = 0; i < forWorking.length; i++) {
            if(forWorking[i]==33||forWorking[i]==46||forWorking[i]==44||forWorking[i]==63||forWorking[i]==58||forWorking[i]==59){
                forWorking[i] = 32;
            }
        }
            String[] allStrings = Arrays.toString(forWorking).split(" ");

        for (String allString : allStrings) {
            if (allString.equals(lookingWord)) {
                count++;
            }
        }




        return count;
}


    public String[] allStringsWithWord(String path, String lookingWord){
        if(lookingWord == null){
            throw new NullPointerException();
        }
        String[] result = new String[CountContainsWord(path, lookingWord)];
        byte[] content = new byte[10];

        try(FileInputStream fileInputStream = new FileInputStream(path)) {

            int fileLength = (int) new File(path).length();
            content = new byte[fileLength];
            int index = 0;
            int value;

            while (true) {
                value = fileInputStream.read();
                if (value == -1) {
                    break;
                }
                content[index] = (byte) value;
                index++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        char[] forWorking = new char[content.length];
        for (int i = 0; i < forWorking.length; i++) {
            forWorking[i] = (char) content[i];
        }
        
        StringBuilder stringBuilder= new StringBuilder();
            List resultList = new ArrayList();
            int indexArray =0;
            
            for (int i = 0; i < forWorking.length; i++) {
                if(forWorking[i]==33||forWorking[i]==46||forWorking[i]==63){
                    resultList.add(stringBuilder.toString());
                    
                }
                else {
                    stringBuilder.append(forWorking[i]);
                }
            }
            
            char[] actualString;
            char[] lookingWordChar = lookingWord.toCharArray();
            int index =0;

        for (int i = 0; i < resultList.size(); i++) {       // перебор всех строк

            actualString = resultList.get(i).toString().toCharArray();

            for (int y = 0; y < actualString.length; y++) {         //Проверка есть ли слово в строке
                if(actualString[i]==lookingWordChar[index]){
                    index++;
                }
                else{
                    index = 0;
                }

                if(index == lookingWordChar.length){                // добавения слова
                    result[indexArray] = resultList.get(i).toString();
                    index =0;
                    indexArray++;
                }
            }
        }

            


        return result;
    }



}
