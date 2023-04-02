package utilz;

import java.util.*;

public class CreateTimerTask {
	private Timer timer;
	private TimerTask task;

	public CreateTimerTask(TimerTask this_task, String taskName,int delay, int period){
		this.task = this_task;
		timer = new Timer(taskName);
			
		timer.scheduleAtFixedRate(task, delay, period);
	}

	public static void InitialiceTimerTask(TimerTask this_task, String taskName,int delay, int period){
		TimerTask task = this_task;
		Timer timer = new Timer(taskName);
			
		timer.scheduleAtFixedRate(task, delay, period);
	}
}
