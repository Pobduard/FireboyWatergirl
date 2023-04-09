package Entities;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import utilz.LoadImg;

import maines.game;

public class Slope extends Bloques{
	BufferedImage Sprites[];
	boolean isRightSlope = false, isLeftSlope = false, upside = false;
	float slope = 0;
	Point2D.Float Point1, Point2;
	Player player;
	public Slope(float x, float y, int height, int width, int id, boolean collision, Player player) {
		super(x, y, height, width, id, collision);
		createHitbox(x, y, width, height);
		this.player = player;
		initSprite();
		checkType(id);
		initPoint();
		calculateSlope();
	}

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
			System.out.println("LeftPlayerCondition");
			if(isLeftSlope && !upside){
				if(this.player.xSpeed > 0){//DownWards
					int pixelsUp = (int)(this.player.hitbox.x - Point2.x);
					this.player.hitbox.y -=	pixelsUp-1;
				}
				else if(this.player.xSpeed < 0){//Upwards
					int pixelsUp = (int)(this.player.hitbox.x - Point2.x);
					this.player.hitbox.y +=	pixelsUp;
				}
			}
			if(isLeftSlope && upside){
				if(this.player.xSpeed > 0){//UpWards
					int pixelsDown = (int)(this.player.hitbox.x - Point2.x);
					this.player.hitbox.y +=	pixelsDown-1;
				}
				else if(this.player.xSpeed < 0){//Downwards
					int pixelsDown = (int)(this.player.hitbox.x - Point2.x);
					this.player.hitbox.y -=	pixelsDown;
				}
			}


		}
	}
	// TODO:
	public void rightPlayerCollision(){
		if(this.player.hitbox.intersects(this.hitbox) && isInsideSlope()){
			System.out.println("RightPlayerCondition");
			if(isRightSlope && !upside){
				if(this.player.xSpeed > 0){//Upwards
					int pixelsUp = (int)(this.player.hitbox.x - Point2.x);
					this.player.hitbox.y +=	pixelsUp-1;
				}
				else if(this.player.xSpeed < 0){//DownWards
					int pixelsUp = (int)(this.player.hitbox.x - Point2.x);
					this.player.hitbox.y -=	pixelsUp;
				}
			}
			if(isRightSlope && upside){
				if(this.player.xSpeed > 0){//DownWards
					int pixelsDown = (int)(this.player.hitbox.x - Point2.x);
					this.player.hitbox.y -=	pixelsDown-1;
				}
				else if(this.player.xSpeed < 0){//Upwards
					int pixelsDown = (int)(this.player.hitbox.x - Point2.x);
					this.player.hitbox.y +=	pixelsDown;
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
		}
		else if ((this.isRightSlope && !this.upside) || (this.isLeftSlope && this.upside)){		//		/_ || /¬
			YCondition = (this.slope * this.Point1.x) + 24;
		}

		if(YCondition == 0){System.out.println("YCondition is 0");}
		System.out.println(YCondition);
		return YCondition;
	}

	public boolean isInsideSlope(){
		if((YCondition() >= (this.player.hitbox.y+this.player.hitbox.height+1)) && !this.upside){
			System.out.println("NotUpside Condition");
			return true;}
		
		else if((YCondition() >= (this.player.hitbox.y+this.player.hitbox.height)) && this.upside){
				System.out.println("Upside Condition");
			return true;}
		else{return false;}
	}
}
