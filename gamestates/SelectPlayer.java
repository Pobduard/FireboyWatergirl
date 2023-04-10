package gamestates;

import javax.swing.*;

import utilz.LoadImg;
import maines.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class SelectPlayer {
	private JTextField nombreJugador;
    private Rectangle botonMario, botonLuigi;
    private boolean intoMario, intoLuigi;
    public String nombre;
	public int type;


    private BufferedImage background, Mario, Luigi;
    MyPanel panel;

    public SelectPlayer(MyPanel panel) {
        this.panel = panel;
        this.background = LoadImg.GetResizedImage(LoadImg.LvlWon, game.Game_Width, game.Game_Height);
        this.Mario = LoadImg.GetResizedImage(LoadImg.MarioSelect, 240, 360);
        this.Luigi = LoadImg.GetResizedImage(LoadImg.LuigiSelect, 240, 360);
        botonMario = new Rectangle(100, 400, 240, 360);
        botonLuigi = new Rectangle(400, 400, 240, 360);
    }

	public void update() {
		if(GameStates.gamestate == GameStates.PLAYERSELECTOR){
			if(intoMario){this.type = 0;
            this.intoMario = false;
            GameStates.gamestate = GameStates.LVLSELECTOR;}
			if(intoLuigi){this.type = 1;
                this.intoLuigi = false;
                GameStates.gamestate = GameStates.LVLSELECTOR;}
	    }
    }

	public void draw(Graphics g) {
		if(GameStates.gamestate == GameStates.PLAYERSELECTOR){
			g.drawImage(background, 0, 0, game.Game_Width, game.Game_Height, null);
			g.setFont(new Font("Comic Sans", Font.BOLD, 25));

            g.drawImage(Mario, 100, 400, 240, 360, null);

            g.drawImage(Luigi, 400, 400, 240, 360, null);
		}
	}

	public void isActive(){
		if(GameStates.gamestate != GameStates.PLAYERSELECTOR){
            update();
		}
	}

    private void initTextField() {
        nombreJugador = new JTextField();
        nombreJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombre = nombreJugador.getText();
            }
        });
    }

    public int getType() {
        return type;
    }

    public void MouseClicked(MouseEvent e) {
		if(IsIn(e, botonMario)){
			this.intoMario = true;
		} else {}
		if(IsIn(e, botonLuigi)){
			this.intoLuigi = true;
		}
	}

	public boolean IsIn(MouseEvent e, Rectangle pn){
		return pn.contains(e.getX(), e.getY());
	}
}
