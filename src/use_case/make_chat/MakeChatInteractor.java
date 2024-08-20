package use_case.make_chat;

import entity.Chat;
import entity.ChatFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The interactor class that handles the business logic for creating a new chat session.
 * It implements the {@link MakeChatInputBoundary} interface and interacts with the data access layer,
 * the output boundary (presenter), and the chat entity factory.
 */
public class MakeChatInteractor implements MakeChatInputBoundary {

    private final MakeChatUserDataAccessInterface makeChatDAO;
    private final MakeChatOutputBoundary makeChatPresenter;
    private final ChatFactory chatFactory;

    /**
     * Constructs a new {@code MakeChatInteractor} with the specified data access interface,
     * output boundary, and chat factory.
     *
     * @param makeChatDAO      the data access interface used to interact with the chat-related data.
     * @param makeChatPresenter the output boundary used to present the result of the chat creation process.
     * @param chatFactory      the factory used to create instances of {@link Chat}.
     */
    public MakeChatInteractor(MakeChatUserDataAccessInterface makeChatDAO, MakeChatOutputBoundary makeChatPresenter, ChatFactory chatFactory) {
        this.makeChatDAO = makeChatDAO;
        this.makeChatPresenter = makeChatPresenter;
        this.chatFactory = chatFactory;
    }

    /**
     * Handles the process of creating a new chat session based on the provided input data.
     *
     * Validates the input data and interacts with the necessary components to either create
     * the chat or handle any errors that occur during the process.
     *
     * @param inputData the input data required to create the chat, including chat name, users, and time.
     */
    @Override
    public void makeChat(MakeChatInputData inputData) {
        // Validate the input data
        if (inputData.getChatName().isEmpty() || inputData.getUser_2().isEmpty()) {
            makeChatPresenter.setFailView("Please enter all fields");
        } else if (makeChatDAO.ChatExists(inputData.getChatName())) {
            makeChatPresenter.setFailView("Chat name already exists");
        } else if (!makeChatDAO.UserExists(inputData.getUser_2())) {
            makeChatPresenter.setFailView("User doesn't exist");
        } else {
            // Create the chat
            LocalDateTime date = LocalDateTime.now();
            ArrayList<String> users = new ArrayList<>();
            users.add(inputData.getUser_1());
            users.add(inputData.getUser_2());
            Chat chat = chatFactory.createChat(inputData.getChatName(), users, 2, new ArrayList<>(), date);
            makeChatDAO.saveChat(inputData.getUser_1(), inputData.getUser_2(), chat);

            // Prepare and present the output data
            MakeChatOutputData outputData = new MakeChatOutputData(chat, false);
            makeChatPresenter.setPassView(outputData);
        }
    }
}