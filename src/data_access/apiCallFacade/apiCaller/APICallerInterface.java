package data_access.apiCallFacade.apiCaller;

public interface APICallerInterface {
    public float getSimilarityScore(String text1, String text2);

    public String filterProfanity(String text1);

    public void use_paid(boolean paid);
}
