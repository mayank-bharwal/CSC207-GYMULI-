package use_case.update_profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateProfileOutputDataTest {

    private UpdateProfileOutputData outputData;

    @BeforeEach
    void setUp() {
        outputData = new UpdateProfileOutputData(
                "testUsername", "testPassword", "This is a bio", "Computer Science",
                25, Arrays.asList("Reading", "Coding"), false
        );
    }

    /**
     * Test the getUsername method.
     */
    @Test
    void getUsername() {
        assertEquals("testUsername", outputData.getUsername());
    }

    /**
     * Test the getAge method.
     */
    @Test
    void getAge() {
        assertEquals(25, outputData.getAge());
    }

    /**
     * Test the getBio method.
     */
    @Test
    void getBio() {
        assertEquals("This is a bio", outputData.getBio());
    }

    /**
     * Test the getProgramOfStudy method.
     */
    @Test
    void getProgramOfStudy() {
        assertEquals("Computer Science", outputData.getProgramOfStudy());
    }

    /**
     * Test the getInterests method.
     */
    @Test
    void getInterests() {
        List<String> expectedInterests = Arrays.asList("Reading", "Coding");
        assertEquals(expectedInterests, outputData.getInterests());
    }

    /**
     * Test the isUseCaseFailed method.
     */
    @Test
    void isUseCaseFailed() {
        assertFalse(outputData.isUseCaseFailed());
    }

    /**
     * Test the setUseCaseFailed method.
     */
    @Test
    void setUseCaseFailed() {
        outputData.setUseCaseFailed(true);
        assertTrue(outputData.isUseCaseFailed());
    }
}