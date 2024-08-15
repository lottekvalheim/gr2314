package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class DressesTest {

    private Dresses dresses = new Dresses();

    /**
     * Set up a new Dresses object with some sample dresses before each test.
     */
    @BeforeEach
    void setUp() {
        
        Dress dress1 = new Dress("dress1", true);
        Dress dress2 = new Dress("dress5", false);
        Dress dress3 = new Dress("dress10", true);
        dresses.addDress(dress1);
        dresses.addDress(dress2);
        dresses.addDress(dress3);
    }

    /**
     * Test the getDresses() method.
     * Checks if the getDresses method returns the correct number of dresses
     */
    @Test
    void testGetDresses() {
        List<Dress> dressList = dresses.getDresses();
        assertEquals(3, dressList.size());
    }

    /**
     * Test the getIndividual(String) method.
     * Checks if the getIndividual method returns the correct dress based on ID
     */
    @Test
    void testGetIndividual() {
        Dress dress = dresses.getIndividual("dress1");
        assertNotNull(dress);
        assertEquals("dress1", dress.getId());
    }

    /**
     * Test the setIsAvailable(String) method.
     * Checks if the setIsAvailable method changes availability correctly
     */
    @Test
    void testSetIsAvailable() {
        Dress dress = dresses.getIndividual("dress1");
        assertTrue(dress.getIsAvailable());

        dress.changeAvailability(); // Assuming there is a method that changes the availability
        assertFalse(dress.getIsAvailable());
    }

        /**
     * Test the Dresses(List<Dress> dresses) constructor.
     */
    @Test
    void testDressesConstructorWithList() {
        Dress dress1 = new Dress("dress1", true);
        Dress dress2 = new Dress("dress2", true);
        Dresses newDresses = new Dresses(List.of(dress1, dress2));
        assertEquals(2, newDresses.getDresses().size());
    }

    /**
     * Test the setDresses(List<Dress> dresses) method.
     */
    @Test
    void testSetDresses() {
        Dress newDress = new Dress("dressNew", true);
        dresses.setDresses(List.of(newDress));
        List<Dress> dressList = dresses.getDresses();
        assertEquals(1, dressList.size());
        assertEquals("dressNew", dressList.get(0).getId());
    }

    /**
     * Test the removeDress(String id) method.
     */
    @Test
    void testRemoveDress() {
        assertEquals(3, dresses.getDresses().size());
        dresses.removeDress("dress1");
        assertEquals(2, dresses.getDresses().size());
        assertNull(dresses.getIndividual("dress1"));
    }

    /**
     * Test the addDress(Dress dress) method.
     */
    @Test
    void testAddDress() {
        Dress newDress = new Dress("dressNew", true);
        dresses.addDress(newDress);
        assertEquals(4, dresses.getDresses().size());
        assertNotNull(dresses.getIndividual("dressNew"));
    }
}
