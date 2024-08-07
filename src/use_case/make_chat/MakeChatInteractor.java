package use_case.make_chat;

import entity.Chat;
import entity.ChatFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MakeChatInteractor implements MakeChatInputBoundary{

    final MakeChatUserDataAccessInterface makeChatDAO;
    final MakeChatOutputBoundary makeChatPresenter;
    final ChatFactory chatFactory;

    public MakeChatInteractor(MakeChatUserDataAccessInterface makeChatDAO, MakeChatOutputBoundary makeChatPresenter, ChatFactory chatFactory) {
        this.makeChatDAO = makeChatDAO;
        this.makeChatPresenter = makeChatPresenter;
        this.chatFactory = chatFactory;
    }

    @Override
    public void makeChat(MakeChatInputData inputData) {
        if (inputData.getChatName().isEmpty() || inputData.getUser_2().isEmpty()) {
            makeChatPresenter.setFailView("Please enter all fields");
        } else if (makeChatDAO.ChatExists(inputData.getChatName())) {
            makeChatPresenter.setFailView("Chat name already exists");
        } else if (!makeChatDAO.UserExists(inputData.getUser_2())) {
            makeChatPresenter.setFailView("User doesn't exist");
        } else {
            LocalDateTime date = LocalDateTime.now();
            ArrayList<String> users = new ArrayList<>();
            users.add(inputData.getUser_1());
            users.add(inputData.getUser_2());
            Chat chat = chatFactory.createChat(inputData.getChatName(), users, 2, new ArrayList<>(), date);
            makeChatDAO.saveChat(inputData.getUser_1(), inputData.getUser_2(), chat);
            MakeChatOutputData outputData = new MakeChatOutputData(chat, false);
            makeChatPresenter.setPassView(outputData);
        }
    }
}
