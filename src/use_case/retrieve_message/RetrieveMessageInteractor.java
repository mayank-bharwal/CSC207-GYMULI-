package use_case.retrieve_message;

public class RetrieveMessageInteractor implements RetrieveMessageInputBoundary {
    final private RetrieveMessageOutputBoundary outputBoundary;
    final private RetrieveMessageUserDataAccessInterface RetrieveDAO;

    public RetrieveMessageInteractor(RetrieveMessageOutputBoundary outputBoundary, RetrieveMessageUserDataAccessInterface retrieveDAO) {
        this.outputBoundary = outputBoundary;
        RetrieveDAO = retrieveDAO;
    }

    @Override
    public void retrieveMessage(RetrieveMessageInputData inputData) {
        String sender = inputData.getSender();
        String reciever = inputData.getReciever();


    }
}
