// package persistence;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.eq;
// import static org.mockito.Mockito.*;

// import java.io.File;
// import java.nio.file.Path;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.io.TempDir;
// import org.mockito.MockitoAnnotations;

// import core.Dress;
// import core.Dresses;


// public class FileStorageTest {
//     private static final String TEST_FILE_PATH = "test-file.json";


//     private FileStorage fileStorage;

//     /**
//      * Initialize the test environment before each test case.
//      */
//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//         fileStorage = spy(new FileStorage());
        

//     }

//     @Test
//     public void testToggleButton_DressExists() {
//     Dress dress1 = new Dress("dress1", false);
//     Dress dress2 = new Dress("dress2", true);
//     Dress dress3 = new Dress("dress3", true);
//     Dresses testDresses = new Dresses(dress1, dress2, dress3);

//     fileStorage = spy(fileStorage);
//     doReturn(testDresses).when(fileStorage).readJsonFile();

//     Boolean result = fileStorage.toggleButton("dress1");

//     assertTrue(result);

   
//     }



//     /**
//      * Test the Makefile method.
//      */
//     @Test
//     public void TestMakeFile(@TempDir Path tempDir) {
//         File tempFile = new File(tempDir.toFile(), "testfile.txt");

//         fileStorage.makefile(tempFile);

//         assertTrue(tempFile.exists());
//     }

//     /**
//      * Test the readJsonFile method when the file does not exist.
//      */
//     @Test
//     public void testReadJsonFile_FileDoesNotExist(@TempDir Path tempDir) {
//         String filePath = tempDir.resolve("non-existing-file.json").toString();
        
//         fileStorage.setFilePath(filePath);

//         Dresses result = fileStorage.readJsonFile();

//         assertNotNull(result); 
//         assertEquals(3, result.getDresses().length); 

//         File file = new File(filePath);
//         assertTrue(file.exists());
//     }


//     /**
//      * Test the readJsonFile method when the file exists.
//      */
//     @Test
//     public void testReadJsonFile_FileExists() {
//         Dress dress1 = new Dress("dress1", true);
//         Dress dress2 = new Dress("dress2", true);
//         Dress dress3 = new Dress("dress3", true);
//         Dresses testDresses = new Dresses(dress1, dress2, dress3);

//         doReturn(testDresses).when(fileStorage).readJsonFile();

//         Dresses result = fileStorage.readJsonFile();

//         assertNotNull(result);
//         assertEquals(3, result.getDresses().length);
//         assertEquals("dress1", result.getIndividual("dress1").getId());
//     }

//     /**
//      * Test the writeJsonFile method.
//      */
//     @Test
//     public void testWriteJsonFile() {
//         Dress dress1 = new Dress("dress1", true);
//         Dress dress2 = new Dress("dress2", true);
//         Dress dress3 = new Dress("dress3", true);
//         Dresses testDresses = new Dresses(dress1, dress2, dress3);

//         doNothing().when(fileStorage).writeJsonFile(testDresses);

//         assertDoesNotThrow(() -> fileStorage.writeJsonFile(testDresses));
//     }


//     }
