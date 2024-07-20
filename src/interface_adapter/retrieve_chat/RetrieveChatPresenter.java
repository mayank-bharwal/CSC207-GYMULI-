//package interface_adapter.retrieve_chat;
//
//import interface_adapter.ViewModelManager;
//import use_case.retrieve_chat.RetrieveChatOutputBoundary;
//import use_case.retrieve_chat.RetrieveChatOutputData;
//
//import java.time.LocalDateTime;
//
//public class RetrieveChatPresenter implements RetrieveChatOutputBoundary{
//
//    private final RetrieveChatViewModel retrieveChatViewModel;
//    private final RetrieveChatViewModel retrieveRetrieveChatViewModel2;
//    private ViewModelManager viewModelManager;
//
//    public RetrieveChatPresenter(RetrieveChatViewModel retrieveChatViewModel, RetrieveChatViewModel retrieveRetrieveChatViewModel2, ViewModelManager viewModelManager) {
//        this.retrieveChatViewModel = retrieveChatViewModel;
//        this.retrieveRetrieveChatViewModel2 = retrieveRetrieveChatViewModel2;
//        this.viewModelManager = new ViewModelManager();
//    }
//
//
//
//    @Override
//    public void prepareSuccessView(RetrieveChatOutputData chatInfo) {
//
//        LocalDateTime responseTime = chatInfo.getTime();
//        RetrieveChatState retrieveChatState = retrieveRetrieveChatViewModel2.getState();
//
//        viewModelManager.setActiveView(retrieveRetrieveChatViewModel2.getViewName());
//        viewModelManager.firePropertyChanged();
//
//    }
//
//    @Override
//    public void prepareFailView(String error) {
//        RetrieveChatState chatState = RetrieveChatViewModel.getState();
//        retrieveChatViewModel.firePropertyChanged();
//
//    }
//}
