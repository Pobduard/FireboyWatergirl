package maines;

import javax.swing.*;
import java.awt.*;

import inputs.*;
import gamestates.GameStates;

/** 
 * Clase principal en la que se mostrara todo el juego  */
public class MyPanel extends JPanel{
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
		addMouseMotionListener(mouse);
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
				this.Mygame.getMainMenu().update();
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
				this.Mygame.getMainMenu().draw(g);
				break;
			default:
				break;
		}
		this.repaint();
	}

	private void isActive(){
		if(GameStates.gamestate != GameStates.MAINMENU){
			this.Mygame.getMainMenu().update();
		}
	}

}
