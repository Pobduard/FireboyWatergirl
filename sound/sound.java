package sound;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

public class sound {
	public sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File path = new File(System.getProperty("user.dir") + "/sound");
		File[] file = path.listFiles();
		String song = null;
		for (File f : file) {
			String holder = f.getName();
			if(holder.endsWith("wav")){
				song = f.getAbsolutePath();
				System.out.println(song);
			}
		}

		AudioInputStream aud = AudioSystem.getAudioInputStream(new File(song));
    	Clip clip = AudioSystem.getClip();
    	clip.open(aud);

    	clip.start();
	}
}
