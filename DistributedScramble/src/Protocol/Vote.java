package Protocol;

public class Vote {

    private boolean isApprove;
    private String word;

    public Vote(boolean isApprove,String word) {
        this.isApprove = isApprove;
        this.word=word;
    }

    public boolean isApprove() {
        return this.isApprove;
    }
    
    public String getWord() {
    	return this.word;
    }
}
