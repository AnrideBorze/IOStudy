import java.io.*;
import java.util.ArrayList;

public class FileAnalyzer {


    public int countContainsWord(String path, String lookingWord) throws IOException {
        if (lookingWord == null) {
            throw new NullPointerException();
        }

        String content = readByPath(path);

        String[] allStrings = convertStringToStringArray(content);

        int count = 0;

        for (String allString : allStrings) {
            if (allString.equals(lookingWord)) {
                count++;
            }
        }


        return count;
    }


    public ArrayList <String> allStringsWithWord(String path, String lookingWord) throws IOException {
        if (lookingWord == null) {
            throw new NullPointerException();
        }
        String content = readByPath(path);

        ArrayList <String> resultList = convertStringToList(content);

        ArrayList <String> result = checkingListByWord(resultList,path,lookingWord);


        return result;
    }

    public ArrayList<String> convertStringToList(String forWork){
        char[] forWorking = forWork.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();
        ArrayList resultList = new ArrayList();

        for (int i = 0; i < forWorking.length; i++) {
            if (forWorking[i] == 33 || forWorking[i] == 46 || forWorking[i] == 63) {
                stringBuilder.append(forWorking[i]);
                resultList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();

            } else {
                stringBuilder.append(forWorking[i]);

            }
        }
        return resultList;
    }


   public String[] convertStringToStringArray(String text) {

        String[] result = text.split(" ");


        return result;
   }




   public String readByPath(String path) throws IOException {
        byte[] result;

        FileInputStream fileInputStream = new FileInputStream(path);

        int fileLength = (int) new File(path).length();
        result = new byte[fileLength];

        fileInputStream.read(result);
        fileInputStream.close();


        return new String(result);

    }


   public ArrayList <String>checkingListByWord(ArrayList<String> list, String path, String lookingWord) throws IOException{

        ArrayList <String> result = new ArrayList<>();



       for (String s : list) {
           if(s.contains(lookingWord)){
               result.add(s);
           }
       }
       return result;
   }

}
