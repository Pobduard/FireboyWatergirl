package gamestates;

import maines.*;
import utilz.LoadImg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import static utilz.Constants.MainMenuButton.*;

public class SelectLvl implements ActionListener {
    private BufferedImage background;
    private MyPanel Mypanel;
    public JButton level1Button, level2Button;
    private JLabel textLabel;
    private Font font = new Font("Mv Boli", Font.BOLD, 30);

    public SelectLvl(MyPanel panel){
        this.Mypanel = panel;
        initButtons(this.Mypanel);
        this.background = LoadImg.GetResizedImage(LoadImg.PlayerSelector, game.Game_Width, game.Game_Height);
    }

    public void update(){
        if(GameStates.gamestate == GameStates.LVLSELECTOR){
            this.textLabel.setEnabled(true);
            this.textLabel.setVisible(true);
            this.level1Button.setEnabled(true);
            this.level1Button.setVisible(true);
            this.level2Button.setEnabled(true);
            this.level2Button.setVisible(true);
        } else {
            this.textLabel.setEnabled(false);
            this.textLabel.setVisible(false);
            this.level1Button.setEnabled(false);
            this.level1Button.setVisible(false);
            this.level2Button.setEnabled(false);
            this.level2Button.setVisible(false);
        }
    }

    public void draw(Graphics g){
        if(GameStates.gamestate == GameStates.LVLSELECTOR){
            g.drawImage(this.background, 0, 0, game.Game_Width, game.Game_Height, null);
        }
    }

    private void initButtons(MyPanel panel) {
        panel.setLayout(null);
        this.textLabel = new JLabel("Elije un nivel");
        this.textLabel.setForeground(Color.WHITE);
        this.textLabel.setFont(this.font);
        this.textLabel.setBounds(100,100,ButtonWidth*2,ButtonHeight*2);
        this.Mypanel.add(textLabel);


        this.level1Button = new JButton("NIVEL 1");
        this.level1Button.setForeground(Color.WHITE);
        this.level1Button.setFont(this.font);
        this.level1Button.setBounds((game.Game_Width/2) - ButtonWidth/2, (game.Game_Height/2) - (ButtonHeight*2), ButtonWidth*2, ButtonHeight*2);
        this.level1Button.addActionListener(this);
        this.level1Button.addMouseListener(panel.mouse);
        this.level1Button.addMouseMotionListener(panel.mouse);
        this.level1Button.setLocation((int)((game.Game_Width * 0.5f) - (ButtonWidth/2)),(int)(game.Game_Height *0.4f));
        this.Mypanel.add(this.level1Button);

        this.level2Button = new JButton("NIVEL 2");
        this.level2Button.setForeground(Color.WHITE);
        this.level2Button.setFont(this.font);
        this.level2Button.setBounds((game.Game_Width/2) - ButtonWidth/2, (game.Game_Height/2) - (ButtonHeight), ButtonWidth*2, ButtonHeight*2);
        this.level2Button.addActionListener(this);
        this.level2Button.addMouseListener(panel.mouse);
        this.level2Button.addMouseMotionListener(panel.mouse);
        this.level2Button.setLocation((int)((game.Game_Width * 0.5f) - (ButtonWidth/2)),(int)(game.Game_Height *0.6f));
        this.Mypanel.add(level2Button);

        this.textLabel.setEnabled(false);
        this.textLabel.setVisible(false);
        this.level1Button.setEnabled(false);
        this.level1Button.setVisible(false);
        this.level2Button.setEnabled(false);
        this.level2Button.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameStates.gamestate == GameStates.LVLSELECTOR){
            if(e.getSource() == this.level1Button){
                System.out.println("soy en boton 1");
                LevelStates.levelstate = LevelStates.LVL1;
                GameStates.gamestate = GameStates.PLAYING;
            }
            if(e.getSource() == this.level2Button){
                System.out.println("soy el boton 2");
                LevelStates.levelstate = LevelStates.LVL2;
                GameStates.gamestate = GameStates.PLAYING;
            }
        }
    }
}