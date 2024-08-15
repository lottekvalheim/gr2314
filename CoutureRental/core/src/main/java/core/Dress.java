package core;

public class Dress {
    /**
     * The unique identifier for the dress.
    */
    private String id;

    /**
     * Indicates the availability status of the dress.
     * <p>It is set to true if the dress is available, and false if it is not available.</p>
     */
    private Boolean isAvailable;

    /**
     * Constructs a `Dress` object with the specified ID and availability status.
     *
     * @param id           The unique identifier for the dress.
     * @param isAvailable  The availability status of the dress (true if available, false otherwise).
     */
    public Dress(final String id, final Boolean isAvailable) {
        this.id = id;
        this.isAvailable = isAvailable;
    }

    /**
     * Get the unique identifier of the dress.
     *
     * @return The ID of the dress.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the availability status of the dress.
     *
     * @return True if the dress is available; false otherwise.
     */
    public Boolean getIsAvailable() {
        return isAvailable;
    }

    /**
     * Set the availability status of the dress.
     *
     */
    public void changeAvailability() {
        this.isAvailable = !isAvailable;
    }

    /**
     * Set the unique identifier of the dress.
     *
     * @param id The ID of the dress.
     */
    public void setId(final String id) {
        this.id = id;
    }
}