package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {

    private Users users;
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User("user1", "pass1");
        user2 = new User("user2", "pass2");
        users = new Users(List.of(user1, user2));
    }

    @Test
    void testDefaultConstructor() {
        Users defaultUsers = new Users();
        assertNotNull(defaultUsers.getUsers());
        assertTrue(defaultUsers.getUsers().isEmpty());
    }

    @Test
    void testConstructorWithList() {
        List<User> userList = users.getUsers();
        assertEquals(2, userList.size());
        assertEquals(user1, userList.get(0));
        assertEquals(user2, userList.get(1));
    }

    @Test
    void testGetUsers() {
        List<User> userList = users.getUsers();
        assertNotSame(userList, users.getUsers());
        assertEquals(2, userList.size());
    }

    @Test
    void testSetUsers() {
        User newUser = new User("user3", "pass3");
        users.setUsers(List.of(newUser));
        List<User> updatedUserList = users.getUsers();
        assertEquals(1, updatedUserList.size());
        assertTrue(updatedUserList.contains(newUser));
    }

        @Test
    void testSetUsersWithEmptyList() {
        users.setUsers(Collections.emptyList());
        List<User> userList = users.getUsers();
        assertNotNull(userList);
        assertTrue(userList.isEmpty());
    }

    @Test
    void testSetUsersWithNull() {
        users.setUsers(null);
        List<User> userList = users.getUsers();
        assertNotNull(userList);
        assertTrue(userList.isEmpty());
    }

    @Test
    void testSetUsersWithMultipleUsers() {
        User user1 = new User("user1", "pass1");
        User user2 = new User("user2", "pass2");
        users.setUsers(Arrays.asList(user1, user2));
        List<User> userList = users.getUsers();
        assertNotNull(userList);
        assertEquals(2, userList.size());
        assertTrue(userList.contains(user1));
        assertTrue(userList.contains(user2));
    }

    @Test
    void testGetIndividual() {
        assertEquals(user1, users.getIndividual("user1"));
        assertNull(users.getIndividual("nonexistent"));
    }

    @Test
    void testAddIndividual() {
        User newUser = new User("user3", "pass3");
        users.addIndividual(newUser);
        List<User> userList = users.getUsers();
        assertEquals(3, userList.size());
        assertTrue(userList.contains(newUser));
    }

    @Test
    void testCheckUsername() {
        assertTrue(users.checkUsername("user1"));
        assertFalse(users.checkUsername("nonexistent"));
    }

    @Test
    void testComparePassword() {
        assertTrue(users.comparePassword("user1", "pass1"));
        assertFalse(users.comparePassword("user1", "wrongpass"));
    }
}


