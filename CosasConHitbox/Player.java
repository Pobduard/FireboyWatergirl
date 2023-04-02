package CosasConHitbox;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends HitboxMadre {
    BufferedImage image;
    boolean isAlive;
    private Color color;
    private boolean right, left, up, down;

    public Color SetColor(int id){
        switch (id){
            case 0:
                color = Color.RED;
                return color;
            case 1:
                color = Color.YELLOW;
                return color;
            case 2:
               color = Color.BLACK;
                return color;
            default:
                return Color.cyan;
        }
    }
    public Player(int x, int y, int height, int width, int id){
       super(x, y, height, width, id);
       createHitbox(x,y,width,height);
        System.out.println("Me cree xd" );

    }
    public void render(Graphics g, Color this_color){
        drawHitbox(g,this_color);
    }
    private void updatePosition(){

    }


    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
