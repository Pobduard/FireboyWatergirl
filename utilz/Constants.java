package utilz;

/**
 * Esta clase almacena los valores de constantes que seran usadas durante la creacion del videojuego
 */
public class Constants {
	public static class PlayerConstants{
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALL = 3;

		/**
		 *
		 * @param player_action
		 * @return Retorna la cantidad de imagenes que tiene esa animacion
		 */
		public static int GetSpriteAmount(int player_action){
			switch(player_action){
				case IDLE:
					return 1;
				case RUNNING:
					return 8;
				case JUMP:
					return 1;
				case FALL:
					return 1;
				default:
					return 1;
			}
		}
	}

	public static class MainMenuButton{
		public static final int ButtonWidth = 140, ButtonHeight = 56;
	}

	public static class LiquidsConstants{
		public static int LAVA_FLOW = 0;
		public static int LAVA_RIGHT_SIDE = 1;
		public static int LAVA_LEFT_SIDE = 2;
		public static int WATER_FLOW = 3;
		public static int WATER_RIGHT_SIDE = 4;
		public static int WATER_LEFT_SIDE = 5;
		public static int TOXIC_FLOW = 6;
		public static int TOXIC_RIGHT_SIDE = 7;
		public static int TOXIC_LEFT_SIDE = 8;
		
		public static int SpriteAmount = 14;


	}

}
