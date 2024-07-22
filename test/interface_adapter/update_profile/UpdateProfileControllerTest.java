package interface_adapter.update_profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.update_profile.UpdateProfileInputBoundary;
import use_case.update_profile.UpdateProfileInputData;
import use_case.update_profile.UpdateProfileOutputBoundary;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * This class tests the functionality of the UpdateProfileController class.
 */
class UpdateProfileControllerTest {

    private UpdateProfileController updateProfileController;
    private UpdateProfileInputBoundary mockUpdateProfileInputBoundary;
    private UpdateProfileOutputBoundary mockUpdateProfileOutputBoundary;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockUpdateProfileInputBoundary = Mockito.mock(UpdateProfileInputBoundary.class);
        mockUpdateProfileOutputBoundary = Mockito.mock(UpdateProfileOutputBoundary.class);
        updateProfileController = new UpdateProfileController(mockUpdateProfileInputBoundary, mockUpdateProfileOutputBoundary);
    }

    /**
     * Tests the update method of UpdateProfileController.
     * It verifies that the method correctly captures the input data and passes it to the input boundary.
     */
    @Test
    void testUpdate() {
        String username = "Jasmine";
        String password = "newPassword";
        String currentUsername = "jasmine";
        String currentPassword = "currentPassword";
        String bio = "(Demo)";
        Integer age = 21;
        String programOfStudy = "Computer Science";
        ArgumentCaptor<UpdateProfileInputData> captor = ArgumentCaptor.forClass(UpdateProfileInputData.class);

        updateProfileController.update(username, password, currentUsername, currentPassword, bio, age, programOfStudy,
                Arrays.asList("Reading", "Running"));

        verify(mockUpdateProfileInputBoundary).execute(captor.capture());
        UpdateProfileInputData capturedInputData = captor.getValue();

        assertEquals(currentUsername, capturedInputData.getCurrentUsername());
        assertEquals(currentPassword, capturedInputData.getCurrentPassword());
        assertEquals(username, capturedInputData.getUsername());
        assertEquals(password, capturedInputData.getPassword());
        assertEquals(bio, capturedInputData.getBio());
        assertEquals(age, capturedInputData.getAge());
        assertEquals(programOfStudy, capturedInputData.getProgramOfStudy());
        assertEquals(Arrays.asList("Reading", "Running"), capturedInputData.getInterests());
    }
}