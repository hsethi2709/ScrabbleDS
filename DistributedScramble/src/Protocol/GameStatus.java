package Protocol;

public class GameStatus {

    private boolean isOver;
    private String winner;
    private int[] scores;
    private String nextTurn;

    public GameStatus(String winner) {
        this.isOver = true;
        this.winner = winner;
    }

    public GameStatus(int[] scores, String nextTurn) {
        this.isOver = false;
        this.scores = scores;
        this.nextTurn = nextTurn;
    }
    
    
    
    public boolean isOver() {
        return this.isOver;
    }

    public String getWinner() {
        return this.winner;
    }

    public int[] getScores() {
        return this.scores;
    }

    public String getNext() {
        return this.nextTurn;
    }
}
