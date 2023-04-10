package utilz;

import java.util.*;

/** Clase hecha para manejar dentro de si lo Necesario Correspondiente a sus Diferentes Timers */
public class CreateTimerTask {
	private Timer timer;
	private TimerTask task;

	/** Crea el Timer Correspondiente al {@code TimerTask} Pasado
	* @see java.util.TimerTask
	* @see java.util.Timer*/
	public CreateTimerTask(TimerTask this_task, String taskName,int delay, int period){
		this.task = this_task;
		timer = new Timer(taskName);
			
		timer.scheduleAtFixedRate(task, delay, period);
	}

	/**
	 * Es el metodo encargado de inicializar los TimerTask
	 * @param this_task
	 * @param taskName
	 * @param delay
	 * @param period
	 */
	public static void InitialiceTimerTask(TimerTask this_task, String taskName,int delay, int period){
		TimerTask task = this_task;
		Timer timer = new Timer(taskName);
			
		timer.scheduleAtFixedRate(task, delay, period);
	}
}
