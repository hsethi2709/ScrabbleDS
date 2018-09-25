package Protocol;

public class Insert {

    private String character;
    private int x;
    private int y;

    public Insert(String character, int x, int y) {
        this.character = character;
        this.x = x;
        this.y = y;
    }

    public String getCharacter() {
        return this.character;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
