package Protocol;

import java.util.HashMap;

public class Insert {

    private HashMap<Integer, String>character;
    private int column;
    private int row;

    public Insert(HashMap<Integer,String> character, int y,int z) {
        this.character = character;
        this.column = y;
        this.row=z;
    }

    public HashMap<Integer, String> getCharacter() {
        return this.character;
    }

    public int getColumn() {
        return this.column;
    }
    public int getRow() {
    	return this.row;
    }
}
