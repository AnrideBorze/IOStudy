import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ITestFileManager {
    final static String RESOURCES = "src/test/com.sarakhman/resources/";
    FileManager fileManager = new FileManager();


    @ParameterizedTest
    @CsvSource({"dir 01, 0", "dir 06, 1", "dir 08, 2", "dir 09, 5"})
    public void testCountDirsWorkCorrectlyWithValidPath(String dir, int expectedDirsCount) throws IOException {
        String path = RESOURCES + dir;

        assertEquals(expectedDirsCount, fileManager.countDirs(path));
    }

    @ParameterizedTest
    @CsvSource({"dir 01, 0", "dir 02, 2", "dir 03, 2", "dir 04, 3", "dir 05, 0", "dir 06, 0", "dir 07, 0", "dir 08, 3", "dir 09, 2", "dir 10, 5"})
    public void testCountFilesWorkCorrectlyWithValidPath(String dir, int expectedFilesCount) throws IOException {
        String path = RESOURCES + dir;

        assertEquals(expectedFilesCount, fileManager.countFiles(path));
    }


    @ParameterizedTest
    @CsvSource({"dir 01, dir 007", "dir 03, dir 007", "dir 04, dir 007", "dir 09, dir 007", "dir 10, dir 007"})
    public void testMoveMovedTheSameCountDirsAndFiles(String from, String to) throws IOException {
        int countDirsBefore = fileManager.countDirs(RESOURCES + from);
        int countFilesBefore = fileManager.countFiles(RESOURCES + from);

        fileManager.move(RESOURCES + from, RESOURCES + to);
        assertEquals(0, fileManager.countFiles(RESOURCES + from));

        int countDirsAfter = fileManager.countDirs(RESOURCES + to);
        int countFilesAfter = fileManager.countFiles(RESOURCES + to);
        assertEquals(countDirsBefore,countDirsAfter);
        assertEquals(countFilesBefore,countFilesAfter);


        fileManager.move(RESOURCES + to,RESOURCES+from);


    }

    @ParameterizedTest
    @CsvSource({"dir 01, dir 007", "dir 03, dir 007", "dir 04, dir 007", "dir 09, dir 007", "dir 10, dir 007"})
    public void testCopyMakeCopyTheSameCountDirsAndFiles(String from, String to) throws IOException {
        fileManager.copy(RESOURCES + from, RESOURCES + to);
        int countDirsBefore = fileManager.countDirs(RESOURCES + from);
        int countFilesBefore = fileManager.countFiles(RESOURCES + from);
        int countDirsAfter = fileManager.countDirs(RESOURCES + to);
        int countFilesAfter = fileManager.countFiles(RESOURCES + to);
        assertEquals(countDirsBefore,countDirsAfter);
        assertEquals(countFilesBefore,countFilesAfter);
    }

    @Test
    public void testCountDirsThrowFileNotFoundExceptionWhenPathIsNotValid(){
        Assertions.assertThrows(IOException.class, () ->{
            fileManager.countDirs(RESOURCES + "dir999");
        }
        );
    }
    @Test
    public void testCountFilesThrowFileNotFoundExceptionWhenPathIsNotValid(){
        Assertions.assertThrows(IOException.class, () ->{
                    fileManager.countFiles(RESOURCES + "dir999");
                }
        );
    }

    @Test
    public void testMoveThrowFileNotFoundExceptionWhenPathFromIsNotValid(){
        Assertions.assertThrows(IOException.class, () ->{
                    fileManager.move(RESOURCES + "dir 999", RESOURCES);
                }
        );
    }

    @Test
    public void testCopyThrowFileNotFoundExceptionWhenPathFromIsNotValid(){
        Assertions.assertThrows(IOException.class, () ->{
                    fileManager.copy(RESOURCES + "dir 999", RESOURCES);
                }
        );
    }

}
