package levels;

import Entities.*;
import java.awt.*;

public class leveldata{
	private int lvlData[][];
	private Bloques objData[][];
	private Player player;
	
	/** Constructor para la clase {@link #leveldata(int[][])} 
	 * @see #Entities.Player()
	*/
	public leveldata(int[][] data, Bloques[][] obj){
		this.lvlData = data;
		this.objData = obj;
	}

	/** 
	 * Actualiza los datos correspondientes al nivel */
	public void update(){
		resetPlayerHitboxPos();
	}

	public void draw(Graphics g){
		for (int i = 0; i < this.objData.length; i++) {
			for (int j = 0; j < this.objData[i].length; j++) {
				if(this.objData[i][j] != null){
					this.objData[i][j].draw(g);}
			}
		}
	}

	/** 
	 * @return {@code lvlData[][]}*/
	public int[][] getLvlData(){
		return this.lvlData;
	}

	public Bloques[][] getObjData(){
		return this.objData;
	}

	/**
	 * @return {@code Player} del nivel correspondiente */
	private void resetPlayerHitboxPos(){
		if(!this.player.isAlive()){
			this.player.resetHitboxPos();
		}
	}

	public void setPlayer(Player player){
		this.player = player;
	}

	public Object isActive(){
		return this.getClass();
	}
}
