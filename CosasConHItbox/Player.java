package CosasConHItbox;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends HitboxMadre {
    BufferedImage image;
    boolean isAlive;
    private Color color;

    public Color SetColor(){
        switch (id){
            case 0:
                color = Color.RED;
                return color;
            case 1:
                color = Color.BLUE;
                return color;
            case 2:
               color = Color.BLACK;
                return color;
            default:
                return Color.cyan;
        }
    }
    Player(int x, int y, int height, int width, int id){
       super(x, y, height, width, id);
       createHitbox(x,y,width,height,color);
    }
}
