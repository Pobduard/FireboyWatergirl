package gamestates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import maines.*;
import static utilz.Constants.MainMenuButton.*;

public class MainMenu implements ActionListener{
	private MyPanel Mypanel;
	private int Index = 0;
	public JButton playButton, quitButton;
	private boolean playButtonOver, quitButtonOver;

	public MainMenu(MyPanel panel){
		this.Mypanel = panel;
		initButtons(this.Mypanel);
	}

	public void update(){
		isActive();
		selectButton();
	}

	public void draw(Graphics g){
		drawButtons(g);
	}

	private void drawButtons(Graphics g){
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, game.Game_Width, game.Game_Height);
	}

	private void initButtons(MyPanel panel){
		this.playButton = new JButton("Play");
		this.playButton.setBounds((game.Game_Width/2) - ButtonWidth/2, (game.Game_Height/2) - (ButtonHeight*2), ButtonWidth, ButtonHeight);
		this.playButton.addActionListener(this);
		this.playButton.setBackground(Color.LIGHT_GRAY);
		playButton.addMouseListener(panel.mouse);
		playButton.addMouseMotionListener(panel.mouse);
		this.Mypanel.add(this.playButton);
		this.quitButton = new JButton("Quit");
		this.quitButton.setBounds((game.Game_Width/2) - ButtonWidth/2, (game.Game_Height/2) - (ButtonHeight), ButtonWidth, ButtonHeight);
		this.quitButton.addActionListener(this);
		this.quitButton.setBackground(Color.LIGHT_GRAY);
		quitButton.addMouseListener(panel.mouse);
		quitButton.addMouseMotionListener(panel.mouse);
		this.Mypanel.add(quitButton);
	}

	private void doAction(){
		selectButton();
		switch(this.Index){
			case 1:
				playButton.doClick();
				break;
			case 2:
				quitButton.doClick();
				break;
		}
	}

	private void selectButton(){
		playButton.setSelected(false);
		quitButton.setSelected(false);
		playButton.setBackground(Color.LIGHT_GRAY);
		quitButton.setBackground(Color.LIGHT_GRAY);
		if(playButtonOver){this.Index = 1;}
		else if(quitButtonOver){this.Index = 2;}
		switch(this.Index){
			case 1:
				playButton.setSelected(true);
				if(playButton.isSelected()){
					playButton.setBackground(Color.ORANGE);}
				break;
			case 2:
				quitButton.setSelected(true);
				if(quitButton.isSelected()){
					quitButton.setBackground(Color.ORANGE);}
				break;
			default:
				break;

		}
	}

	private void isActive(){
		if(GameStates.gamestate == GameStates.MAINMENU){
			abilitateButtons();
		}
		else{inhabiltateButtons();}
	}

	private void abilitateButtons(){		
		playButton.setEnabled(true);		
		quitButton.setEnabled(true);
		playButton.setVisible(true);
		quitButton.setVisible(true);
	}	

	private void inhabiltateButtons(){		
		playButton.setEnabled(false);		
		quitButton.setEnabled(false);	
		playButton.setVisible(false);
		quitButton.setVisible(false);
	}

	public void KeyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_ENTER:
				doAction();
				break;
		}
	};

	public void KeyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_P:
				GameStates.gamestate = GameStates.PLAYING;
				break;
			case KeyEvent.VK_UP:
				this.Index--;
				break;
			case KeyEvent.VK_DOWN:
				this.Index++;
				break;
		}
		if(this.Index >= 3){
			this.Index = 0;}
	};

	public void MouseEntered(MouseEvent e) {
	}

	public void MouseExited(MouseEvent e) {
		if(e.getSource() == playButton){
			this.Index = 0;
		}
		if(e.getSource() == quitButton){
			this.Index = 0;
		}
	}

	public boolean IsIn(MouseEvent e, JButton bt){
		return bt.contains(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e){
		this.playButtonOver = false;
		this.quitButtonOver = false;
		if(e.getSource() == playButton){
			this.playButtonOver = true;
		}
		if(e.getSource() == quitButton){
			this.quitButtonOver = true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(GameStates.gamestate == GameStates.MAINMENU){
			if(e.getSource() == this.playButton){
				GameStates.gamestate = GameStates.PLAYING;
			}
			if(e.getSource() == this.quitButton){
				GameStates.gamestate = GameStates.QUIT;
			}
		}
	}
}
