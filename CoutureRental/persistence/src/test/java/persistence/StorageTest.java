package persistence;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import core.Dress;
import core.Dresses;
import core.User;
import core.Users;

class StorageTest {

    private Storage storage;
    private Path tempDir;

    @BeforeEach
    void setUp(@TempDir Path tempDir) {
        this.tempDir = tempDir;
        storage = new Storage() {
            @Override
            String buildPath(final String filename) {
                return tempDir.resolve(filename).toString();
            }
        };
    }


    @Test
    void testLoadDressesWhenFileDoesNotExist() {
        assertDoesNotThrow(() -> storage.loadDresses());
    }

    @Test
    void testWriteDresses() throws IOException {
        Dresses dresses = new Dresses();
        Dress dress = new Dress("dress1", true);
        dresses.addDress(dress);
        storage.writeDresses(dresses);
        assertTrue(Files.exists(tempDir.resolve(Storage.getFileNameDress())));
    }

    @Test
    void testLoadUsersWhenFileDoesNotExist() {
        assertDoesNotThrow(() -> storage.loadUsers());
    }

    @Test
    void testWriteUsers() throws IOException {
        Users users = new Users();
        User user = new User("user1", "1234");
        users.addIndividual(user);
        storage.writeUsers(users);
        assertTrue(Files.exists(tempDir.resolve(Storage.getFileNameUser())));
    }

    @Test
    void testMakefileUser() throws IOException {
        File file = tempDir.resolve(Storage.getFileNameUser()).toFile();
        assertTrue(!storage.makefileUser(file));
    }

    @Test
    void testMakefileDress() throws IOException {
        File file = tempDir.resolve(Storage.getFileNameDress()).toFile();
        assertTrue(!storage.makefileDress(file));
    }

    @Test
    void testInitializeFileForDress() {
        storage.initializeFile(Storage.getFileNameDress());
        assertTrue(Files.exists(tempDir.resolve(Storage.getFileNameDress())));
    }

    @Test
    void testInitializeFileForUser() {
        storage.initializeFile(Storage.getFileNameUser());
        assertTrue(Files.exists(tempDir.resolve(Storage.getFileNameUser())));
    }

    @Test
    void testCreateUser() throws IOException {
        User user = new User("newUser", "pass");
        storage.createUser(user);
        Users users = storage.loadUsers();
        assertTrue(users.getUsers().stream().anyMatch(u -> u.getUsername().equals("newUser")));
    }

    @Test
    void testChangeAvailability() throws IOException {
        String dressId = "dress1";
        Dress dress = new Dress(dressId, true);
        Dresses dresses = new Dresses();
        dresses.addDress(dress);
        storage.writeDresses(dresses);
        storage.changeAvailability(dressId);
        assertFalse(storage.getAvailability(dressId));
    }

    @Test
    void testGetAvailability() throws IOException {
        String dressId = "dress1";
        Dress dress = new Dress(dressId, true);
        Dresses dresses = new Dresses();
        dresses.addDress(dress);
        storage.writeDresses(dresses);
        assertTrue(storage.getAvailability(dressId));
    }

        @Test
    void testCreateJacksonModule() {
        SimpleModule module = Storage.createJacksonModule();
        
        assertNotNull(module, "The created module should not be null.");
        // Additional assertions can be added to verify specific configurations 
        // of the CoutureModule, if there are any identifiable characteristics.
    }

    @Test
    void testCreateObjectMapper() {
        ObjectMapper objectMapper = Storage.createObjectMapper();

        assertNotNull(objectMapper, "The created ObjectMapper should not be null.");
    }


    



    // Additional tests for error cases and edge cases would follow...
}

