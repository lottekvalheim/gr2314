package core;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {
    /**
     * A list of users.
     */
    @JsonProperty("users")
    private List<User> users = new ArrayList<>();

    /**
     * Default constructor for the `Users` class.
     */
    public Users() {
    }

    /**
     * Constructor that initializes the `User` object with a list of users.
     *
     * @param users A list of users
     */
    @JsonCreator
    public Users(@JsonProperty("users") final List<User> users) {
        this.users = new ArrayList<>(users);
    }

    /**
     * Get the list of users.
     *
     * @return A copy of the list of users.
     */
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Set the list of users.
     *
     * @param users A list of users.
     */
    public void setUsers(final List<User> users) {
        this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();
    }

    /**
     * Get an individual user by its username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object with the specified username, or null if not found.
     */
    public User getIndividual(final String username) {
        return users.stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
    }

    /**
     * Adds a new user to the list of users.
     * @param user
     */
    public void addIndividual(final User user) {
        users.add(user);
    }

    /**
     * Checks if username already exists.
     * @param username
     * @return true if username exists, false if not
     */
    public boolean checkUsername(final String username) {
        return getIndividual(username) != null;
    }

    /**
     * Compares the password for a given username.
     * @param username
     * @param password
     * @return true if password matches, false if not
     */
    public boolean comparePassword(final String username, final String password) {
        User user = getIndividual(username);
        System.out.println(user.getPassword());
        System.out.println(password);
        return (password.equals(user.getPassword()));
    }

}

