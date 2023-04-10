package levels;

import java.awt.*;

import gamestates.GameStates;
import maines.*;

public class Cronometro {
	public boolean cronStop = false;
	private int seg, mins;
	private float Actuales = 0;
	private String segundos, minutos;
	private long prevTime = System.currentTimeMillis();
	private long currentTime = System.currentTimeMillis();

	public Cronometro(){
	}

	public void draw(Graphics g){
		if(GameStates.gamestate == GameStates.PLAYING){
			update();
			segundos = String.format("%02d", seg);
			minutos = String.format("%02d", mins);
	
			g.setColor(Color.CYAN);
			g.setFont(new Font("Mv Boli", Font.BOLD, 20));
			g.drawString("Tiempo en el Nivel", 5, game.Game_Height-5);
			g.drawString((minutos + ":" + segundos), 200, game.Game_Height-5);
		}	else{resetTimer();}
	}

	public void update(){
		if(GameStates.gamestate == GameStates.PLAYING && !cronStop){
			currentTime = System.currentTimeMillis();
			float Diference = (currentTime - prevTime) / 1000;
			Actuales += (1.46f + Diference);	//El tiempo de cada update + poquito mas de tiempo de fallo si hay alguno
			seg = (int)(Actuales / 1_000) % 60;
			mins = (int)(Actuales / 60_000);
			prevTime = currentTime;
		}
	}
	
	public void resetTimer(){
		Actuales = 0;
	}
	public void setCronStop(boolean cronStop) {
		this.cronStop = cronStop;
	}
}
