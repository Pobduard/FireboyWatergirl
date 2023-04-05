package utilz;

import maines.game;

/** Esta Clase es para los Metodos que muultiples entidades pueden usar*/
public class HelpMethods {

	/**Usa la funcion {@link#IsSolid(float, float, int[][])} para determinar si es posble moverse a las coordenadas que se le pasan
	 * @return {@code true} si es posible moverse (no es Solido), {@code false} de lo contrario
	 */
	public static boolean CanMoveHere(float x, float y, int width, int height, int[][] lvlData){
		if(!IsSolid(x, y+height, lvlData)){
			if(!IsSolid(x + width, y + height, lvlData)){
				if(!IsSolid(x, y, lvlData)){
					if(!IsSolid(x + width, y, lvlData)){
						return true;

					}else {return false;}
				}else {return false;}
			}else {return false;}
		}else {return false;}
	}
	
	/**@return {@code false} solo si el tipo de "bloque" donde se encuentran {@code (x,y)} dentro del 
	 * nivel {@code lvlData[x][y]} es considerado como "no Solido" */
	public static boolean IsSolid(float x, float y, int[][] lvlData){
		if(x < 0 || x > game.Game_Width){return true;}
		if(y < 0 || y > game.Game_Height){return true;}

		int xInsideTile = (int)x / game.Tile_Size;
		int yInsideTile = (int)y / game.Tile_Size;

		//-1 por recordar que el Array Empieza en 0, tons llega hasta 39
		if(xInsideTile > game.Game_Tiles_In_Width-1){
			xInsideTile = game.Game_Tiles_In_Width-1;}

		//-1 por recordar que el Array Empieza en 0, tons llega hasta 29
		if(yInsideTile > game.Game_Tiles_In_Height-1){
			yInsideTile = game.Game_Tiles_In_Height-1;}

		int currentTile = lvlData[xInsideTile][yInsideTile];

		//255 = Aire, 34 = Toxico, 153 = Agua, 237 = Lava 
		if(currentTile == 255 || currentTile == 34 || currentTile == 237 || currentTile == 153){
			return false;}
		else {return true;}
	}

}
