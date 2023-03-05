package maines;
import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel{
	public MyPanel(){
		this.setPreferredSize(new Dimension(200, 50));
		this.repaint();
	}

	public void paintComponent(Graphics g){
		g.setColor(Color.green);
		g.setFont(new Font("Comic Sans", Font.BOLD, 15));
		g.drawString("Tenia que hacerlo.", 5, 20);
		g.drawString("Mirá la consola y titulo xd", 5, 40);

	}
}
