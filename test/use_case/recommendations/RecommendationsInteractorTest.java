package use_case.recommendations;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the RecommendationsInteractor class.
 */
class RecommendationsInteractorTest {

    private RecommendationsDataAccessInterface recommendationsDAO;
    private RecommendationsOutputBoundary outputBoundary;
    private RecommendationsInteractor interactor;
    private User user;

    /**
     * Sets up the test environment before each test.
     * Initializes the necessary mock objects and the RecommendationsInteractor instance.
     */
    @BeforeEach
    void setUp() {
        recommendationsDAO = mock(RecommendationsDataAccessInterface.class);
        outputBoundary = mock(RecommendationsOutputBoundary.class);
        interactor = new RecommendationsInteractor(recommendationsDAO, outputBoundary);
        user = mock(User.class);
    }

    /**
     * Tests the execute method with valid input.
     * Verifies that the recommendations are shown successfully.
     */
    @Test
    void execute_withValidInput_showsRecommendations() {
        String username = "testUser";
        when(user.getUsername()).thenReturn(username);
        RecommendationsInputData inputData = new RecommendationsInputData(user, 3);
        Map<User, Double> similarUsersMap = Collections.singletonMap(mock(User.class), 0.9);

        when(recommendationsDAO.getNSimilarUsers(user, 3)).thenReturn(similarUsersMap);
        when(recommendationsDAO.AccountExists(username)).thenReturn(true);

        interactor.execute(inputData);

        verify(recommendationsDAO, times(2)).getNSimilarUsers(user, 3);
        verify(recommendationsDAO).AccountExists(username);

        ArgumentCaptor<RecommendationsOutputData> outputDataCaptor = ArgumentCaptor.forClass(RecommendationsOutputData.class);
        verify(outputBoundary).showSuccessScreen(outputDataCaptor.capture());

        RecommendationsOutputData outputData = outputDataCaptor.getValue();
        assertNotNull(outputData);
        assertEquals(similarUsersMap, outputData.getUserSimilarities());
    }
}