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
        if (makeChatDAO.ChatExists(inputData.getChatName())) {
            makeChatPresenter.setFailView("Chat name already exists");
        } else if (!makeChatDAO.UserExists(inputData.getUser_2())) {
            makeChatPresenter.setFailView("User doesnt exist");
        } else {
            LocalDateTime date = LocalDateTime.now();
            ArrayList<String> user = new ArrayList<>();
            user.add(inputData.getUser_1());
            user.add(inputData.getUser_2());
            Chat chat = chatFactory.createChat(inputData.getChatName(), user, 2, new ArrayList<>());
            makeChatDAO.saveChat(chat);
            MakeChatOutputData outputData = new MakeChatOutputData(chat, false);
        }
    }
}
