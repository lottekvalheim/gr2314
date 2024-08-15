package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "testPass");
    }

    @Test
    void testGetUsername() {
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void testGetPassword() {
        assertEquals("testPass", user.getPassword());
    }

    @Test
    void testSetUsername() {
        user.setUsername("newTestUser");
        assertEquals("newTestUser", user.getUsername());
    }

    @Test
    void testSetPassword() {
        user.setPassword("newTestPass");
        assertEquals("newTestPass", user.getPassword());
    }

    @Test
    void testToString() {
        String expectedString = "User [username=testUser, password=testPass]";
        assertEquals(expectedString, user.toString());
    }
}
