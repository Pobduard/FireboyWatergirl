package Entities;

import java.awt.*;
import java.awt.geom.Point2D;

import maines.game;

public class Slope extends Bloques{
	boolean isRightSlope = false, isLeftSlope = false, upside = false;
	float slope = 0;
	Point2D.Float Point1, Point2;
	Player player;
	public Slope(float x, float y, int height, int width, int id, boolean collision, Player player) {
		super(x, y, height, width, id, collision);
		createHitbox(x, y, width, height);
		this.player = player;
		checkType(id);
		initPoint();
		calculateSlope();
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
			this.isLeftSlope = true;
		}
		else if(id == 168){/* 	Right	 */
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
			float FarPercentage = this.player.hitbox.x / game.Tile_Size;
			this.player.hitbox.y = FarPercentage * game.Tile_Size;
		}
	}
	// TODO:
	public void rightPlayerCollision(){
		if(this.player.hitbox.intersects(this.hitbox) && isInsideSlope()){
			float FarPercentage = (this.player.hitbox.x+this.player.hitbox.width) /game.Tile_Size;
			this.player.hitbox.y = FarPercentage * game.Tile_Size;
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
		return YCondition;
	}

	public boolean isInsideSlope(){
		if((YCondition() >= (this.player.hitbox.y+this.player.hitbox.height)) && !this.upside){
			return true;}
		
		else if(!(YCondition() >= (this.player.hitbox.y+this.player.hitbox.height)) && this.upside){
			return true;}
		else{return false;}
	}
}