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
		player1 = new Player(15,15,20,20,2);
	}

	public void render(Graphics g){
		player1.render(g);
	};

	public void paintComponent(Graphics g){
		g.setColor(Color.green);
		g.setFont(new Font("Comic Sans", Font.BOLD, 15));
		g.drawString("Tenia que hacerlo.", 5, 20);
		g.drawString("Mirá la consola y titulo xd", 5, 40);

	}
}
