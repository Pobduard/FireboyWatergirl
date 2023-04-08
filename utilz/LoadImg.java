package utilz;

import java.awt.*;
import java.awt.Image;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class LoadImg {
	public static final String LEVEL_ONE_PIXELS = "levels/LEVEL_ONE.png"; 
	public static final String LEVEL_ONE_PIXIL = "levels/pixilvl.png"; 
	public static final String LEVEL_TWO_PIXELS = "levels/LEVEL_TWO.png"; 
	public static final String LEVEL_TRE = "levels/LEVEL_TRE.png"; 

	public static final String GEMS = "resources/Gems.png"; 
	public static final String LIQUID_ASSETS = "resources/GroundAssets.png"; 
	
	public static final String LUIGI_IDLE = "resources/LuigiIdle.png"; 
	public static final String LUIGI_MOVE = "resources/LuigiMove.png"; 
	public static final String LUIGI_JUMP = "resources/LuigiJump.png"; 
	public static final String LUIGI_FALL = "resources/LuigiFall.png"; 
	
	public static final String MARIO_IDLE = "resources/MarioIdle.png"; 
	public static final String MARIO_MOVE = "resources/MarioMove.png"; 
	public static final String MARIO_JUMP = "resources/MarioJump.png"; 
	public static final String MARIO_FALL = "resources/MarioFall.png"; 


	public static BufferedImage GetImage(String path){
		BufferedImage img = null;
		InputStream in = LoadImg.class.getResourceAsStream("/"+path);
		try{
			img = ImageIO.read(in);
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static BufferedImage GetResizedImage(String this_path, int width, int height){
		BufferedImage img = null;
		InputStream in = LoadImg.class.getResourceAsStream("/"+ this_path);
		try{
			img = ImageIO.read(in);
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Image scaled = img.getScaledInstance(width, height, Image.SCALE_FAST);
		img = toBufferedImage(scaled);


		return img;
	}

	public static BufferedImage toBufferedImage(Image img){
    if (img instanceof BufferedImage)
    {
        return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
	}
}
