package Protocol;

public class WaitingList {

    private String[] waitingList;
    private String gameStatus;

    public WaitingList(String[] waitingList,String gameStatus) {
        this.waitingList = waitingList;
        this.gameStatus=gameStatus;
    }

    public String[] getList() {
        return this.waitingList;
    }
    

    public String gameStatus() {
        return this.gameStatus;
    }
}
