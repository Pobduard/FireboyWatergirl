package Entities;

public class Bloques extends Entity{
    private int id;
    protected Bloques(int x, int y, int height, int width, int id) {
        super(x, y, height, width);
        this.id = id;
    }
}
