package interface_adapter.recommendations;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.recommendations.RecommendationsInputBoundary;
import use_case.recommendations.RecommendationsInputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RecommendationsController class.
 */
class RecommendationsControllerTest {

    private RecommendationsController recommendationsController;
    private RecommendationsInputBoundary mockInputBoundary;
    private User mockUser;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        mockInputBoundary = mock(RecommendationsInputBoundary.class);
        recommendationsController = new RecommendationsController(mockInputBoundary);
        mockUser = mock(User.class);
    }

    /**
     * Tests the execute method.
     * Verifies that the correct input data is passed to the input boundary.
     */
    @Test
    void execute() {
        int numberOfRecommendations = 3;

        ArgumentCaptor<RecommendationsInputData> captor = ArgumentCaptor.forClass(RecommendationsInputData.class);

        recommendationsController.execute(mockUser, numberOfRecommendations);

        verify(mockInputBoundary).execute(captor.capture());

        RecommendationsInputData capturedData = captor.getValue();
        assertEquals(mockUser, capturedData.getUser());
        assertEquals(numberOfRecommendations, capturedData.getNumberOfRecommendations());
    }
}
