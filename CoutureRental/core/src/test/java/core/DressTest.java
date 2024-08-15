package core;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DressTest {




    private Dress dress;

    @BeforeEach
    void setUp() {
        dress = new Dress("dress10", true);
    }

    @Test
    void testGetId() {
        assertEquals("dress10", dress.getId());
    }

    @Test
    void testGetIsAvailable() {
        assertTrue(dress.getIsAvailable());
    }

    @Test
    void testSetIsAvailable() {
        dress.changeAvailability();
        assertFalse(dress.getIsAvailable());
    }

    @Test
    void testChangeAvailabilityFromTrueToFalse() {
        assertTrue(dress.getIsAvailable()); 
        dress.changeAvailability(); 
        assertFalse(dress.getIsAvailable()); 
    }

    @Test
    void testChangeAvailabilityFromFalseToTrue() {
        dress.changeAvailability();
        assertFalse(dress.getIsAvailable()); 
        dress.changeAvailability(); 
        assertTrue(dress.getIsAvailable()); 
    }

    @Test
    void testRepeatedChangeAvailability() {
        boolean originalAvailability = dress.getIsAvailable();
        dress.changeAvailability(); 
        dress.changeAvailability(); 
        assertEquals(originalAvailability, dress.getIsAvailable());
    }

   
    @Test
    void testSetId() {
        dress.setId("dress20");
        assertEquals("dress20", dress.getId());
    }




}
