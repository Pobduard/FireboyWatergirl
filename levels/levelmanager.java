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
		lvlOne = new leveldata(setLvlData(LoadImg.LEVEL_ONE_PIXELS));
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
				System.out.println(i+" "+j+" "+colorvalue);
				lvlData[i][j] = colorvalue;
			}
		}

		return lvlData;
	}

	public static Color checkPixelValue(int value){
		Color returnColor = Color.YELLOW;
		switch (value) {
			case 11:	//& Aire
				returnColor = Color.WHITE;
				break;
			case 13:	//& Bloque
				returnColor = Color.BLACK;
				break;
			case 39:	//& Rampa
				returnColor = Color.GRAY;
				break;
			case 25:	//& Agua
				returnColor = Color.BLUE;
				break;
			case 4:	//& Lava
				returnColor = Color.ORANGE;
				break;
			case 12:	//& Toxico
				returnColor = Color.GREEN;
				break;
			default:
				break;
		}

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
