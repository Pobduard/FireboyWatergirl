package gamestates;

public enum GameStates {
	PLAYING, MAINMENU, PAUSE, QUIT, PLAYERSELECTOR ,LVLSELECTOR, RESTART, LVLWON;

	public static GameStates gamestate = MAINMENU;
}
