package Entities;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import utilz.LoadImg;

/**
 * Clase Slope
 */
public class Slope extends Bloques{
	BufferedImage Sprites[];
	boolean isRightSlope = false, isLeftSlope = false, upside = false;
	float slope = 0;
	Point2D.Float Point1, Point2;
	Player player;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param height
	 * @param width
	 * @param id
	 * @param collision
	 * @param player
	 */
	public Slope(float x, float y, int height, int width, int id, boolean collision, Player player) {
		super(x, y, height, width, id, collision);
		createHitbox(x, y, width, height);
		this.player = player;
		initSprite();
		checkType(id);
		initPoint();
		calculateSlope();
	}

	/**
	 * Todo Carga los sprites de los slopes
	 */
	private void initSprite() {
		BufferedImage slopesprite = LoadImg.GetResizedImage(LoadImg.SlopesSprite, 24*2, 24);
		this.Sprites = new BufferedImage[2];
		for (int i = 0; i < Sprites.length; i++) {
			this.Sprites[i] = slopesprite.getSubimage(i*24, 0, 24, 24);
		}
	}

	@Override
	public void update(){
		if(this.isLeftSlope){
			leftPlayerCollision();
		}
		if(this.isRightSlope){
			rightPlayerCollision();
		}
	}

	@Override
	public void draw(Graphics g){
		if(isLeftSlope && !upside){		//type 84	GREY		ERROR UP
			g.drawImage(this.Sprites[1], (int)this.Point1.x, (int)this.Point1.y, 24, 24, null);}
		if(isRightSlope && !upside){	//type 168	GREEN		ERROR DOWN
			g.drawImage(this.Sprites[0], (int)this.Point1.x, (int)this.Point2.y, 24, 24, null);}
		if(isLeftSlope && upside){		//type 180	WEIRD COLOR
			g.drawImage(this.Sprites[1], (int)this.Point1.x, (int)this.Point2.y+24, 24, -24, null);}
		if(isRightSlope && upside){		//type 235	YELLOW
			g.drawImage(this.Sprites[0], (int)this.Point1.x, (int)this.Point1.y+24, 24, -24, null);}
		g.drawLine((int)this.Point1.x, (int)this.Point1.y, (int)this.Point2.x, (int)this.Point2.y);
	}

	public void initPoint(){
		if(!this.upside){
			if(this.isLeftSlope){//		_\
				this.Point1 = new Point2D.Float(x, y);
				this.Point2 = new Point2D.Float(x+width, y+height);
				}
			else if(this.isRightSlope){//		/_
				this.Point1 = new Point2D.Float(x, y+height);
				this.Point2 = new Point2D.Float(x+width, y);
				}
		}
		else if(upside){
			if(this.isLeftSlope){//		_\
				this.Point1 = new Point2D.Float(x, y+height);
				this.Point2 = new Point2D.Float(x+width, y);
				}
			else if(this.isRightSlope){//		/_
				this.Point1 = new Point2D.Float(x, y);
				this.Point2 = new Point2D.Float(x+width, y+height);
				}
		}
		
	}

	public void checkType(int id){
		if(id == 84){/* 	Left	 */
			this.upside = false;
			this.isLeftSlope = true;
		}
		else if(id == 168){/* 	Right	 */
			this.upside = false;
			this.isRightSlope = true;
		}
		else if(id == 180){/* 	UpsideLeft	 */
			this.upside = true;
			this.isLeftSlope = true;
		}
		else if(id == 235){/* 	UpsideRight	 */
			this.upside = true;
			this.isRightSlope = true;
		}
	}
	
	public void leftPlayerCollision(){
		if(this.player.hitbox.intersects(this.hitbox) && isInsideSlope()){
			if(isLeftSlope && !upside){
				if(this.player.xSpeed < 0){//Upwards
					this.player.resetInAir();
					this.player.hitbox.y -=	this.player.playerSpeed;
				}
				else if(this.player.xSpeed == 0 && !upside){
                    this.player.resetInAir();
                    this.player.hitbox.y = this.player.hitbox.y;
				}
			}
			if(isLeftSlope && upside){
				if(this.player.xSpeed < 0){//Downwards
					this.player.hitbox.y +=	this.player.playerSpeed;
				}
				if(0 < this.player.xSpeed){//UpWards	"Only Collision"
					this.player.hitbox.y +=	(this.player.playerSpeed/2);		//No. Lo "Lo Deja" Pasar, es como que colisiona
				}
			}


		}
	}

	public void rightPlayerCollision(){
		if(this.player.hitbox.intersects(this.hitbox) && isInsideSlope()){
			if(isRightSlope && !upside){
				if(this.player.xSpeed > 0){//Upwards
					this.player.resetInAir();
					this.player.hitbox.y -= this.player.playerSpeed;
				}
				else if(this.player.xSpeed == 0 && !upside){
                    this.player.resetInAir();
                    this.player.hitbox.y = this.player.hitbox.y;
					}
			}
			if(isRightSlope && upside){
				if(this.player.xSpeed > 0){//DownWards
					this.player.hitbox.y +=	this.player.playerSpeed;
				}
				else if(this.player.xSpeed == 0 && !upside){//Only Collision
					this.player.hitbox.y +=	(this.player.playerSpeed/2);
				}
			}
		}
	}

	public void calculateSlope(){
		float slopeTop = this.Point1.y - this.Point2.y;
		float slopeBottom = this.Point1.x - this.Point2.x;
		this.slope = (slopeTop / slopeBottom);
	}

	public float YCondition(){
		float YCondition = 0;
		if((this.isLeftSlope && !this.upside) || (this.isRightSlope && this.upside)){		 	//		_\ || ¬\
			YCondition = (this.slope * this.Point1.x);
			YCondition += this.Point1.y;
		}
		else if ((this.isRightSlope && !this.upside) || (this.isLeftSlope && this.upside)){		//		/_ || /¬
			YCondition = (this.slope * this.Point1.x) + 24;
			YCondition += this.Point1.y;
		}
		return YCondition;
	}

	public boolean isInsideSlope(){
		if((YCondition() <= (this.player.hitbox.y+this.player.hitbox.height)) && !this.upside){	// RightNormal
			return true;}

		if(!(YCondition() <= (this.player.hitbox.y+this.player.hitbox.height)) && !this.upside){	//LeftNormal
			return true;}
		
		else if(!(YCondition() >= (this.player.hitbox.y)) && this.upside){	//LeftUpside
			return true;}

		else if((YCondition() >= (this.player.hitbox.y)) && this.upside){	//RightUpside
			return true;}
		else{return false;}
	}
}
