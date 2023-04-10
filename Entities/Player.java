package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import levels.leveldata;
import maines.game;
import gamestates.GameStates;
import utilz.HelpMethods;
import utilz.LoadImg;

import static utilz.Constants.PlayerConstants.*;

/**Clase Jugador
*/
public class Player extends Entity {
   /* * @param right si es cierta, el jugador esta moviendose a la derecha
 * @param left si es cierta, el jugador esta moviendose a la izquierda
 * @param InAir si es cierta, el jugador esta en el aire (saltando, o callendo)
 * @param IsAlive si es cierta, el jugador esta vivo/en juego*/


    private BufferedImage[][] Sprites;
    private leveldata level;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    private boolean moving, right ,left ,inAir , canJump, Alive = true;
    private int type;
    private float playerSpeed = 0.9f, jumpSpeed = -2.5f, gravity = 0.02f;
    private float fallSpeedAfterCollision = 0.5f, airSpeed = 0f;
    public float xSpeed = 0f;

    /**Constructor<p>
     * Crear la {@code hitbox}
     * @param x coordenada en x de la hitbox
     * @param y coordenada en y de la hitbox
     * @param width ancho de la hitbox
     * @param height alto de la hitbox
     * @param level obtener la data del nivel el cual sera actualizado
     * @param type tipo de jugador seleccionado
    */
    public Player(float x, float y, int width, int height, leveldata level, int type){
       super(x, y, height, width);
       this.level = level;
       this.type = type;
       createHitbox(x,y,width,height);
       loadAnimations(type);
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
     * Carga las animaciones correspondientes de cada personaje  */
    private void loadAnimations(int type){
        if(type == 0){  //"Fire"
            BufferedImage Idle = LoadImg.GetImage(LoadImg.MARIO_IDLE);
            BufferedImage Jump = LoadImg.GetImage(LoadImg.MARIO_JUMP);
            BufferedImage Fall = LoadImg.GetImage(LoadImg.MARIO_FALL);
            BufferedImage img = LoadImg.GetImage(LoadImg.MARIO_MOVE);
            BufferedImage[] Movement = new BufferedImage[8];
            for (int i = 0; i < Movement.length; i++) {
                Movement[i] = img.getSubimage(i*16, 0, 16, 32);
            }
            this.Sprites = new BufferedImage[4][8];
            this.Sprites[0][0] = Idle;
            this.Sprites[1] = Movement;
            this.Sprites[2][0] = Jump;
            this.Sprites[3][0] = Fall;
        }
        if(type == 1){  //"Wa'er"
        BufferedImage Idle = LoadImg.GetImage(LoadImg.LUIGI_IDLE);
        BufferedImage Jump = LoadImg.GetImage(LoadImg.LUIGI_JUMP);
        BufferedImage Fall = LoadImg.GetImage(LoadImg.LUIGI_FALL);
        BufferedImage img = LoadImg.GetImage(LoadImg.LUIGI_MOVE);
        BufferedImage[] Movement = new BufferedImage[8];
        for (int i = 0; i < Movement.length; i++) {
            Movement[i] = img.getSubimage(i*15, 0, 15, 36);
        }
        this.Sprites = new BufferedImage[4][8];
        this.Sprites[0][0] = Idle;
        this.Sprites[1] = Movement;
        this.Sprites[2][0] = Jump;
        this.Sprites[3][0] = Fall;
        };
    }

    /** 
     * @return {@code type} */
    private int playerType(){
        return this.type;
    }

    /**
     * Dibuja el Jugador en la posicion de su hitbox Solo si se esta jugando*/
    public void draw(Graphics g){
        if(GameStates.gamestate == GameStates.PLAYING){
            // g.setColor(Color.BLACK);
            // g.fillRect((int)this.hitbox.x, (int)this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height);
            if(this.isAlive())
                {if(this.left && playerAction == RUNNING){
                    g.drawImage(this.Sprites[playerAction][aniIndex], (int)(this.hitbox.x+this.hitbox.width), (int)this.hitbox.y, -(int)this.hitbox.width, (int)this.hitbox.height,null);
                }else{
                    g.drawImage(this.Sprites[playerAction][aniIndex], (int)this.hitbox.x, (int)this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height, null);
                }
                drawHitbox(g);
            }
        }
    }

    /**
     * Actualiza la posicion dependiendo de sus atributos booleanos*/
    private void updatePos(){
        this.moving= false;
        if(this.canJump){
            jump();}

        //Si no se pulsa anda, salimos
		if(!inAir){
			{if((!left && !right) || left && right)
				return;}}

        this.xSpeed = 0f;

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
                //TODO: SECURITY
                if(this.airSpeed >= 5f){this.airSpeed = 5f;}
                updateXPos(xSpeed);

            } else
                {	//Si aqui no nos podemos mover, entonces es porque estamos en el piso, o colisionando con el techo
                this.hitbox.y = HelpMethods.GetEntityYPosInsideTile(this.hitbox, airSpeed);

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
        this.moving = true;

    }

    /** 
     * Actualiza la Posicion en la X del Player  */
    private void updateXPos(float xSpeed){
        if(HelpMethods.CanMoveHere((this.hitbox.x+xSpeed), this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height, level.getLvlData())){	//+ Chequea por lo que seria la sig Posicion, siguiente por la velocidad que lleve
            this.hitbox.x += xSpeed;
        }else {
				this.hitbox.x = HelpMethods.GetEntityXPosInsideTile(this.hitbox, xSpeed);
			}
    }

    /** 
     * Resetea el estado de {@code inAir} del personaje, y reinicia su {@code airSpeed} */
    private void resetInAir() {
		this.inAir = false;
		this.airSpeed = 0;
	}

    /** 
     * Cambia los Estados del Personaje para que en los chekeos "pueda saltar"  */
    private void jump(){
        if(this.inAir){return;}
        else{
            this.inAir = true;
            this.airSpeed = this.jumpSpeed;}
    }

    /**
     * Pone en Falso todos los Boleanos de Movimiento  */
    private void setBoleans(){
        this.moving = false;
        this.right = false;
        this.left = false;
        this.inAir = false;
        this.canJump = false;
    }

    /**
     * Actualiza la Animacion*/
    private void updateAnimation(){
        aniTick++;
		if(aniTick >= aniSpeed){
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(playerAction)){
				aniIndex = 0;
			}
		}
    }

