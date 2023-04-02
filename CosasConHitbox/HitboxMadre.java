package CosasConHitbox;

import java.awt.*;


public abstract class HitboxMadre extends Rectangle {
    protected int x,y,width,height,id;
    protected Rectangle hitbox;
    protected HitboxMadre(int x, int y, int height, int width, int id){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }

   protected void drawHitbox(Graphics g){
        g.setColor(Color.RED);
        g.drawRect(x,y,width,height);
   }
   protected void createHitbox(int x,int y,int width, int height,Color color){
        Rectangle hitbox = new Rectangle(x,y,width,height);
   }
   public Rectangle getHitbox(){
        return hitbox;
   }
}
