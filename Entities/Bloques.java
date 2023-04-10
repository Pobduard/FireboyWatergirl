package Entities;

import java.awt.*;

/**
 * Clase Bloques
 */
public class Bloques extends Entity{
    public int id;
    public float index;
    public boolean collision = true, isWin = false;

    /**
     * @param x
     * @param y
     * @param height
     * @param width
     * @param id (Tipe de Bloque que es)
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
