package Entities;

import java.awt.*;
public class Bloques extends Entity{
    protected int id;
    protected boolean collision = true;
    protected Bloques(float x, float y, int height, int width, int id, boolean collision) {
        super(x, y, height, width);
        this.id = id;
        this.collision = collision;
    }

    public void update(){}
    public void draw(Graphics g){}
}