    /** 
     * Resetea los cambios en la Animacion para que se haga un bucle  */
    private void resetAnimationTick(){
        this.aniTick = 0;
        this.aniIndex = 0;
    }

    /**
     * Selecciona la animacion, dependiendo de los atributos del personaje*/
    private void setAnimation(){
        int startAni = playerAction;
        if(moving){playerAction = RUNNING;}
        else{playerAction = IDLE;}

        if(inAir){
            if(airSpeed < 0){playerAction = JUMP;}
            else{playerAction = FALL;}
        }

        if(startAni != playerAction){resetAnimationTick();}
    }

    /** 
     * Determina si el jugador colsiono con algo "Letal", tomando en cuenta tipo de jugador es.
     * @see #setPlayerType(int)
     * */
    public void IsLethal(){
		int xLeft = (int)this.hitbox.x / game.Tile_Size;
		int yUp = (int)this.hitbox.y / game.Tile_Size;
		int xRight = (int)(this.hitbox.x + this.hitbox.width) / game.Tile_Size;
		int yDown = (int)(this.hitbox.y + this.hitbox.height) / game.Tile_Size;
        int[][] levelData = level.getLvlData();

        //-1 por recordar que el Array Empieza en 0, tons llega hasta 39
		if(xLeft > game.Game_Tiles_In_Width-1){
			xLeft = game.Game_Tiles_In_Width-1;}

		//-1 por recordar que el Array Empieza en 0, tons llega hasta 29
		if(yUp > game.Game_Tiles_In_Height-1){
			yUp = game.Game_Tiles_In_Height-1;}

        //-1 por recordar que el Array Empieza en 0, tons llega hasta 39
		if(xRight > game.Game_Tiles_In_Width-1){
			xRight = game.Game_Tiles_In_Width-1;}

		//-1 por recordar que el Array Empieza en 0, tons llega hasta 29
		if(yDown > game.Game_Tiles_In_Height-1){
			yDown = game.Game_Tiles_In_Height-1;}

		int LeftUp = levelData[xLeft][yUp];
		int RightDown = levelData[xRight][yDown];
		int LeftDown = levelData[xLeft][yDown];
		int RightUp = levelData[xRight][yUp];

		//34 = Toxico, 153 = Agua, 237 = Lava 
        if(LeftUp == 34 || RightDown == 34 || LeftDown == 34 || RightUp == 34){this.Alive = false;}                  //Para Ambos

        if(playerType() == 0 && (LeftUp == 153 || RightDown == 153 || LeftDown == 153 || RightUp == 153)){
            this.Alive = false;}    //Para FireBoy
        if(playerType() == 1 && (LeftUp == 237 || RightDown == 237 || LeftDown == 237 || RightUp == 237)){this.Alive = false;}    //Para Watergirl

        if(this.hitbox.x < 0 || (this.hitbox.x + this.hitbox.width) > game.Game_Width){this.Alive = false;}
		if(this.hitbox.y < 0 || (this.hitbox.y + this.hitbox.height) > game.Game_Height){this.Alive = false;}
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
