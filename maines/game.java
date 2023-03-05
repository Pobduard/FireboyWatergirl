package maines;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sound.sound;

public class game {
	private MyFrame frame;
	private MyPanel panel;
	
	public game(){
	panel = new MyPanel();
	frame = new MyFrame(panel, "La Venganza se Sirve Fria y Cruel :D");

	frame.add(panel);
	panel.setFocusable(true);
	panel.requestFocusInWindow();
	frame.setVisible(true);


	
	try {
		new sound();
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	System.out.println("\n\n\n\n\n[Verse 1] \n"+
		"We're no strangers to love\n"+
		"You know the rules and so do I\n"+
		"A full commitment's what I'm thinking of\n"+
		"You wouldn't get this from any other guy\n"+
		"[Pre-Chorus]\n"+
		"I just wanna tell you how I'm feeling\n"+
		"Gotta make you understand\n"+
		
		"[Chorus]\n"+
		"Never gonna give you up\n"+
		"Never gonna let you down\n"+
		"Never gonna run around and desert you\n"+
		"Never gonna make you cry\n"+
		"Never gonna say goodbye\n"+
		"Never gonna tell a lie and hurt you\n");
	
	}
}
