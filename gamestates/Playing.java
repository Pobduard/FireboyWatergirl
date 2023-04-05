package gamestates;

import java.awt.Graphics;
import java.awt.event.*;

import Entities.Player;
import maines.game;

public class Playing implements StateMethods{
	private game Mygame;
	private Player player;
	public Playing(game Mygame) {
		this.Mygame = Mygame;
		this.player = Mygame.getPanel().getLvlManager().getPlayer();
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			//+LEFT
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				System.out.println("Left");
				this.player.setLeft(true);
				break;
			//+RIGHT
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				this.player.setRight(true);
				System.out.println("Right");
				break;
			//+ Espacio
			case KeyEvent.VK_SPACE:
				this.player.setJump(true);
				break;
			//+ Escape
			case KeyEvent.VK_ESCAPE:
				// paused = !paused;		//Siempre sera lo contrario
				break;
			default:
				break;
		}
		System.out.println(e.getKeyCode());
	}

	@Override
	public void KeyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			//+LEFT
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				this.player.setLeft(false);
				break;
			//+RIGHT
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				this.player.setRight(false);
				break;
			//+ Espacio
			case KeyEvent.VK_SPACE:
				this.player.setJump(false);
				break;
			//+ Escape
			case KeyEvent.VK_ESCAPE:
				// paused = !paused;		//Siempre sera lo contrario
				break;
			default:
				break;
		}
	}

	@Override
	public void MouseClicked(MouseEvent e) {
	}

	@Override
	public void MouseReleased(MouseEvent e) {
	}

	@Override
	public void MousePressed(MouseEvent e) {
	}

	@Override
	public void MouseEntered(MouseEvent e) {
	}

	@Override
	public void MouseExited(MouseEvent e) {
	}

	@Override
	public void MouseDragged(MouseEvent e) {
	}
	
}
