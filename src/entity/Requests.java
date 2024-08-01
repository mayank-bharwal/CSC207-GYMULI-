package entity;




/**
 * The Requests class represents a friend request from one user to the other, and contains the request sender and the request receiver
 */
public class Requests {
    private User From;
    private User To;

    /**
     * Constructs a new requests
     * @param From    the user sending the request
     * @param To      the user receiving the request
     */

    public Requests(User From, User To) {
        this.From = From;
        this.To = To;
    }

    /**
     * get the user sending the request
     * @return the user sending the request
     */
    public User getFrom() {
        return From;
    }

    public User getTo() {
        return To;
    }

    public void setFrom(User From) {
        this.From = From;
    }

    public void setTo(User To) {
        this.To = To;
    }
}


