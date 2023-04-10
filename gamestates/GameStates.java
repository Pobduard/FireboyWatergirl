package gamestates;

public enum GameStates {
	PLAYING, MAINMENU, PAUSE, QUIT, LVLSELECTOR, RESTART, LVLWON;

	public static GameStates gamestate = MAINMENU;
}
