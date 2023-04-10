package levels;

import Entities.*;
import java.awt.*;

public class leveldata{
	private int lvlData[][];
	private Bloques objData[][];
	private Player player;
	public boolean isWin;
	
	/** Constructor para la clase {@link #leveldata}
	 * @see Entities.Player
	 * @param data
	 * @param obj
	*/
	public leveldata(int[][] data, Bloques[][] obj){
		this.lvlData = data;
		this.objData = obj;
	}

	/** 
	 * Actualiza los datos correspondientes al nivel */
	public void update(){
		resetPlayerHitboxPos();
		hasWon();
	}

	public void draw(Graphics g){
		for (int i = 0; i < this.objData.length; i++) {
			for (int j = 0; j < this.objData[i].length; j++) {
				if(this.objData[i][j] != null){
					this.objData[i][j].draw(g);}
			}
		}
	}

	public void hasWon(){
		for (int i = 0; i < this.objData.length; i++) {
			for (int j = 0; j < this.objData[i].length; j++) {
				if(this.objData[i][j] != null){
					if(this.player.type == 0 && this.objData[i][j].id == 210){	//Is Mario's Door
						if((this.objData[i][j].collision == true) && (this.objData[i][j].index) >= 5f){this.isWin = true;}
					}
					if(this.player.type == 1 && this.objData[i][j].id == 20){	//Is Luigi's Door
					if((this.objData[i][j].collision == true) && (this.objData[i][j].index) >= 5f){this.isWin = true;}
					}
				}
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
