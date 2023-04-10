package gamestates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import maines.*;
import utilz.LoadImg;

import static utilz.Constants.MainMenuButton.*;

public class MainMenu implements ActionListener{
	private BufferedImage background;
	private MyPanel Mypanel;
	private int Index = 0;
	public JButton playButton, quitButton;
	private boolean playButtonOver, quitButtonOver;
	private Font font = new Font("Mv Boli", Font.BOLD, 45);

	public MainMenu(MyPanel panel){
		this.Mypanel = panel;
		initButtons(this.Mypanel);
		this.background = LoadImg.GetImage(LoadImg.MainMenu);
	}

	public void update(){
		isActive();
		selectButton();
	}

	public void draw(Graphics g){
		g.drawImage(background, 0, 0, game.Game_Width, game.Game_Height, null);
	}

	private void initButtons(MyPanel panel){
		panel.setLayout(null);
		this.playButton = new JButton("Play");
		this.playButton.setForeground(Color.WHITE);
		this.playButton.setFont(this.font);
		this.playButton.setBounds((game.Game_Width/2) - ButtonWidth/2, (game.Game_Height/2) - (ButtonHeight*2), ButtonWidth, ButtonHeight);
		this.playButton.addActionListener(this);
		this.playButton.addMouseListener(panel.mouse);
		this.playButton.addMouseMotionListener(panel.mouse);
		this.playButton.setLocation((int)((game.Game_Width * 0.5f) - (ButtonWidth/2)),(int)(game.Game_Height *0.4f));
		this.Mypanel.add(this.playButton);

		this.quitButton = new JButton("Quit");
		this.quitButton.setForeground(Color.WHITE);
		this.quitButton.setFont(this.font);
		this.quitButton.setBounds((game.Game_Width/2) - ButtonWidth/2, (game.Game_Height/2) - (ButtonHeight), ButtonWidth, ButtonHeight);
		this.quitButton.addActionListener(this);
		this.quitButton.addMouseListener(panel.mouse);
		this.quitButton.addMouseMotionListener(panel.mouse);
		this.quitButton.setLocation((int)((game.Game_Width * 0.5f) - (ButtonWidth/2)),(int)(game.Game_Height *0.6f));
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
		playButton.setBackground(new Color(152, 196, 0));
		quitButton.setBackground(new Color(196, 0, 54));
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

	/**Maneja los botones<p></p>
	 *Este metodo habilita o desabilita los botones, depende de
	 * cual gamestate es el actual
	 */
	private void isActive(){
		if(GameStates.gamestate == GameStates.MAINMENU){
			abilitateButtons();
		}
		else{inhabiltateButtons();}
	}

	/**
	 * Habilita los botones
	 */
	private void abilitateButtons(){		
		this.playButton.setEnabled(true);		
		this.quitButton.setEnabled(true);
		this.playButton.setVisible(true);
		this.quitButton.setVisible(true);
	}

	/**
	 * Inhabilita los botones
	 */
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
				if(this.Index == 0){
					this.Index = 2;
				}else {this.Index--;}
				
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
				GameStates.gamestate = GameStates.LVLSELECTOR;
			}
			if(e.getSource() == this.quitButton){
				GameStates.gamestate = GameStates.QUIT;
			}
		}
	}
}
