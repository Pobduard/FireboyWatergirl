package maines;

import javax.swing.*;

public class MyFrame extends JFrame{
	private MyPanel panel;
	public MyFrame(MyPanel this_panel, String s){
		this.panel = this_panel;
		this.setTitle(s);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.pack();
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
