package entity;

public class Requests {
    private User From;
    private User To;

    public Requests(User From, User To) {
        this.From = From;
        this.To = To;
    }

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
