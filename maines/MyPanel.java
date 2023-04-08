package maines;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import inputs.*;
import gamestates.GameStates;
import static utilz.Constants.MainMenuButton.*;

/** 
 * Clase principal en la que se mostrara todo el juego  */
public class MyPanel extends JPanel implements ActionListener{
	game Mygame;
	public KeyInputs key;
	public MouseInputs mouse;
	public JButton playButton, quitButton;

	/** 
	 * Constructor para la clase {@link #MyPanel(game)}  */
	public MyPanel(game Mygame){
		this.setPreferredSize(new Dimension(game.Game_Width, game.Game_Height));
		this.Mygame= Mygame;
		key = new KeyInputs(Mygame);
		mouse = new MouseInputs(Mygame);
		addKeyListener(key);
		addMouseListener(mouse);
		initButtons();
    }
    
	/** Empieza la cadena de Actualizaciones de Datos
	 * @see levelmanager
	*/
	public void update(){
		isActive();
		switch (GameStates.gamestate) {
			case PLAYING:
				this.Mygame.getPlaying().update();
				break;
			case MAINMENU:
				break;
			case QUIT:
				System.exit(0);
			default:
				break;
		}
	}

	/** Empieza la cadena de Dibujo de los Graficos
	 * @see java.awt.Graphics
	 * @see levelmanager
	*/
	public void paintComponent(Graphics g){
		switch (GameStates.gamestate) {
			case PLAYING:
				this.Mygame.getPlaying().draw(g);
				break;
			case MAINMENU:
				drawButtons(g);
				break;
			default:
				break;
		}
	}

	private void initButtons(){
		this.playButton = new JButton("Play");
		this.playButton.setBounds((game.Game_Width/2) - ButtonWidth/2, (game.Game_Height/2) - (ButtonHeight*2), ButtonWidth, ButtonHeight);
		this.playButton.addActionListener(this);
		this.playButton.setBackground(Color.LIGHT_GRAY);
		this.add(this.playButton);
		this.quitButton = new JButton("Quit");
		this.quitButton.setBounds((game.Game_Width/2) - ButtonWidth/2, (game.Game_Height/2) - (ButtonHeight), ButtonWidth, ButtonHeight);
		this.quitButton.addActionListener(this);
		this.quitButton.setBackground(Color.GRAY);
		this.add(quitButton);
	}

	private void drawButtons(Graphics g){
		this.setBackground(Color.GRAY);
	}

	private void isActive(){
		if(GameStates.gamestate == GameStates.MAINMENU){
			updateButtons();
		}
		else{inhabiltateButtons();}
	}

	private void updateButtons(){		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(GameStates.gamestate == GameStates.MAINMENU){
			if(e.getSource() == this.playButton){
				System.out.println("Play");
				GameStates.gamestate = GameStates.PLAYING;
			}
			if(e.getSource() == this.quitButton){
				System.out.println("Quit");
				GameStates.gamestate = GameStates.QUIT;
			}
		}
	}
}
