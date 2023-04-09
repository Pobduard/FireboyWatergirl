package inputs;

import java.awt.event.*;
import maines.game;
import gamestates.GameStates;
public class MouseInputs implements MouseListener, MouseMotionListener{
	private game Mygame;

	public MouseInputs(game game) {
		this.Mygame = game;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		switch (GameStates.gamestate) {
			case MAINMENU:
				break;
			case LVLSELECTOR:
				break;
			case PAUSE:
				break;
			case RESTART:
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gamestate) {
			case MAINMENU:
				break;
			case LVLSELECTOR:
				break;
			case PAUSE:
				break;
			case RESTART:
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		switch (GameStates.gamestate) {
			case MAINMENU:
				this.Mygame.getMainMenu().MouseEntered(e);
				break;
			case LVLSELECTOR:
				break;
			case PAUSE:
				break;
			case RESTART:
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		switch (GameStates.gamestate) {
			case MAINMENU:
				this.Mygame.getMainMenu().MouseExited(e);
				break;
			case LVLSELECTOR:
				break;
			case PAUSE:
				break;
			case RESTART:
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gamestate) {
			case MAINMENU:
				this.Mygame.getMainMenu().mouseMoved(e);
				break;
			case LVLSELECTOR:
				break;
			case PAUSE:
				break;
			case RESTART:
				break;
			default:
				break;
		}
	}
	
}
