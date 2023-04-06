package maines;
import javax.swing.*;

import java.awt.*;
import levels.levelmanager;
import inputs.*;

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
		this.lvlmanager = new levelmanager(Mygame);
    }
    
	/** Empieza la cadena de Actualizaciones de Datos
	 * @see levelmanager
	*/
	public void update(){
		lvlmanager.update();
	}

	/** Empieza la cadena de Dibujo de los Graficos
	 * @see java.awt.Graphics
	 * @see levelmanager
	*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
  		lvlmanager.draw(g);
	}

	/** 
	 * @return {@code lvlmanager} de la clase */
	public levelmanager getLvlManager(){
		return this.lvlmanager;
	}
}
