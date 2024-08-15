package rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(classes = {CoutureRentalApplication.class})

public class ContextTest {
    private CoutureRentalController coutureRentalController;
    
    @BeforeEach
    void setUp() {
        try {
            coutureRentalController = new CoutureRentalController();
        } catch (IOException e) {
            fail("Failed to create controller");
        }
    }

    @Test
    @DisplayName("Test if controller is created")
    void loadContext() {
        Assertions.assertNotNull(coutureRentalController);
    }
}
