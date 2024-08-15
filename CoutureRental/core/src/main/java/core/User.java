package core;

public class User {

    /**
     * Username of user.
     */
    private String username;

    /**
     * Password of user.
     */
    private String password;

    /**
     * Constructor for User object.
     * @param username
     * @param password
     */
    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Sets the username for user.
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Sets the password for user.
     * @param password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets the username for user.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the password for user.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns a string representation of the User object. The string includes the
     * username and password of the user.
     *
     * @return A string containing the username and password of the user.
     */
    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + "]";
    }
}
