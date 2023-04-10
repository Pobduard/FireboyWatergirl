package levels;

import maines.game;
import utilz.LoadImg;

import java.awt.*;
import java.awt.image.*;

import Entities.*;
import gamestates.GameStates;
import gamestates.LevelStates;

/** 
 * Clase Principal para manejar todos los datos correspondientes a un nivel */
public class levelmanager {
	BufferedImage tile[] = new BufferedImage[4];
	BufferedImage liquid[][] = new BufferedImage[9][15];
	BufferedImage item[] = new BufferedImage[2];
	BufferedImage background, spikes;
	LevelStates currentstate = LevelStates.LVL1;
	private Bloques objData[][];
	private leveldata lvlOne, lvlTwo, lvlTre;
	private Player player;
	private Object currentObjData = null;
	private final int playerWidth = game.Tile_Size+8, playerHeight = game.Tile_Size+16;

	/** Constructor para la clase {@link #levelmanager} */
	public levelmanager(){
		initSprites();
		this.player = new Player((game.Tile_Size*2), (game.Game_Height-(game.Tile_Size*3+2)), playerWidth, playerHeight, null, 1);
		lvlOne = new leveldata(setLvlData(LoadImg.LEVEL_ONE_PIXIL), setObjData(LoadImg.LEVEL_ONE_PIXIL));
		lvlTwo = new leveldata(setLvlData(LoadImg.LEVEL_TWO_PIXELS), setObjData(LoadImg.LEVEL_TWO_PIXELS));
		lvlTre = new leveldata(setLvlData(LoadImg.LEVEL_TRE), setObjData(LoadImg.LEVEL_TRE));

		this.currentObjData = lvlOne.getObjData();
	}

	public void isLvlWon(){
		if(LevelStates.levelstate == LevelStates.LVL1){
			if(lvlOne.isWin){GameStates.gamestate = GameStates.LVLWON;
				this.player.xSpeed = 0;
				lvlOne.isWin = false;}
		}
		if(LevelStates.levelstate == LevelStates.LVL2){
			if(lvlTwo.isWin){GameStates.gamestate = GameStates.LVLWON;
				this.player.xSpeed = 0;
				lvlTwo.isWin = false;}
		}
		if(LevelStates.levelstate == LevelStates.LVL3){
			if(lvlTre.isWin){GameStates.gamestate = GameStates.LVLWON;
				this.player.xSpeed = 0;
				lvlTre.isWin = false;}
		}
	}

	/** 
	 * @return La {@code lvlData[][]} del {@link leveldata} en que nos encontramos 
	 * @see gamestates.LevelStates
	 * */
	public int[][] getLeveldata(){
		int[][] returlvl = null;
		switch (LevelStates.levelstate) {
			case LVL1:
				returlvl = this.lvlOne.getLvlData();
				break;
			case LVL2:
				returlvl =  this.lvlTwo.getLvlData();
				break;
			case LVL3:
				returlvl =  this.lvlTre.getLvlData();
				break;
		
			default:
				break;
		}
		return returlvl;
	}

