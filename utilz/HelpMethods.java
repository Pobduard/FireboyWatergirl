package utilz;

import java.awt.geom.Rectangle2D;

import maines.game;

/** Esta Clase es para los Metodos que multiples entidades pueden usar*/
public class HelpMethods {

	/**Usa la funcion {@link#IsSolid(float, float, int[][])} para determinar si es posble moverse a las coordenadas que se le pasan
	 * @return {@code true} si es posible moverse (no es Solido), {@code false} de lo contrario
	 */
	public static boolean CanMoveHere(float x, float y, int width, int height, int[][] lvlData){
		if(!IsSolid(x, y+height, lvlData)){
			if(!IsSolid(x + width, y + height, lvlData)){
				if(!IsSolid(x, y, lvlData)){
					if(!IsSolid(x + width, y, lvlData)){


						if (height >= game.Tile_Size && game.Tile_Size > width){
							if(!IsMiddleYSolid(x, y, width, height, lvlData)){
								return true;
							}else {return false;}
						
						} else if(width >= game.Tile_Size && game.Tile_Size > height){
							if(!IsMiddleXSolid(x, y, width, height, lvlData)){
								return true;
							}else {return false;}
						}
						else if(height >= game.Tile_Size && width >= game.Tile_Size){
							if(!IsMiddleYSolid(x, y, width, height, lvlData)){
								if(!IsMiddleXSolid(x, y, width, height, lvlData)){
									return true;
								}else {return false;}
							}else {return false;}
						}


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
		// if(x < 0 || x > game.Game_Width - 18){return true;}
		// if(y < 0 || y > game.Game_Height - 36){return true;}

		int xInsideTile = (int)x / game.Tile_Size;
		int yInsideTile = (int)y / game.Tile_Size;

		//-1 por recordar que el Array Empieza en 0, tons llega hasta 39
		if(xInsideTile > game.Game_Tiles_In_Width-1){
			xInsideTile = game.Game_Tiles_In_Width-1;}

		//-1 por recordar que el Array Empieza en 0, tons llega hasta 29
		if(yInsideTile > game.Game_Tiles_In_Height-1){
			yInsideTile = game.Game_Tiles_In_Height-1;}

		int currentTile = lvlData[xInsideTile][yInsideTile];
		switch (currentTile) {
			case 255: case 34: case 237: case 153: case 220: case 5: case 84: case 168: case 180: case 235: case 20: case 210:
				return false;
			default:
				return true;
		}
	}

	/**@return {@code false} solo si el tipo de "bloque" donde se encuentran {@code (x,y)} dentro del 
	 * nivel {@code lvlData[x][y]} y {@code lvlData[x][y+1]} son considerados como "no Solido" */
	public static boolean IsMiddleYSolid(float x, float y, int width, int height, int[][] lvlData){
		int xInsideTile = (int)x / game.Tile_Size;
		int yInsideTile = (int)y / game.Tile_Size;

		int UpTile = lvlData[xInsideTile][yInsideTile];
		int DownTile = lvlData[xInsideTile][yInsideTile+1];
		switch (UpTile) {
			case 255: case 34: case 237: case 153: case 220: case 5: case 84: case 168: case 180: case 235: case 20: case 210:
			switch (DownTile) {
				case 255: case 34: case 237: case 153: case 220: case 5: case 84: case 168: case 180: case 235: case 20: case 210:
					return false;
				default:
					return true;
			}
			default:
				return true;
		}
	}

	/**@return {@code false} solo si el tipo de "bloque" donde se encuentran {@code (x,y)} dentro del 
	 * nivel {@code lvlData[x][y]} y {@code lvlData[x+1][y]} son considerados como "no Solido" */
	public static boolean IsMiddleXSolid(float x, float y, int width, int height, int[][] lvlData){
		int xInsideTile = (int)x / game.Tile_Size;
		int yInsideTile = (int)y / game.Tile_Size;

		int LeftTile = lvlData[xInsideTile][yInsideTile];
		int RightTile = lvlData[xInsideTile+1][yInsideTile];
		switch (LeftTile) {
			case 255: case 34: case 237: case 153: case 220: case 5: case 84: case 168: case 180: case 235: case 20: case 210:
			switch (RightTile) {
				case 255: case 34: case 237: case 153: case 220: case 5: case 84: case 168: case 180: case 235: case 20: case 210:
					return false;
				default:
					return true;
			}
			default:
				return true;
		}
	}

	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		// Verificar si los pixeles, abajo-izquierda, y abajo-derecha son solidos, si no, estamos fuera de la plataforma
		if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))						//abajo-izquierda + 1, pal piso
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))	//abajo-derecha + 1, pal piso
				return false;
		return true;
	}

	public static float GetEntityXPosInsideTile(Rectangle2D.Float hitbox, float xSpeed){
		//Da igual si la velociadad es 0, si oo es, no es necesario alguna colision
		int xInsideTile = (int)(hitbox.x / game.Tile_Size);	//Encontrar en que tile estamos
		float tileXPos = xInsideTile * game.Tile_Size;		//Posicion al "inicio" del Tile 

		if(xSpeed > 0){		//Si es verdadero, vamos a la derecha, vamos en +
			float XReturn = 0f;
			if(hitbox.width >= game.Tile_Size){

				float xOffSet = (int)(game.Tile_Size - hitbox.width); //Negativo
				XReturn = tileXPos + hitbox.width + (xOffSet*2) - 0.1f;
				return XReturn;
			}else{
				float xOffSet = (int)(game.Tile_Size - hitbox.height);
				XReturn = tileXPos + xOffSet - 0.1f;
				return  XReturn;
				}
		}else {
			return tileXPos;
			}
	}

	public static float GetEntityYPosInsideTile(Rectangle2D.Float hitbox, float airSpeed) {
		int yInsideTile = (int)(hitbox.y / game.Tile_Size);	//Encontrar en que tile estamos
		float tileYPos = yInsideTile * game.Tile_Size;		//Posicion al "inicio" del Tile 
		
		if(airSpeed > 0){
			float YReturn = 0f;
			// Falling - Touching Floor
			if(hitbox.height >= game.Tile_Size){

				float yOffSet = (int)(game.Tile_Size - hitbox.height); //Negativo
				YReturn = tileYPos + hitbox.height + (yOffSet*2) - 0.1f;
				return YReturn;
			}else{
				float yOffSet = (int)(game.Tile_Size - hitbox.height);
				YReturn = tileYPos + yOffSet - 0.1f;
				return  YReturn;
				}
		}else {
			return tileYPos;
			}
	}
}
