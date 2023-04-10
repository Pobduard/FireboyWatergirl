package inputs;
import java.awt.event.*;

import gamestates.GameStates;
import maines.game;

/**
 * Esta clase se encarga de manejar lo relacionado a eventos por teclado
 */

public class KeyInputs implements KeyListener {
	private game Mygame;

	public KeyInputs(game game) {
		this.Mygame = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (GameStates.gamestate) {
			case PLAYING:
				this.Mygame.getPlaying().KeyPressed(e);
				break;
			case MAINMENU:
				this.Mygame.getMainMenu().KeyPressed(e);
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (GameStates.gamestate) {
			case PLAYING:
				this.Mygame.getPlaying().KeyReleased(e);
				break;
			case MAINMENU:
				this.Mygame.getMainMenu().KeyReleased(e);
				break;
			default:
				break;
		}
	}
}
