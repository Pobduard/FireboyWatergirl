package Entities;

import java.awt.*;
import levels.leveldata;
import maines.game;
import gamestates.GameStates;
import utilz.HelpMethods;

/**Clase Jugador 
 * @param right si es cierta, el jugador esta moviendose a la derecha
 * @param left si es cierta, el jugador esta moviendose a la izquierda
 * @param InAir si es cierta, el jugador esta en el aire (saltando, o callendo)
 * @param IsAlive si es cierta, el jugador esta vivo/en juego
*/
public class Player extends Entity {
    private leveldata level;
    private boolean right ,left ,inAir , jump, Alive = true;
    private int type;
    private float xSpeed = 0.5f, ySpeed = -2.5f, gravity = 0.02f;

    /**Constructor<p>
     * Crear la {@code hitbox}
    */
    public Player(float x, float y, int height, int width, leveldata level){
       super(x, y, height, width);
       this.level = level;
       createHitbox(x,y,width,height);
       setBoleans();
        System.out.println("Jugador: Me cree xd" );
    }

    /**
     * llama el resto de updates del jugador Solo si se esta jugando*/
    public void update(){
        if(GameStates.gamestate == GameStates.PLAYING){
            updatePos();
            IsLethal();
            updateAnimation();
            setAnimation();
        }
    }

    /**
     * Dibuja el Jugador en la posicion de su hitbox Solo si se esta jugando*/
    public void draw(Graphics g){
        if(GameStates.gamestate == GameStates.PLAYING){
            g.setColor(Color.BLACK);
            g.fillRect((int)this.hitbox.x, (int)this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height);
            drawHitbox(g);
        }
    }

    /**
     * Actualiza la posicion dependiendo de sus atributos booleanos*/
    private void updatePos(){
        if(!Alive){
            resetHitboxPos();
            return;}

        if(!inAir){
            this.jump = true;} 
        else {
            this.jump = false;
            this.hitbox.y += gravity;}
        if(!jump){
            this.hitbox.y += ySpeed;}

        if((this.right && this.left)){
            return;}

        if(this.right){
            if(HelpMethods.CanMoveHere(this.hitbox.x + xSpeed, this.hitbox.y, this.width, this.height, level.getLvlData())){
                this.hitbox.x += xSpeed;
            }
        }

        if(this.left){
            if(HelpMethods.CanMoveHere(this.hitbox.x - xSpeed, this.hitbox.y, this.width, this.height, level.getLvlData())){
                this.hitbox.x -= xSpeed;
            }
        }
    }

    /**
     * Pone en Falso todos los Boleanos de Movimiento  */
    private void setBoleans(){
        this.right = true;
        this.left = false;
        this.inAir = false;
        this.jump = false;
    }

    /**
     * Actualiza la Animacion*/
    private void updateAnimation(){}

    /**
     * Selecciona la animacion, dependiendo de los atributos del personaje*/
    private void setAnimation(){}

    /** 
     * Determina si el jugador colsiono con algo "Letal", tomando en cuenta tipo de jugador es.
     * @see #setPlayerType(int)
     * */
    public void IsLethal(){
		int xInsideTile = (int)this.hitbox.x / game.Tile_Size;
		int yInsideTile = (int)this.hitbox.y / game.Tile_Size;
        int levelData[][] = level.getLvlData();

		int currentTile = levelData[xInsideTile][yInsideTile];

		//34 = Toxico, 153 = Agua, 237 = Lava 
		if(currentTile == 34){this.Alive = false;}                  //Para Ambos
        if(type == 0 && currentTile == 153){this.Alive = false;}    //Para FireBoy
        if(type == 1 && currentTile == 237){this.Alive = false;}    //Para Watergirl
	}
    
    /** 
     * Selecciona el tipo que jugador que sera generado
     * <p>
     * {@code 0} para FireBoy
     * {@code 1} para Watergirl*/
    public void setPlayerType(int set){
        this.type = set;
    }

    /**
     * @return {@code right} boolean
     */
    public boolean isRight() {
        return this.right;
    }

    /**
     * Cambia la condicion de {@code right}
      */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * @return {@code left} boolean
     */
    public boolean isLeft() {
        return this.left;
    }

    /**
     * Cambia la condicion de {@code left}
      */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * @return {@code Alive} boolean */
    public boolean isAlive(){
        return this.Alive;
    }

    /**
     * Cambia la condicion del boolean*/
    public void setAlive(boolean alive){
        this.Alive = alive;
    }

    /**
     * @return {@code jump} boolean */
    public boolean isJump() {
        return this.jump;
    }

    /**
     * Cambia la condicion del boolean*/
    public void setJump(boolean jump) {
        this.jump = jump;
    }

    /**
     * Cambia el Nivel a verficar*/
    public void setlvl(leveldata lvl) {
        this.level = lvl;
    }
}
