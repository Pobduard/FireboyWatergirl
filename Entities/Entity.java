package Entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/*
* Tambien a lo largo de todo el Juego, si algo se mueve, se movera usando su hitbox y no los valores originales, con la finalidad
* de poder Reiniciar la posicion de esa hitbox de forma mas sencila (el valor de su "x" y "y" no se cambiaran a menos que se desee, y ese sera el caso de cambios
* de nivel por ejemplo, que usualmente se hara de forma "discreta", osea no en conjunto con actualizar la hitbox)
 */
/** 
 * {@code Entity} es una clase Madre para Todo Tipo de Entidades
*/
public abstract class Entity{
    protected int width,height;
    protected float x, y;
    protected Rectangle2D.Float hitbox;

    /**
     * @param x posicion en x de la Entity
     * @param y posicion en y de la Entity
     * @param width ancho de la Entity
     * @param height alto de la Entity
     */
    protected Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // /**
    // * @return {@code id}
    //  */
    // protected int getId() {
    //     return this.id;
    // }

    // /**
    //  * Cambia la id del Objeto
    //   */
    // protected void changeId(int Id) {
    //     this.id = Id;
    // }

    /**
     *Dibujar la Hitbox, Para Debuggin
     */
    protected void drawHitbox(Graphics g){
        g.setColor(Color.PINK);
        g.drawRect((int)this.hitbox.x, (int)this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height);
   }

    
    /**
     * Crea una Hitbox para el Objeto
     * @param x Posicion x de la hitbox
     * @param y Posicion y de la hitbox
     * @param width Ancho de la hitbox
     * @param height Alto de la hitbox
     */
    protected void createHitbox(float x,float y,int width, int height){
        hitbox = new Rectangle2D.Float(x,y,width,height);
   }

    /**
    * @return {@code hitbox}
     */

    /**
     *
     * @return la hitbox
     */
    public Rectangle2D.Float getHitbox(){
        return hitbox;
   }

    /**
 * Cambiar la posicion de la {@code (x,y)} que son fijas
  */
   public void changeXYPos(float x,float y){
       this.x = x;
       this.y = y;
   }

    /**
    * Igualar {@code (hitbox.x, hitbox.y)} a las {@code (x,y)} "ancladas"
     */
    public void resetHitboxPos(){
    this.hitbox.x = this.x;
    this.hitbox.y = this.y;
   }
}
