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

		//255 = Aire, 34 = Toxico, 153 = Agua, 237 = Lava 
		if(currentTile == 255 || currentTile == 34 || currentTile == 237 || currentTile == 153 || currentTile == 220 || currentTile == 5){
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

	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){
		//Da igual si la velociadad es 0, si oo es, no es necesario alguna colision
		int currentTile = (int)(hitbox.x / game.Tile_Size);	//Encontrar en que tile estamos

		if(xSpeed > 0){		//Si es verdadero, vamos a la derecha, vamos en +
			int tileXPos = currentTile * game.Tile_Size;		//Asi encontramos que pixel especifico del Tile actual
			int xOffSet = (int)(game.Tile_Size - hitbox.width);	//Cuanta diferencia hay entre el tile y el jugador
			return tileXPos + xOffSet - 1;		//Regresar la posicion en la cual esta la colision, -1 porque si no nos pasaremos al siguiente Tile

		}	else {			//Izquierda
			return currentTile * game.Tile_Size;		//Izquierda, el inicio, tons ya esta por default
		}
	}

	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int)(hitbox.y / game.Tile_Size);	//Encontrar en que tile estamos
		if(airSpeed > 0){
			// Falling - Touching Floor
			int tileYPos = currentTile * game.Tile_Size;
			int yOffSet = (int)(game.Tile_Size - hitbox.height);

			return  tileYPos + yOffSet - 1;

		}else {
			// Jumping
			return currentTile * game.Tile_Size;
		}
	}

}
