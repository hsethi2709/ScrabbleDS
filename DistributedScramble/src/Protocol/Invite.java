package Protocol;

public class Invite {

    private String[] inviteName;

    public Invite(String[] inviteName) {
        this.inviteName = inviteName;
    }

    public String[] getNames() {
        return this.inviteName;
    }
}
