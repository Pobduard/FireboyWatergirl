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
    private boolean right ,left ,inAir , canJump, Alive = true;
    private int type;
    private float playerSpeed = 0.9f, jumpSpeed = -2.5f, gravity = 0.02f;
    private float fallSpeedAfterCollision = 0.5f, airSpeed = 0f;

    /**Constructor<p>
     * Crear la {@code hitbox}
    */
    public Player(float x, float y, int width, int height, leveldata level){
       super(x, y, height, width);
       this.level = level;
       createHitbox(x,y,width,height);
       setBoleans();
    }

    /**
     * llama el resto de updates del jugador Solo si se esta jugando*/
    public void update(){
        if(GameStates.gamestate == GameStates.PLAYING){
            if(!isAlive()){resetHitboxPos();}
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
        if(this.canJump){
            jump();}

        //Si no se pulsa anda, salimos
		if(!inAir){
			{if((!left && !right) || left && right)
				return;}}

        float xSpeed = 0f;

        if(this.right){
            xSpeed += playerSpeed;
        }

        if(this.left){
            xSpeed -= playerSpeed;
        }

        //Si no esta en el Aire, pero Tampoco en el suelo
        if(!inAir && !HelpMethods.IsEntityOnFloor(this.hitbox, level.getLvlData())){
            this.inAir = true;} 

        if (inAir) {    //+ Accion en Y
            if(HelpMethods.CanMoveHere(this.hitbox.x, this.hitbox.y + airSpeed, (int)this.hitbox.width, (int)this.hitbox.height, level.getLvlData())){
                this.hitbox.y += this.airSpeed;
                this.airSpeed += this.gravity;
                updateXPos(xSpeed);

            } else
                {	//Si aqui no nos podemos mover, entonces es porque estamos en el piso, o colisionando con el techo
                this.hitbox.y = HelpMethods.GetEntityYPosUnderRoofOrAboveFloor(this.hitbox, airSpeed);

                if(this.airSpeed > 0){	//osease vamos abajo, por lo que tocamos el suelo
                    resetInAir();
                }else
                    {				//Si no tocamos el suelo, tons el techo
                    this.airSpeed = this.fallSpeedAfterCollision;}

                updateXPos(xSpeed);
                }

        } else
            {    //+ Accion en X si no estamos en el aire
            updateXPos(xSpeed);}

    }

    /** Actualiza la Posicion en la X del Player  */
    private void updateXPos(float xSpeed){
        if(HelpMethods.CanMoveHere((this.hitbox.x+xSpeed), this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height, level.getLvlData())){	//+ Chequea por lo que seria la sig Posicion, siguiente por la velocidad que lleve
            this.hitbox.x += xSpeed;
        }else {
				this.hitbox.x = HelpMethods.GetEntityXPosNextToWall(this.hitbox, xSpeed);
			}
    }

    private void resetInAir() {
		this.inAir = false;
		this.airSpeed = 0;
	}

    private void jump(){
        if(this.inAir){return;}
        else{
            this.inAir = true;
            this.airSpeed = this.jumpSpeed;}
    }

    /**
     * Pone en Falso todos los Boleanos de Movimiento  */
    private void setBoleans(){
        this.right = false;
        this.left = false;
        this.inAir = false;
        this.canJump = false;
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

        //-1 por recordar que el Array Empieza en 0, tons llega hasta 39
		if(xInsideTile > game.Game_Tiles_In_Width-1){
			xInsideTile = game.Game_Tiles_In_Width-1;}

		//-1 por recordar que el Array Empieza en 0, tons llega hasta 29
		if(yInsideTile > game.Game_Tiles_In_Height-1){
			yInsideTile = game.Game_Tiles_In_Height-1;}

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
     * @return {@code canJump} boolean */
    public boolean iscanJump() {
        return this.canJump;
    }

    /**
     * Cambia la condicion del boolean*/
    public void setcanJump(boolean canJump) {
        this.canJump = canJump;
    }

    /**
     * Cambia el Nivel a verficar*/
    public void setlvl(leveldata lvl) {
        this.level = lvl;
    }
}
