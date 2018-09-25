package Protocol;

public class WaitingList {

    private String[] waitingList;

    public WaitingList(String[] waitingList) {
        this.waitingList = waitingList;
    }

    public String[] getList() {
        return this.waitingList;
    }

    public int count() {
        return this.waitingList.length;
    }
}
