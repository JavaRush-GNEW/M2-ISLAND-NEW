package ua.com.javarush.gnew.Field;

import lombok.Data;

@Data
public class Field implements Fieldable{

    public int rows;
    public int columns;

    private Fieldable[][] field;

    public Field(int rows,int columns) {
        this.rows = rows;
        this.columns = columns;
        field = new Fieldable[rows][columns];
    }
    @Override
    public void SaveInField(int x, int y, Fieldable object){
        field[x][y] = object;
    }
    @Override
    public Fieldable getFieldable(int x, int y) {
        return field[x][y];
    }
}
