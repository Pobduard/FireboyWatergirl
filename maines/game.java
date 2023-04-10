package maines;

import utilz.CreateTimerTask;
import java.util.TimerTask;
import gamestates.*;

/** 
 * Clase con los Datos Principales del juego, desde donde se crea lo Esencial para el juego e inicia todo  */
public class game {
	private MyFrame frame;
	private MyPanel panel;
	private Playing playing;
	private MainMenu mainMenu;
	private SelectLvl lvlSelector;

	private TimerTask taskUpdate, taskDraw;
	private final int FPS = 120, UPS = 200;

	public static final int Tile_Size = 24;
	public static final int Game_Tiles_In_Height = 30, Game_Tiles_In_Width = 40;
	public static final int Game_Height = (Tile_Size*Game_Tiles_In_Height);
	public static final int Game_Width = (Tile_Size*Game_Tiles_In_Width);
	
	/** 
	 * Constructor para la clase {@link #game()}  */
	public game(){
	initPlaying();
	panel = new MyPanel(this);
	initMainMenu(panel);
	frame = new MyFrame(panel, "Jaiber Arellano's & Williangel Quevedo's - Fireboy and Watergirl");
	frame.add(panel);
	initLvlSelector(panel);

	frame.setVisible(true);
	initTask();
	initUpdates();
	initDraws();
	
	panel.setFocusable(true);
	panel.requestFocus();
	panel.requestFocusInWindow();
	}

	/**
	 * Genera un gamestate de la clase {@link gamestates.Playing}  con el cual actualizaremos y dibujaremos en pantalla
	 */
	private void initPlaying(){
		playing = new Playing();
	}

	/**
	 * Genera el menu principal
	 * @param panel
	 */
	private void initMainMenu(MyPanel panel){
		mainMenu = new MainMenu(this.panel);
	}

	/**
	 * Genera el Selector de Niveles
	 * @param panel
	 */
	private void initLvlSelector(MyPanel panel){
		lvlSelector = new SelectLvl(this.panel);
	}

	/**
	 * Genera los Timers correspondientes a {@link #initUpdates()} e {@link #initDraws()}  */
	public void initTask(){
		taskUpdate = new TimerTask() {
			@Override
			public void run(){
				panel.update();
			}
		};
		taskDraw = new TimerTask() {
			@Override
			public void run() {
				panel.repaint();
			}
		};

	};

	/**
	 * Inicializa el Timer encargado de actualizar los datos del juego
	 * @see utilz.CreateTimerTask */
	public void initUpdates(){
		CreateTimerTask.InitialiceTimerTask(taskUpdate, "Update-Timer", 0, Math.floorDiv(UPS, 100));
	}

	/**
	 * Inicializa el Timer encargado de actualizar todo lo referente a los graficos del juego
	 * @see java.awt.Graphics
	 * @see utilz.CreateTimerTask
	 * @see java.util.TimerTask */
	public void initDraws(){
		CreateTimerTask.InitialiceTimerTask(taskDraw, "Draws-Timer", 0, Math.floorDiv(FPS, 100));
	}

	/** 
	 * @return {@code Panel} de la clase (para manejar Inputs), actualizar y dibujar*/
	public MyPanel getPanel(){
		return this.panel;
	}

	/** 
	 * @return {@code playing} de la clase (para manejar Inputs), actualizar y dibujar */
	public Playing getPlaying(){
		return this.playing;
	}

	/**
	 *
	 * @return {@code MainMenu} de la clase (para manejar Inputs), actualizar y dibujar
	 */
	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public SelectLvl getLvlSelector() {
		return lvlSelector;
	}

}