	/** 
	 * @return La {@code lvlData[][]} dependiendo de la lectura de los pixeles de la imagen
	 * @param lvlImgPath La Direccion en {@code String} de la imagen de pixeles que representa el nivel*/
	public int[][] setLvlData(String lvlImgPath){
		BufferedImage img = LoadImg.GetImage(lvlImgPath);
		int[][] lvlData = new int[img.getWidth()][img.getHeight()];

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color pixelcolor = new Color(img.getRGB(i, j));
				int colorvalue = pixelcolor.getRed();
				lvlData[i][j] = colorvalue;
			}
		}

		return lvlData;
	}

	private Bloques initialiceObj(float x, float y, int id, int i, int j){
		if(id == 84){return new Slope(x, y, game.Tile_Size, game.Tile_Size, id, false, getPlayer());} 
		else if(id == 168){return new Slope(x, y, game.Tile_Size, game.Tile_Size, id, false, getPlayer());} 
		else if(id == 180){return new Slope(x, y, game.Tile_Size, game.Tile_Size, id, false, getPlayer());}
		else if(id == 235){return new Slope(x, y, game.Tile_Size, game.Tile_Size, id, false, getPlayer());}
		else if(id == 5){return new Item(x, y, game.Tile_Size, game.Tile_Size, id, false, getPlayer());}
		else if(id == 220){return new Item(x, y, game.Tile_Size, game.Tile_Size, id, false, getPlayer());}
		else if(id == 20){return new Door(x, y, game.Tile_Size*2, game.Tile_Size*2, id, false, getPlayer());}
		else if(id == 210){return new Door(x, y, game.Tile_Size*2, game.Tile_Size*2, id, false, getPlayer());}
		else {return null;}
		
		// switch (id) {
		// 	case 156:	//& Bloque
		// 		break;
		// 	case 5:	//& Diamante
		// 		break;
		// 	case 220:	//& Ruby
		// 		break;
		// 	case 50:	//& Boton
		// 		break;
		// 	case 150:	//& PiedraMovible
		// 		break;
		// 	case 211:	//& Ruby
		// 	case 111:	//& Ruby
		// 		break;
		// 	default:
		// 		break;
		// }
	}

	/** 
	 * @return La {@code lvlData[][]} dependiendo de la lectura de los pixeles de la imagen
	 * @param lvlImgPath La Direccion en {@code String} de la imagen de pixeles que representa el nivel*/
	public Bloques[][] setObjData(String lvlImgPath){
		BufferedImage img = LoadImg.GetImage(lvlImgPath);
		Bloques[][] objData = new Bloques[img.getWidth()][img.getHeight()];

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color pixelcolor = new Color(img.getRGB(i, j));
				int colorvalue = pixelcolor.getRed();
				checkPixelValue(colorvalue);
				objData[i][j] = initialiceObj(i*game.Tile_Size, j*game.Tile_Size, colorvalue, i, j);
			}
		}

		return objData;
	}

	/** 
	 * Para Debuggin, Dependiendo de el valor leido en {@code lvlData[][]} imprime en pantalla un bloque de color predeterminado
	 * @see java.awt.Color*/
	public static Color checkPixelValue(int valor){
		Color returnColor = Color.LIGHT_GRAY;
		switch (valor) {
			case 255:	//& Aire
				returnColor = Color.WHITE;
				break;
			case 156:	//& Bloque
				returnColor = Color.BLACK;
				break;
			case 84:	//& Rampa	_\
				returnColor = new Color(84, 109, 142);
				break;
			case 168:	//& Rampa	/_
				returnColor = new Color(168, 230, 29);
				break;
			case 180:	//& Rampa		־/
				returnColor = new Color(180, 165, 213);
				break;
			case 235:	//& Rampa		\־
				returnColor = new Color(235, 194, 14);
				break;
			case 153:	//& Toxico
				returnColor = Color.BLUE;
				break;
			case 237:	//& Lava
				returnColor = Color.RED;
				break;
			case 34:	//& Spikes
				returnColor = Color.GREEN;
				break;
			case 5:		//& HongoVerde
				returnColor = new Color(5,183,239);
				break;
			case 220:	//& HongoRojo
				returnColor = new Color(220,114,35);
				break;
			case 50:	//& Boton
				returnColor = new Color(156,90,60);
				break;
			case 20:	//& Puerta Luigi
				returnColor = new Color(20,0,0);
				break;
			case 210:	//& Puerta Mario
				returnColor = new Color(210,0,0);
				break;
			case 150:	//& PiedraMovible
				returnColor = Color.darkGray;
				break;
			default:
				break;
		}

		return returnColor;
	}
	
	/** 
	 *@return El Valor ubicado en {@code lvlData[][]} 
	 * @see java.awt.Color*/
	public static int getPixelValue(int i, int j, int[][] lvlData){
		return lvlData[i][j];
	}

	/** 
	 * Actualiza Todos los datos correspondientes al Nivel en el que nos encontramos
	 * @see gamestates.LevelStates*/
	public void update(){
		if(GameStates.gamestate == GameStates.PLAYING){		
		LevelStates Verify = currentstate;
			setPlayers();
			if(LevelStates.levelstate == LevelStates.LVL1){
				currentstate = LevelStates.LVL1;
				if(player.type == 0){
					player.changeXYPos((game.Tile_Size*2), (game.Game_Height-(game.Tile_Size*3+2)));
				}
				if(player.type == 1){
					player.changeXYPos((game.Tile_Size*2), (game.Game_Height-(game.Tile_Size*7+2)));
				}
				player.setlvl(lvlOne);
				player.update();
				refresObjData();
				lvlOne.update();
				isLvlWon();}
			if(LevelStates.levelstate == LevelStates.LVL2){
				currentstate = LevelStates.LVL2;
				if(player.type == 0){
					player.changeXYPos((game.Tile_Size*2), (game.Game_Height-(game.Tile_Size*3)));
				}
				if(player.type == 1){
					player.changeXYPos((game.Tile_Size*4), (game.Game_Height-(game.Tile_Size*3)));
				}
				player.setlvl(lvlTwo);
				player.update();
				refresObjData();
				lvlTwo.update();
				isLvlWon();}
			if(LevelStates.levelstate == LevelStates.LVL3){
				currentstate = LevelStates.LVL3;
				player.changeXYPos(3, (game.Game_Height-(game.Tile_Size*2)));
				player.setlvl(lvlTre);
				player.update();
				refresObjData();
				lvlTre.update();
				isLvlWon();}
		if(Verify != currentstate){
			player.resetHitboxPos();
		}
		}
	}

	/** 
	 * Dibuja Todo lo Relacionado al Nivel en el que nos encontramos 
	 * @see gamestates.LevelStates
	*/
	public void draw(Graphics g){
		if(GameStates.gamestate == GameStates.PLAYING){		
			drawlvl(g);
			if(LevelStates.levelstate == LevelStates.LVL1){
				lvlOne.draw(g);}
			if(LevelStates.levelstate == LevelStates.LVL2){
				lvlTwo.draw(g);}
			if(LevelStates.levelstate == LevelStates.LVL3){
				lvlTre.draw(g);}
			player.draw(g);
		}
	}

	/** 
	 * Dibuja el Nivel en el que nos encontramos 
	 * @see gamestates.LevelStates
	*/
	public void drawlvl(Graphics g){
		g.drawImage(background, 0, 0, game.Game_Width, game.Game_Height, null);
		int[][] currentLvl = getLeveldata(); 
		for (int i = 0; i < game.Game_Tiles_In_Width; i++) {
			for (int j = 0; j < game.Game_Tiles_In_Height; j++) {
				//TODO: DELETE AT THE END
				g.setColor(checkPixelValue(currentLvl[i][j]));
				g.fillRect(i * game.Tile_Size, j*game.Tile_Size, game.Tile_Size, game.Tile_Size);

				if(getPixelValue(i, j, currentLvl) == 156){
					g.drawImage(tile[0], i * game.Tile_Size, j*game.Tile_Size, game.Tile_Size, game.Tile_Size, null);}
				if(getPixelValue(i, j, currentLvl) == 150){
					g.drawImage(tile[1], i * game.Tile_Size, j*game.Tile_Size, game.Tile_Size, game.Tile_Size, null);}
				if(getPixelValue(i, j, currentLvl) == 34){
					g.drawImage(spikes, i * game.Tile_Size, j*game.Tile_Size, game.Tile_Size, game.Tile_Size, null);}
			}
		}
	}

	public void initSprites(){
		this.background = LoadImg.GetResizedImage(LoadImg.Background1, game.Game_Width, game.Game_Height);
		this.spikes = LoadImg.GetResizedImage(LoadImg.SpikesSprites, game.Tile_Size, game.Tile_Size);
		BufferedImage tiles = LoadImg.GetResizedImage(LoadImg.TilesSprite, game.Tile_Size*4, game.Tile_Size);
		for (int i = 0; i < tile.length; i++) {
			this.tile[i] = tiles.getSubimage(i*game.Tile_Size, 0, game.Tile_Size, game.Tile_Size);
		}
		BufferedImage liquids = LoadImg.GetResizedImage(LoadImg.LiquidSprites, game.Tile_Size*15, game.Tile_Size*9);
		for (int i = 0; i < liquid.length; i++) {
			for (int j = 0; j < liquid[i].length; j++) {
				this.liquid[i][j] = liquids.getSubimage(i*game.Tile_Size, j, game.Tile_Size, game.Tile_Size);
			}
		}
	}

	public void setPlayers(){
		lvlOne.setPlayer(this.player);
		lvlTwo.setPlayer(this.player);
		lvlTre.setPlayer(this.player);
	}

	/** 
	 * @return {@code Player} de la clase */
	public Player getPlayer(){
		return this.player;
	}

	public boolean ObjDataActive(){
		Object ndObjData = null;
		if(GameStates.gamestate == GameStates.PLAYING){		
			if(LevelStates.levelstate == LevelStates.LVL1){
				ndObjData = lvlOne.isActive();}
			if(LevelStates.levelstate == LevelStates.LVL2){
				ndObjData = lvlTwo.isActive();}
			if(LevelStates.levelstate == LevelStates.LVL3){
				ndObjData = lvlTre.isActive();}
		}
		if(currentObjData.equals(ndObjData)){return true;}
		else{return false;}
	}

	public void refresObjData(){
		if(!ObjDataActive()){
			this.objData = getCurrentObjData();}
		updateObjData();
	}

	private Bloques[][] getCurrentObjData(){
		Bloques[][] returnObj = null;
		switch (LevelStates.levelstate) {
			case LVL1:
				returnObj = this.lvlOne.getObjData();
				break;
			case LVL2:
				returnObj =  this.lvlTwo.getObjData();
				break;
			case LVL3:
				returnObj =  this.lvlTre.getObjData();
				break;
		
			default:
				break;
		}
		return returnObj;
	}

	private void updateObjData(){
		for (int i = 0; i < this.objData.length; i++) {
			for (int j = 0; j < this.objData[i].length; j++) {
				if(this.objData[i][j] != null){
					this.objData[i][j].update();}
			}
		}
	}
}
