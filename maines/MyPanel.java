package maines;
import javax.swing.*;

import Entities.Player;

import java.awt.*;
import maines.game;
import levels.levelmanager;

public class MyPanel extends JPanel{
	game Mygame;
	levelmanager lvlmanager;
	int[][] currentlvl;

	public MyPanel(game game){
		this.Mygame= game;
		this.lvlmanager = new levelmanager(game);
		this.setPreferredSize(new Dimension(game.Game_Width, game.Game_Height));
		selectLevel(1);
    }
    
	public void update(){
		lvlmanager.update();
	}

	public void selectLevel(int lvlNumber){
		currentlvl = lvlmanager.getLeveldata(lvlNumber);
	}

	public void paintComponent(Graphics g){
  		lvlmanager.draw(g);
		g.setFont(new Font("Comic Sans", Font.BOLD, 15));
		g.drawString("Tenia que hacerlo.", 5, 20);
	}
}
