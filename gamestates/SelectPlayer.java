package gamestates;

import javax.swing.*;

import utilz.LoadImg;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPlayer {
	JTextField nombreJugador;
    private JButton botonMario = new JButton();
    private JButton botonLuigi = new JButton();
    public String nombre;
	public int type;

    public SelectPlayer() {
        initBotones();
        initTextField();
        verficarBotones();
    }

	public void update(){
		if(GameStates.gamestate == GameStates.PLAYERSELECTOR){
			nombreJugador.setEnabled(true);
			nombreJugador.setVisible(true);
			botonMario.setEnabled(true);
			botonMario.setVisible(true);
			botonLuigi.setEnabled(true);
			botonLuigi.setVisible(true);
		}
	}

	public void draw(Graphics g){}

	public void isActive(){
		if(GameStates.gamestate != GameStates.PLAYERSELECTOR){
			nombreJugador.setEnabled(false);
			nombreJugador.setVisible(false);
			botonMario.setEnabled(false);
			botonMario.setVisible(false);
			botonLuigi.setEnabled(false);
			botonLuigi.setVisible(false);
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
        if (nombre.length()>8){
            nombre = nombre.substring(0, 8);
        }
    }

    public void initBotones(){
        botonMario = new JButton();
        botonLuigi = new JButton();
        botonMario.setBounds(100,300,240,360);
        botonLuigi.setBounds(400,300,240,360);
        ImageIcon imagenMario = new ImageIcon(LoadImg.MarioSelect);
        ImageIcon imagenLuigi = new ImageIcon(LoadImg.LuigiSelect);
        botonMario.setIcon(new ImageIcon(imagenMario.getImage().getScaledInstance(botonMario.getWidth(),botonMario.getHeight(),Image.SCALE_FAST)));
        botonLuigi.setIcon(new ImageIcon(imagenLuigi.getImage().getScaledInstance(botonMario.getWidth(),botonMario.getHeight(),Image.SCALE_FAST)));

    }
    private void verficarBotones() {
        botonMario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = 0;
				GameStates.gamestate = GameStates.LVLSELECTOR;
            }
        });
        botonLuigi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = 1;
				GameStates.gamestate = GameStates.LVLSELECTOR;
            }
        });
    }
}
