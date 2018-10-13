package Protocol;

import java.util.HashMap;

public class Insert {

    private HashMap<Integer, String>character;
    private int column;

    public Insert(HashMap<Integer,String> character, int y) {
        this.character = character;
        this.column = y;
    }

    public HashMap<Integer, String> getCharacter() {
        return this.character;
    }

    public int getColumn() {
        return this.column;
    }
}
