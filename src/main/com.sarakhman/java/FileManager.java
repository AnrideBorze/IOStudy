//Пишем class FileManager с методами
//public class FileManager {
//    // public static int countFiles(String path) - принимает путь к папке,
//// возвращает количество файлов в папке и всех подпапках по пути
//    public static int countFiles(String path) {
//        return 0;
//    }
//
//    // public static int countDirs(String path) - принимает путь к папке,
//// возвращает количество папок в папке и всех подпапках по пути
//    public static int countDirs(String path) {
//        return 0;
//    }
//}
//
//    public static void copy(String from, String to) - метод по копированию папок и файлов.
//        Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
//public static void move(String from, String to) - метод по перемещению папок и файлов.
//        Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.

import java.io.*;

public class FileManager {
    public static int countFiles(String path) throws IOException {
        int result = 0;
        File file = new File(path);
        if(!file.exists()){
            throw new IOException();
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File currentFile : files) {
                if (!currentFile.isDirectory()) {
                    result++;
                } else {
                    result += countFiles(currentFile.getPath());
                }
            }
        }
        return result;
    }


    public static int countDirs(String path) throws IOException {
        int result = 0;
        File file = new File(path);
        if(!file.exists()){
            throw new IOException();
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File currentFile : files) {
                if (currentFile.isDirectory()) {
                    result++;
                    result += countDirs(currentFile.getPath());
                }
            }
        }
        return result;
    }

    public void copy(String from, String to) throws IOException {
        File fromFile = new File(from);
        File toFile = new File(to);

        if (!checkFile(fromFile, toFile)) {
            throw new IOException();
        } else {
            if (fromFile.isFile()) {
                copyFile(fromFile, toFile);
            } else {
                copyDir(fromFile,toFile);
            }
        }


    }


    public void move(String from, String to) throws IOException {
        copy(from, to);
        clearDir(from);

    }

    public void clearDir(String path) {
        File currentFile = new File(path);
        File[] files = currentFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                } else {
                    file.deleteOnExit();
                }
                currentFile.delete();
            }
        }
    }

    private void copyFile(File from, File to) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(from);
             FileOutputStream outputStream = new FileOutputStream(to)) {
            inputStream.transferTo(outputStream);
        }
    }

    private void copyDir(File from, File to) throws IOException {
        if(!to.exists()){
            to.mkdir();
        }

        String[] allFiles = from.list();
        if(allFiles!=null){
            for (String currentFileName : allFiles) {
                File startFile = new File(currentFileName);
                File finishName = new File(currentFileName);
                copy(startFile.getPath(),finishName.getPath());
            }
        }

    }

    private boolean checkFile(File from, File to) {

        if (!from.exists() || to.isFile()) {
            return false;
        } else {
            return true;
        }

    }

}



