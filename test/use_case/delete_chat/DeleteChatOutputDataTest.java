package use_case.delete_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DeleteChatOutputData.
 */
class DeleteChatOutputDataTest {

    private DeleteChatOutputData deleteChatOutputData;
    private final String chatName = "TestChat";
    private final List<String> members = new ArrayList<>();
    private final boolean failView = false;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        members.add("User1");
        members.add("User2");
        deleteChatOutputData = new DeleteChatOutputData(chatName, (ArrayList<String>) members, failView);
    }

    /**
     * Tests the getChatname method.
     * Ensures it returns the correct chat name.
     */
    @Test
    void getChatname() {
        assertEquals(chatName, deleteChatOutputData.getChatname());
    }

    /**
     * Tests the getMembers method.
     * Ensures it returns the correct list of members.
     */
    @Test
    void getMembers() {
        assertEquals(members, deleteChatOutputData.getMembers());
    }

    /**
     * Tests the isFailView method.
     * Ensures it returns the correct fail view status.
     */
    @Test
    void isFailView() {
        assertEquals(failView, deleteChatOutputData.isFailView());
    }
}