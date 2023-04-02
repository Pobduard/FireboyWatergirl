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
	
	public game(){
	panel = new MyPanel();
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

	public void initUpdates(){
		CreateTimerTask.InitialiceTimerTask(taskUpdate, "Update-Timer", 0, Math.floorDiv(UPS, 100));
	
	}
	public void initDraws(){
		CreateTimerTask.InitialiceTimerTask(taskDraw, "Draws-Timer", 0, Math.floorDiv(FPS, 100));
	}
}
