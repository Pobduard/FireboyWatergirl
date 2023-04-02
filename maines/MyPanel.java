package maines;
import CosasConHitbox.Player;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel{
	Player player1;
	public MyPanel(){
		this.setPreferredSize(new Dimension(200, 50));
		this.repaint();
		newPlayer();
	}

	private void newPlayer() {
		player1 = new Player(40,45,70,70,2);
	}
	/*private void newBlocks(){
		bloque =
	}*/


	public void paintComponent(Graphics g){
		//player1.SetColor(1);
		g.setFont(new Font("Comic Sans", Font.BOLD, 15));
		g.drawString("Tenia que hacerlo.", 5, 20);
		player1.render(g,player1.SetColor(player1.getId()));

	}
}
