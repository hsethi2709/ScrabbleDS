package Protocol;

public class GameList {

    private String[] gameList;

    public GameList(String[] gameList) {
        this.gameList = gameList;
    }

    public String[] getList() {
        return this.gameList;
    }

    public int count() {
        return this.gameList.length;
    }
}
