package Protocol;

public class Reply {

    private String replyTo;
    private boolean result;
    private String failMessage;

    public Reply(String replyTo, boolean result, String failMessage) {
        this.replyTo = replyTo;
        this.result = result;
        this.failMessage = failMessage;
    }

    public String getType() {
        return this.replyTo;
    }

    public boolean getResult() {
        return this.result;
    }

    public String getMessage() {
        return this.failMessage;
    }

}
