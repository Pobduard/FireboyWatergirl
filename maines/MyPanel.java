package maines;
import CosasConHitbox.Player;

import javax.swing.*;
import java.awt.*;
import maines.game;
import levels.levelmanager;

public class MyPanel extends JPanel{
	game Mygame;
  	Player player1;
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
		player1 = new Player(15,15,20,20,2);
	}
	/*private void newBlocks(){
		bloque =
	}*/

	public void selectLevel(int lvlNumber){
		currentlvl = lvlmanager.getLeveldata(lvlNumber);
	}

	public void paintComponent(Graphics g){
  //player1.SetColor(1);
  		lvlmanager.draw(g, currentlvl);
		g.setFont(new Font("Comic Sans", Font.BOLD, 15));
		g.drawString("Tenia que hacerlo.", 5, 20);
		player1.render(g,player1.SetColor(player1.getId()));
	}
}
