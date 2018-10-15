package Protocol;

import java.util.HashMap;

import GameGUI.MyDefaultTableModel;

public class Insert {

    private HashMap<String, String>character;
    private int column;
    private int row;
    private MyDefaultTableModel myModel;


    public Insert(HashMap<String, String> character, int y,int z, MyDefaultTableModel model) {

        this.character = character;
        this.column = y;
        this.row=z;
        this.myModel = model;
    }

    public HashMap<String, String> getCharacter() {
        return this.character;
    }

    public int getColumn() {
        return this.column;
    }
    public int getRow() {
    	return this.row;
    }
    
    public MyDefaultTableModel getModel() {
    	return this.myModel;
    }
    
    
}
