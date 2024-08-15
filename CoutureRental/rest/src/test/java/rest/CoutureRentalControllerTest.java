package rest;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.*;
import core.Dress;
import core.Dresses;
import core.User;
import core.Users;
import persistence.Storage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CoutureRentalControllerTest {
    private CoutureRentalController coutureRentalController;

    @BeforeEach
    void setUp() {
        try {
            coutureRentalController = new CoutureRentalController();
            coutureRentalController.setStorage(new StorageMock());
        } catch (IOException e) {
            fail("Failed to create controller");
        }
    }

    @Test
    @DisplayName("Can get dresses")
    void canGetDresses() {
        try { 
            Dresses dresses = coutureRentalController.getDresses();
            assertNotNull(dresses);
        } catch (IOException e) {
            fail("Failed to getDresses");
        }
    }

    @Test
    @DisplayName("Can get users")
    void canGetUsers() {
        try {
            Users users = coutureRentalController.getUsers();
            assertNotNull(users);
        } catch (IOException e) {
            fail("Failed to getUsers");
        }
    }

    @Test
    @DisplayName("Can add a user")
    void canAddUser() {
        try {
            User user = new User ("testUsername", "testPassword");
            User addedUser = coutureRentalController.addUser(user);
            assertNotNull(addedUser);

            Users users = coutureRentalController.getUsers();
            for (User u : users.getUsers()) {
                System.out.println("DETTE ER BRUKERNE" + u);
            }
            boolean userExists = false;
            for (User u : users.getUsers()) {
                if (u.getUsername().equals("testUsername")) {
                    userExists = true;
                    break;
                } 
            }
            assertTrue(userExists, "The added user exsist in the list of users");
        } catch (IOException e) {
            fail("Failed to addUser");
        }
    }

    @Test
    @DisplayName("Can get dress availability")
    void canGetAvailability() {
        try {
            boolean availability = coutureRentalController.getAvailability("testId");
            assertNotNull(availability);
        } catch (IOException e) {
            fail("Failed to getAvailability");
        }
    }

    @Test
    @DisplayName("Can change dress availability")
    void canChangeAvailability() {
        try {
            coutureRentalController.changeAvailability(("testId"));

            boolean newAvailability = coutureRentalController.getAvailability("testId");
            assertTrue(newAvailability, "The availability of the dress has been changed");
        } catch (IOException e) {
            fail("Failed to changeAvailability");
        }
    }

    // Mock Storage class for testing
    private static class StorageMock extends Storage {
        @Override
        public Dresses loadDresses() {
            // Return some mock dresses for testing
            Dresses dresses = new Dresses();
            dresses.addDress(new Dress("testId", true));
            return dresses;
        }

        @Override
        public Users loadUsers() {
            // Return some mock users for testing
            Users users = new Users();
            users.addIndividual(new User("testUsername", "testPassword"));
            return users;
        }

        @Override
        public void writeDresses(Dresses dresses) {
            // Do nothing for testing
        }

        @Override
        public void writeUsers(Users users) {
            // Do nothing for testing
        }

        @Override
        public Boolean makefileUser(File file) {
            // Do nothing for testing
            return true;
        }

        @Override
        public Boolean makefileDress(File file) {
            // Do nothing for testing
            return true;
        }

        @Override
        public void initializeFile(String filename) {
            // Do nothing for testing
        }
    }
}