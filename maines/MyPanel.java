package maines;
import CosasConHItbox.Player2;

import javax.swing.*;
import java.awt.*;
import maines.game;
import levels.levelmanager;

public class MyPanel extends JPanel{
	game Mygame;
  	Player2 player1;
	levelmanager lvlmanager;
	int[][] currentlvl;

	public MyPanel(game game){
		this.Mygame= game;
		this.lvlmanager = new levelmanager(game);
		this.setPreferredSize(new Dimension(game.Game_Width, game.Game_Height));
		selectLevel(1);
    newPlayer();
    }
    
	private void newPlayer() {
		player1 = new Player2(15,15,20,20,2);
	}

	public void render(Graphics g){
		player1.render(g);
	};

	public void selectLevel(int lvlNumber){
		currentlvl = lvlmanager.getLeveldata(lvlNumber);
	}

	public void paintComponent(Graphics g){
		lvlmanager.draw(g, currentlvl);
	}
}
