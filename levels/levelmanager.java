package levels;

import maines.game;
import utilz.LoadImg;

import java.awt.*;
import java.awt.image.*;

public class levelmanager {
	private game Mygame;
	private BufferedImage[] levelSprites;
	private leveldata lvlOne, lvlTwo;

	public levelmanager(game game){
		this.Mygame = game;
		lvlOne = new leveldata(setLvlData(LoadImg.LEVEL_ONE_PIXIL));
		lvlTwo = new leveldata(setLvlData(LoadImg.LEVEL_ONE_PIXELS));
	}

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

	public static Color checkPixelValue(int value){
		Color returnColor = Color.LIGHT_GRAY;

		switch (value) {
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

	public void draw(Graphics g, int[][] currentLvl){
		for (int i = 0; i < game.Game_Tiles_In_Width; i++) {
			for (int j = 0; j < game.Game_Tiles_In_Height; j++) {
				g.setColor(checkPixelValue(currentLvl[i][j]));
				g.fillRect(i * game.Tile_Size, j*game.Tile_Size, game.Tile_Size, game.Tile_Size);
			}
		}
	}
}
