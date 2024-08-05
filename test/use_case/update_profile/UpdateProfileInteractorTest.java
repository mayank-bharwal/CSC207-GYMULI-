package use_case.update_profile;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateProfileInteractorTest {

    private UpdateProfileUserDataAccessInterface userDataAccess;
    private UpdateProfileOutputBoundary outputBoundary;
    private UpdateProfileInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccess = mock(UpdateProfileUserDataAccessInterface.class);
        outputBoundary = mock(UpdateProfileOutputBoundary.class);
        interactor = new UpdateProfileInteractor(userDataAccess, outputBoundary);
    }

    @Test
    void execute_emptyFields_showsFailView() {
        UpdateProfileInputData inputData = new UpdateProfileInputData(
                "existingUser", "correctPassword", "", "newPassword",
                "This is a bio", "Computer Science", 25, Arrays.asList("Reading", "Coding")
        );

        User mockUser = mock(User.class);
        when(userDataAccess.userExists("existingUser")).thenReturn(true);
        when(userDataAccess.getUser("existingUser")).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn("correctPassword");

        interactor.execute(inputData);

        verify(outputBoundary).prepareFailView("All fields must be filled.");
        verify(userDataAccess, never()).updateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt(), anyList());
    }

    @Test
    void execute_usernameTaken_showsFailView() {
        UpdateProfileInputData inputData = new UpdateProfileInputData(
                "existingUser", "correctPassword", "takenUsername", "newPassword",
                "This is a bio", "Computer Science", 25, Arrays.asList("Reading", "Coding")
        );

        User mockUser = mock(User.class);
        when(userDataAccess.userExists("existingUser")).thenReturn(true);
        when(userDataAccess.getUser("existingUser")).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn("correctPassword");
        when(userDataAccess.userExists("takenUsername")).thenReturn(true);

        interactor.execute(inputData);

        verify(outputBoundary).prepareFailView("Username taken.");
        verify(userDataAccess, never()).updateUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt(), anyList());
    }

    @Test
    void execute_successfulUpdate_showsSuccessView() {
        UpdateProfileInputData inputData = new UpdateProfileInputData(
                "existingUser", "correctPassword", "newUsername", "newPassword",
                "This is a bio", "Computer Science", 25, Arrays.asList("Reading", "Coding")
        );

        User mockUser = mock(User.class);
        when(userDataAccess.userExists("existingUser")).thenReturn(true);
        when(userDataAccess.getUser("existingUser")).thenReturn(mockUser);
        when(mockUser.getPassword()).thenReturn("correctPassword");
        when(userDataAccess.userExists("newUsername")).thenReturn(false);

        interactor.execute(inputData);

        ArgumentCaptor<UpdateProfileOutputData> outputDataCaptor = ArgumentCaptor.forClass(UpdateProfileOutputData.class);
        verify(outputBoundary).prepareSuccessView(outputDataCaptor.capture());

        UpdateProfileOutputData outputData = outputDataCaptor.getValue();
        assertEquals("newUsername", outputData.getUsername());
        assertEquals("This is a bio", outputData.getBio());
        assertEquals("Computer Science", outputData.getProgramOfStudy());
        assertEquals(25, outputData.getAge());
        assertEquals(Arrays.asList("Reading", "Coding"), outputData.getInterests());
    }
}