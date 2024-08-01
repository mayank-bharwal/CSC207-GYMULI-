package entity;



/**
 * The UserSimilarity class represents a score between two users that show how similar they are based on their inters
 */
public class UserSimilarity {
    private final String username;
    private final double score;

    /**
     * Constructs a similarity score with the specified user and score
     * @param username    username of user
     * @param score      based on the user's interests
     */
    public UserSimilarity(String username, double score) {
        this.username = username;
        this.score = score;
    }
    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    public double getScore() {
        return score;
    }
}