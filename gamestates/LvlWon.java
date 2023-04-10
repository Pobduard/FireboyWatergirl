package gamestates;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.JPanel;

import inputs.MouseInputs;

import static utilz.Constants.MainMenuButton.*;

import maines.game;
import utilz.LoadImg;

public class LvlWon {
	private Rectangle NextLvl, Quit ,LvlSelector;
	private boolean intoQuit, intoNext, intSelector;
	private BufferedImage background;

	public LvlWon(){
		this.background = LoadImg.GetResizedImage(LoadImg.LvlWon, game.Game_Width, game.Game_Height);
		this.Quit = new Rectangle(ButtonWidth*1, (game.Game_Height/2), ButtonWidth, ButtonHeight);
		this.LvlSelector = new Rectangle(ButtonWidth*3, (game.Game_Height/2), ButtonWidth, ButtonHeight);
		this.NextLvl = new Rectangle(ButtonWidth*5, (game.Game_Height/2), ButtonWidth, ButtonHeight);
	}


	public void update() {
		if(GameStates.gamestate == GameStates.LVLWON){
			if(intoQuit){System.exit(0);}
			if(intSelector){this.intSelector = false;}
			if(intoNext){nextLvl();
				this.intoNext = false;}
		}

	}

	private void nextLvl(){
		if(GameStates.gamestate == GameStates.LVLWON){
			if(LevelStates.levelstate == LevelStates.LVL1){
				LevelStates.levelstate = LevelStates.LVL2;
				GameStates.gamestate = GameStates.PLAYING;

			}
			if(LevelStates.levelstate == LevelStates.LVL2){
				LevelStates.levelstate = LevelStates.LVL3;
				GameStates.gamestate = GameStates.PLAYING;

			}
			if(LevelStates.levelstate == LevelStates.LVL3){
				LevelStates.levelstate = LevelStates.LVL1;
				GameStates.gamestate = GameStates.PLAYING;
					
			}
		}
	}

	public void draw(Graphics g) {
		if(GameStates.gamestate == GameStates.LVLWON){
			g.drawImage(background, 0, 0, game.Game_Width, game.Game_Height, null);
			g.setFont(new Font("Comic Sans", Font.BOLD, 25));
			g.setColor(new Color(255,255,255,150));
			g.fillRect(0, 0, game.Game_Width, game.Game_Height);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(this.Quit.x, this.Quit.y, this.Quit.width, this.Quit.height);
			g.setColor(Color.BLACK);
			g.drawString("QUIT", (this.Quit.x + 30), (this.Quit.y+this.Quit.height)-20);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(this.LvlSelector.x, this.LvlSelector.y, this.LvlSelector.width, this.LvlSelector.height);
			g.setColor(Color.BLACK);
			g.drawString("Select Lvl", (this.LvlSelector.x + 10), (this.LvlSelector.y+this.LvlSelector.height)-20);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(this.NextLvl.x, this.NextLvl.y, this.NextLvl.width, this.NextLvl.height);
			g.setColor(Color.BLACK);
			g.drawString("Next Lvl", (this.NextLvl.x + 20), (this.NextLvl.y+this.NextLvl.height)-20);
		}
	}

	public void MouseClicked(MouseEvent e) {
		if(IsIn(e, Quit)){
			this.intoQuit = true;
		} else {}
		if(IsIn(e, LvlSelector)){
			this.intSelector = true;
		}
		if(IsIn(e, NextLvl)){
			this.intoNext = true;
		}
	}

	public boolean IsIn(MouseEvent e, Rectangle pn){

		return pn.contains(e.getX(), e.getY());
	}
	
}
