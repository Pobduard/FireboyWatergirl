package maines;

import utilz.CreateTimerTask;
import java.util.TimerTask;
import inputs.*;
public class game {
	private MyFrame frame;
	private MyPanel panel;
	private KeyInputs key;
	private MouseInputs mouse;
	// private Keyinputs key;
	private TimerTask taskUpdate, taskDraw;
	private final int FPS = 120, UPS = 200;

	public static final int Tile_Size = 24;
	public static final int Game_Tiles_In_Height = 30, Game_Tiles_In_Width = 40;
	public static final int Game_Height = (Tile_Size*Game_Tiles_In_Height);
	public static final int Game_Width = (Tile_Size*Game_Tiles_In_Width);
	
	public game(){
	panel = new MyPanel(this);
	frame = new MyFrame(panel, "Jaiber Arellano's & Williangel Quevedo's - Fireboy and Watergirl");
	key = new KeyInputs(this);
	mouse = new MouseInputs(this);

	frame.add(panel);
	panel.setFocusable(true);
	panel.requestFocusInWindow();
	frame.setVisible(true);


	initTask();
	initUpdates();
	initDraws();
	}

	public void initTask(){
		taskUpdate = new TimerTask() {
			@Override
			public void run(){
				//panel.update();
			}
		};
		taskDraw = new TimerTask() {
			@Override
			public void run() {
				panel.repaint();
			}
		};

	};

	public void initUpdates(){
		CreateTimerTask.InitialiceTimerTask(taskUpdate, "Update-Timer", 0, Math.floorDiv(UPS, 100));
	
	}
	public void initDraws(){
		CreateTimerTask.InitialiceTimerTask(taskDraw, "Draws-Timer", 0, Math.floorDiv(FPS, 100));
	}
}
