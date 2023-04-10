package maines;

import javax.swing.*;
import java.awt.*;

import inputs.*;
import gamestates.GameStates;
import gamestates.LvlWon;

/** 
 * Clase principal en la que se mostrara todo el juego  */
public class MyPanel extends JPanel{
	game Mygame;
	public KeyInputs key;
	public MouseInputs mouse;
	public JButton playButton, quitButton;
	public LvlWon lvlWon;

	/** 
	 * Constructor para la clase {@link #MyPanel}
	 * @param Mygame
	 * */
	public MyPanel(game Mygame){
		this.setPreferredSize(new Dimension(game.Game_Width, game.Game_Height));
		this.Mygame= Mygame;
		key = new KeyInputs(Mygame);
		mouse = new MouseInputs(Mygame);
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		initLvlWon();
    }
    
	/**Este metodo es el encargado de actualizar el programa
	 * @see levels.levelmanager
	 * @see gamestates.GameStates
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
			case LVLWON:
				this.lvlWon.update();
				break;
			case QUIT:
				System.exit(0);
			default:
				break;
		}
	}

	/** Empieza la cadena de Dibujo de los Graficos
	 * @see java.awt.Graphics
	*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.repaint();
		switch (GameStates.gamestate) {
			case PLAYING:
				this.Mygame.getPlaying().draw(g);
				break;
			case MAINMENU:
				this.Mygame.getMainMenu().draw(g);
				break;
			case LVLWON:
				this.lvlWon.draw(g);
				break;
			default:
				break;
		}

	}

	private void isActive(){
		if(GameStates.gamestate != GameStates.MAINMENU){
			this.Mygame.getMainMenu().update();
		}
	}

	private void initLvlWon(){
		lvlWon = new LvlWon();
	}

	/**
	 *
	 * @return {@code LvlWon} de la clase (para manejar Inputs), actualizar y dibujar
	 */
	public LvlWon getLvlWon() {
		return lvlWon;
	}
}
