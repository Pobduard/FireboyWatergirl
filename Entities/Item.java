package Entities;

import java.awt.*;
import java.awt.image.*;
import maines.game;
import utilz.LoadImg;

public class Item extends Bloques {
	private BufferedImage Sprites[] = new BufferedImage[2];
	private Player player;

	public Item(float x, float y, int height, int width, int id, boolean collision, Player player) {
		super(x, y, height, width, id, collision);
		this.player = player;
		createHitbox(x, y, width, height);
		initSprites();
	}
	
	public void update(){
		hasCollisioned();
	}

    public void draw(Graphics g){
		if(this.collision == false){
			if(this.id == 220 && this.player.type == 0){	//Mario
				g.drawImage(Sprites[0], (int)this.hitbox.x, (int)this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height, null);}
			if(this.id == 5 && this.player.type == 1){		//Luigi
				g.drawImage(Sprites[1], (int)this.hitbox.x, (int)this.hitbox.y, (int)this.hitbox.width, (int)this.hitbox.height, null);}
			}
	}

	private void initSprites(){
		BufferedImage items = LoadImg.GetResizedImage(LoadImg.Items, game.Tile_Size*2, game.Tile_Size);
		for (int i = 0; i < this.Sprites.length; i++) {
			this.Sprites[i] = items.getSubimage(i*game.Tile_Size, 0, game.Tile_Size, game.Tile_Size);
		}
	}

	private void hasCollisioned(){
		if(this.hitbox.intersects(this.player.hitbox)){
			this.collision = true;
		}
	}

	public void resetColision(){
		this.collision = false;
	}
}
