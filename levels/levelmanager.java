package levels;

import maines.game;
import utilz.LoadImg;

import java.awt.*;
import java.awt.image.*;

/** 
 * Clase Principal para manejar todos los carros correspondientes a un nivel */
public class levelmanager {
	private game Mygame;
	private BufferedImage[] levelSprites;
	private int lvl;
	private leveldata lvlOne, lvlTwo;

	/** Constructor para la clase {@link #levelmanager(game)} */
	public levelmanager(game Mygame){
		this.Mygame = Mygame;
		lvlOne = new leveldata(setLvlData(LoadImg.LEVEL_ONE_PIXIL), (game.Tile_Size*2), (game.Game_Height - game.Tile_Size*2 + 2),
		(game.Tile_Size*2), (game.Game_Height - game.Tile_Size*6 + 2));
		lvlTwo = new leveldata(setLvlData(LoadImg.LEVEL_ONE_PIXELS), (game.Tile_Size*2), (game.Game_Height - game.Tile_Size*2 + 2),
		(game.Tile_Size*2), (game.Game_Height - game.Tile_Size*2 + 2));
		this.lvl = 1;
	}

	/** 
	 * @return El Objeto {@link leveldata} en que nos encontramos */
	public leveldata getCurrentLvl(int lvl){
		if(lvl == 2){return lvlTwo;}
		return lvlOne;
	}

	/** 
	 * @return La {@code lvlData[][]} del {@link leveldata} en que nos encontramos */
	public int[][] getLeveldata(int lvl){
		int[][] returlvl = null;
		switch (lvl) {
			case 1:
				returlvl = this.lvlOne.getLvlData();
				break;
			case 2:
				returlvl =  this.lvlTwo.getLvlData();
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

	/** 
	 * Para Debuggin, Dependiendo de el valor leido en {@code lvlData[][]} imprime en pantalla un bloque de color predeterminado
	 * @see java.awt.color*/
	public static Color checkPixelValue(int valor){
		Color returnColor = Color.LIGHT_GRAY;

		switch (valor) {
			case 255:	//& Aire
				returnColor = Color.WHITE;
				break;
			case 156:	//& Bloque
				returnColor = Color.BLACK;
				break;
			case 168:	//& Rampa
			case 235:	//& Rampa
			case 84:	//& Rampa
			case 180:	//& Rampa
				returnColor = Color.MAGENTA;
				break;
			case 153:	//& Agua
				returnColor = Color.BLUE;
				break;
			case 237:	//& Lava
				returnColor = Color.RED;
				break;
			case 34:	//& Toxico
				returnColor = Color.GREEN;
				break;
			case 5:	//& Diamante
				returnColor = Color.CYAN;
				break;
			case 220:	//& Ruby
				returnColor = new Color(220,114,35);
				break;
			case 50:	//& Boton
				returnColor = new Color(156,90,60);
				break;
			case 150:	//& PiedraMovible
				returnColor = Color.darkGray;
				break;
			case 211:	//& Ruby
			case 111:	//& Ruby
				returnColor = Color.PINK;
				break;
			default:
				break;
		}


		// switch (value) {
		// 	case 255:	//& Aire
		// 		returnColor = Color.WHITE;
		// 		break;
		// 	case 0:	//& Bloque
		// 		returnColor = Color.BLACK;
		// 		break;
		// 	case 127:	//& Rampa
		// 		returnColor = Color.GRAY;
		// 		break;
		// 	case 128:	//& Agua
		// 		returnColor = Color.BLUE;
		// 		break;
		// 	case 200:	//& Lava
		// 		returnColor = Color.RED;
		// 		break;
		// 	case 39:	//& Toxico
		// 		returnColor = Color.GREEN;
		// 		break;
		// 	default:
		// 		break;
		// }

		return returnColor;
	}

	/** 
	 * Actualiza Todos los datos correspondientes al Nivel en el que nos encontramos
	 * @see #getCurrentLvl(int)*/
	public void update(){
		getCurrentLvl(this.lvl).update();
	}

	/** 
	 * Dibuja Todo lo Relacionado al Nivel en el que nos encontramos 
	 * @see #getCurrentLvl(int)
	*/
	public void draw(Graphics g){
		drawlvldebug(g, this.lvl);
	}

	/** 
	 * Dibuja el Nivel en el que nos encontramos 
	 * @see #getCurrentLvl(int)
	*/
	public void drawlvldebug(Graphics g, int lvl){
		int[][] currentLvl = getLeveldata(lvl); 
		for (int i = 0; i < game.Game_Tiles_In_Width; i++) {
			for (int j = 0; j < game.Game_Tiles_In_Height; j++) {
				g.setColor(checkPixelValue(currentLvl[i][j]));
				g.fillRect(i * game.Tile_Size, j*game.Tile_Size, game.Tile_Size, game.Tile_Size);
			}
		}
	}

	/** 
	 * Selecciona el nivel a jugar */
	public void setLvl(int lvl){
		this.lvl = lvl;
	}
}
