package Entities;

import java.awt.*;

/**
 * Clase Bloques
 */
public class Bloques extends Entity{
    protected int id;
    protected boolean collision = true;

    /**
     * @param x
     * @param y
     * @param height
     * @param width
     * @param id
     * @param collision
     */
    protected Bloques(float x, float y, int height, int width, int id, boolean collision) {
        super(x, y, height, width);
        this.id = id;
        this.collision = collision;
    }

    public void update(){}
    public void draw(Graphics g){}
}
