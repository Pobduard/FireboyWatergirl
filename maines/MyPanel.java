package maines;
import javax.swing.*;

import java.awt.*;
import levels.levelmanager;
import inputs.*;
import gamestates.GameStates;

/** 
 * Clase principal en la que se mostrara todo el juego  */
public class MyPanel extends JPanel{
	game Mygame;
	levelmanager lvlmanager;
	private KeyInputs key;
	private MouseInputs mouse;

	/** 
	 * Constructor para la clase {@link #MyPanel(game)}  */
	public MyPanel(game Mygame){
		this.setPreferredSize(new Dimension(game.Game_Width, game.Game_Height));
		this.Mygame= Mygame;
		key = new KeyInputs(Mygame);
		mouse = new MouseInputs(Mygame);
		addKeyListener(key);
		addMouseListener(mouse);
    }
    
	/** Empieza la cadena de Actualizaciones de Datos
	 * @see levelmanager
	*/
	public void update(){
		switch (GameStates.gamestate) {
			case PLAYING:
				this.Mygame.getPlaying().update();
				break;
			case MAINMENU:
				break;
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
				break;
			default:
				break;
		}
	}

}
