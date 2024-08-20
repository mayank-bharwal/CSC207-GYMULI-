package use_case.retrieve_chat;

import entity.Chat;
import java.util.ArrayList;

/**
 * The interactor class responsible for handling the business logic of retrieving a chat.
 * It implements the {@link RetrieveChatInputBoundary} interface and interacts with the
 * data access layer and output boundary to process chat retrieval requests.
 */
public class RetrieveChatInteractor implements RetrieveChatInputBoundary {

    private final RetrieveChatOutputBoundary presenter;
    private final RetrieveChatUserDataAccessInterface chatDataAccessObject;

    /**
     * Constructs a new {@code RetrieveChatInteractor} with the specified output boundary and data access interface.
     *
     * @param presenter              the output boundary used to present the result of the chat retrieval process.
     * @param chatDataAccessObject   the data access interface used to retrieve chat data from the data source.
     */
    public RetrieveChatInteractor(RetrieveChatOutputBoundary presenter,
                                  RetrieveChatUserDataAccessInterface chatDataAccessObject) {
        this.presenter = presenter;
        this.chatDataAccessObject = chatDataAccessObject;
    }

    /**
     * Retrieves the chat specified by the input data and presents it using the output boundary.
     *
     * @param inputData  the data required to retrieve the chat, including the chat name.
     */
    @Override
    public void retrieveChat(RetrieveChatInputData inputData) {
        // Retrieve the chat from the data access object
        Chat chat = chatDataAccessObject.getChat(inputData.getChatName());

        // Prepare the output data with the chat details
        RetrieveChatOutputData outputData = new RetrieveChatOutputData(
                chat.getChatName(),
                new ArrayList<>(chat.getUsers()),
                chat.getNoOfMembers(),
                new ArrayList<>(chat.getAllmessages()),
                chat.getTime(),
                false
        );

        // Present the chat details using the output boundary
        presenter.presentChat(outputData);
    }
}