package Entities;

import java.awt.*;
import java.awt.image.*;
import maines.game;
import utilz.LoadImg;

public class Door extends Bloques{
	private BufferedImage Sprites[][] = new BufferedImage[2][6];
	private Player player;

	public Door(float x, float y, int height, int width, int id, boolean collision,  Player player) {
		super(x, y, height, width, id, collision);
		this.player = player;
		this.index = 0;
		createHitbox(x, y, width, height);
		initSprites();
	}

	public void update(){
		hasCollisioned();
		setAnimation();
	}

	private void initSprites(){
		BufferedImage doors = LoadImg.GetResizedImage(LoadImg.DoorsSprites, game.Tile_Size*14, game.Tile_Size*4);
		if(this.player.type == 0){
			for (int i = 0; i < this.Sprites.length; i++) {
				for (int j = 0; j < this.Sprites[i].length; j++) {
					this.Sprites[i][j] = doors.getSubimage(j*game.Tile_Size*2, i, game.Tile_Size*2, game.Tile_Size*2);
				}
			}
		}

		if(this.player.type == 1){
			for (int i = 1; i < this.Sprites.length; i++) {
				for (int j = 0; j < this.Sprites[i].length; j++) {
					this.Sprites[i][j] = doors.getSubimage(j*game.Tile_Size*2, i*game.Tile_Size*2, game.Tile_Size*2, game.Tile_Size*2);
				}
			}
		}
	}

	public void draw(Graphics g){
			if(this.id == 210 && this.player.type == 0){	//Mario
				g.drawImage(Sprites[0][(int)this.index], (int)this.hitbox.x, (int)this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height, null);}
			if(this.id == 20 && this.player.type == 1){		//Luigi
				g.drawImage(Sprites[1][(int)this.index], (int)this.hitbox.x, (int)this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height, null);}
	}

	private void hasCollisioned(){
		if(this.hitbox.intersects(this.player.hitbox)){
			this.collision = true;
		} else {this.collision = false;}
	}

	private void setAnimation(){
		if(this.collision){this.index+= 0.1f;}
		else{this.index-= 0.1f;}
		if(this.index <= 0f){this.index = 0f;}
		if(this.index >= 5f){this.index = 5f;}
	}
	
}
