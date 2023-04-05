package inputs;
import java.awt.event.*;

import gamestates.gamestates;
import maines.game;

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
		switch (gamestates.gamestate) {
			case PLAYING:
				this.Mygame.getPlaying().KeyPressed(e);;
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (gamestates.gamestate) {
			case PLAYING:
				this.Mygame.getPlaying().KeyReleased(e);
				break;
			default:
				break;
		}
	}
}
