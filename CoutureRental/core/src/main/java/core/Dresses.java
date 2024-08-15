package core;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dresses {
    /**
     * A list of dresses.
     */
    @JsonProperty("dresses")
    private List<Dress> dresses = new ArrayList<>();

    /**
     * Default constructor for the `Dresses` class.
     */
    public Dresses() {
    }

    /**
     * Constructor that initializes the `Dresses` object with a collection of dresses.
     *
     * @param dresses A collection of dresses
     */
    public Dresses(final List<Dress> dresses) {
        this.dresses = new ArrayList<>(dresses);
    }

    /**
     * Get the list of dresses.
     *
     * @return A copy of the list of dresses.
     */
    public List<Dress> getDresses() {
        return new ArrayList<>(dresses);
    }

    /**
     * Set the list of dresses.
     *
     * @param dresses A list of dresses.
     */
    public void setDresses(final List<Dress> dresses) {
        this.dresses = new ArrayList<>(dresses);
    }

    /**
     * Get an individual dress by its ID.
     *
     * @param id The ID of the dress to retrieve.
     * @return The Dress object with the specified ID, or null if not found.
     */
    public Dress getIndividual(final String id) {
        return dresses.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Remove a dress from the list of dresses.
     * @param id
     * @return the list of dresses.
     */
    public List<Dress> removeDress(final String id) {
        dresses.removeIf(dress -> dress.getId().equals(id));
        return dresses;
    }

    /**
     * Add a dress to the list of dresses.
     * @param dress
     */
    public void addDress(final Dress dress) {
        dresses.add(dress);
    }
}