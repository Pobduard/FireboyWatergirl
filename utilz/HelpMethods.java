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

						if(!IsMiddleSolid(x, (y+height), lvlData)){
								if(!IsMiddleSolid((x + width), (y + height), lvlData)){
										return true;
				
							}else {return false;}
						}else {return false;}

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

		//255 = Aire, 34 = Toxico, 153 = Agua, 237 = Lava 
		if(currentTile == 255 || currentTile == 34 || currentTile == 237 || currentTile == 153 
		|| currentTile == 220 || currentTile == 5 || currentTile == 84 || currentTile == 168 
		|| currentTile == 180 || currentTile == 235){
			return false;}
		else {return true;}
	}

	/**@return {@code false} solo si el tipo de "bloque" donde se encuentran {@code (x,y)} dentro del 
	 * nivel {@code lvlData[x][y]} es considerado como "no Solido" */
	public static boolean IsMiddleSolid(float x, float y, int[][] lvlData){
		int xMiddleTile = (int)(x / game.Tile_Size)/2;
		int yMiddleTile = (int)(y / game.Tile_Size)/2;

		//-1 por recordar que el Array Empieza en 0, tons llega hasta 39
		if(xMiddleTile > game.Game_Tiles_In_Width-1){
			xMiddleTile = game.Game_Tiles_In_Width-1;}

		//-1 por recordar que el Array Empieza en 0, tons llega hasta 29
		if(yMiddleTile > game.Game_Tiles_In_Height-1){
			yMiddleTile = game.Game_Tiles_In_Height-1;}

		int currentTile = lvlData[xMiddleTile][yMiddleTile];

		//255 = Aire, 34 = Toxico, 153 = Agua, 237 = Lava 
		if(currentTile == 255 || currentTile == 34 || currentTile == 237 || currentTile == 153 
		|| currentTile == 220 || currentTile == 5 || currentTile == 84 || currentTile == 168 
		|| currentTile == 180 || currentTile == 235){
			return false;}
		else {return true;}
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
