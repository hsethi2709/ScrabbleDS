package Protocol;

public class Vote {

    private boolean isApprove;

    public Vote(boolean isApprove) {
        this.isApprove = isApprove;
    }

    public boolean isApprove() {
        return this.isApprove;
    }
}
