package levels;

import Entities.*;

public class leveldata{
	private int lvlData[][];
	private Player player;
	
	/** Constructor para la clase {@link #leveldata(int[][])} 
	 * @see #Entities.Player()
	*/
	public leveldata(int[][] data, Player player){
		this.lvlData = data;
		this.player = player;
	}

	/** 
	 * Actualiza los datos correspondientes al nivel */
	public void update(){
		resetPlayerHitboxPos();
	}

	/** 
	 * @return {@code lvlData[][]}*/
	public int[][] getLvlData(){
		return this.lvlData;
	}


	/**
	 * @return {@code Player} del nivel correspondiente */
	private void resetPlayerHitboxPos(){
		if(!this.player.isAlive()){
			this.player.resetHitboxPos();
		}
	}

}
