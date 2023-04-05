package levels;

import Entities.*;
import maines.game;

public class leveldata{
	private int lvlData[][];
	private float xFireStarter, yFireStarter;
	private float xWaterStarter, yWaterStarter;
	private Player player;
	
	/** Constructor para la clase {@link #leveldata(int[][])} 
	 * @param xFireStarter Posicion en la X donde se generara el FireBoy
	 * @param yFireStarter Posicion en la Y donde se generara el FireBoy
	 * @param xWaterStarter Posicion en la X donde se generara la WaterGirl
	 * @param yWaterStarter Posicion en la Y donde se generara la WaterGirl
	*/
	public leveldata(int[][] data, float xFireStarter, float yFireStarter, float xWaterStarter, float yWaterStarter){
		this.lvlData = data;
		this.xFireStarter = xFireStarter;
		this.yFireStarter = yFireStarter;
		this.xWaterStarter = xWaterStarter;
		this.yWaterStarter = yWaterStarter;
		createPlayer(0);
	}

	/** 
	 * Actualiza los datos correspondientes al nivel */
	public void update(){
		this.player.update();
		resetPlayerHitboxPos();
	}

	/** 
	 * @return {@code lvlData[][]}*/
	public int[][] getLvlData(){
		return this.lvlData;
	}

	/** 
	 * Crea el {@code Player} del nivel */
	private void createPlayer(int playerType){
		// 0 = FireBoy
		if(playerType == 0){this.player = new Player(xFireStarter, yFireStarter, game.Tile_Size, (game.Tile_Size*2), this);}
		// 1 = WaterGirl
		if(playerType == 1){this.player = new Player(xWaterStarter, yWaterStarter, game.Tile_Size, (game.Tile_Size*2), this);}
		
	}

	/**
	 * @return {@code Player} del nivel correspondiente */
	private void resetPlayerHitboxPos(){
		if(!this.player.isAlive()){
			this.player.resetHitboxPos();
		}
	}

}
