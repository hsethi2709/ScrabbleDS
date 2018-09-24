package Protocol;

public class Invitation {

    private String invitationFrom;

    public Invitation(String from) {
        this.invitationFrom = from;
    }

    public String from() {
        return this.invitationFrom;
    }
}
