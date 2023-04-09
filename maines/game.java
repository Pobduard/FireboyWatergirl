package maines;

import utilz.CreateTimerTask;
import java.util.TimerTask;
import gamestates.*;

/** 
 * Clase con los Datos Principales del juego, desde donde se crea lo Esencial para el juego e inicia todo */
public class game {
	private MyFrame frame;
	private MyPanel panel;
	private Playing playing;
	private MainMenu mainMenu;

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

	frame.setVisible(true);
	initTask();
	initUpdates();
	initDraws();
	
	panel.setFocusable(true);
	panel.requestFocus();
	panel.requestFocusInWindow();
	}

	private void initPlaying(){
		playing = new Playing();
	}

	private void initMainMenu(MyPanel panel){
		mainMenu = new MainMenu(this.panel);
	}

	/**
	 * Inicializa los Timers Correspondienes a {@link #initUpdates()} y {@link #initDraws()()}  */
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
	 * Inicializa el Timer Encargado de Actualizar los Datos del juego
	 * @see #utilz.CreateTimerTask.InitialiceTimerTask(Timertask, String, int, int)
	 * @see java.util.TimerTask
	 * @see java.util.Timer*/
	public void initUpdates(){
		CreateTimerTask.InitialiceTimerTask(taskUpdate, "Update-Timer", 0, Math.floorDiv(UPS, 100));
	}

	/**
	 * Inicializa el Timer Encargado de Actualizar Todo lo referente a los graficos del juego
	 * @see java.awt.Graphics
	 * @see #utilz.CreateTimerTask.InitialiceTimerTask(Timertask, String, int, int)
	 * @see java.util.TimerTask
	 * @see java.util.Timer*/
	public void initDraws(){
		CreateTimerTask.InitialiceTimerTask(taskDraw, "Draws-Timer", 0, Math.floorDiv(FPS, 100));
	}

	/** 
	 * @return {@code Panel} de la clase (Para Manejar Inputs)*/
	public MyPanel getPanel(){
		return this.panel;
	}

	/** 
	 * @return {@code Playing} de la clase (Para Manejar Inputs)*/
	public Playing getPlaying(){
		return this.playing;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}
}
