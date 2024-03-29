package gamestates;

import java.awt.Graphics;
import java.awt.event.*;
import maines.*;

import Entities.Player;
import levels.*;

public class Playing implements StateMethods{
	private levelmanager lvlmanager;
	private Player player;
	private Cronometro cron;
	game MyGame;


	public Playing(game game) {
		this.MyGame = game;
		this.lvlmanager = new levelmanager(this);
		this.player = this.lvlmanager.getPlayer();
		initCron();
	}

	@Override
	public void update() {
		this.lvlmanager.update();
		this.cron.update();
	}

	@Override
	public void draw(Graphics g) {
		this.lvlmanager.draw(g);
		this.cron.draw(g);
	}

	private void initCron(){
		cron = new Cronometro();
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			//+LEFT
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				this.player.setLeft(true);
				break;
			//+RIGHT
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				this.player.setRight(true);
				break;
			//+ Espacio
			case KeyEvent.VK_UP:
				this.player.setcanJump(true);
				break;
			//+ ALIVE
			case KeyEvent.VK_U:
				this.player.setAlive(true);;
				break;
			//+ LVL1
			case KeyEvent.VK_1:
				LevelStates.levelstate = LevelStates.LVL1;
				break;
			//+ LVL2
			case KeyEvent.VK_2:
				LevelStates.levelstate = LevelStates.LVL2;
				break;
			//+ LVL3
			case KeyEvent.VK_3:
				LevelStates.levelstate = LevelStates.LVL3;
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
			case KeyEvent.VK_UP:
				this.player.setcanJump(false);
				break;
			//+ MAINMENU
			case KeyEvent.VK_P:
				GameStates.gamestate = GameStates.MAINMENU;
				break;
			//+ Escape
			case KeyEvent.VK_ESCAPE:
			GameStates.gamestate = GameStates.PLAYERSELECTOR;
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

	public Cronometro getCron() {
		return cron;
	}
	
}
