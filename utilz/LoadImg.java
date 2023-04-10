package utilz;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class LoadImg {
	public static final String LEVEL_ONE_PIXIL = "levels/LEVEL_ONE.png"; 
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

	public static final String Identify1 = "resources/Identif1.png";
	public static final String Identify2 = "resources/Identif2.png";
	public static final String Items = "resources/Items16x15.png";
	
	public static final String TilesSprite = "resources/Tiles.png";
	public static final String BigTileSprite = "resources/BigTile.png";
	public static final String SlopesSprite = "resources/Slopes.png";
	public static final String LiquidSprites = "resources/LiquidAssets31x21.png";
	public static final String BotonesSprites = "resources/Botones16x18.png";
	public static final String PilarSprites = "resources/Pilar16x16.png";
	public static final String PlataformaSprites = "resources/Platform16x16.png";
	public static final String SpikesSprites = "resources/Spikes16x16.png";

	public static final String Background1 = "resources/Fondo1.png";
	public static final String Background2 = "resources/Fondo2.png";
	public static final String PlayerSelector = "resources/playerSelector.png";
	public static final String MainMenu = "resources/MainMenu2.png";


	/**
	 * @exception NullPointerException si no esta bien definida la ruta "in" podria ser un puntero nulo
	 * @param path
	 * @return img
	 */
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

	public static BufferedImage ResizeImage(BufferedImage image, int width, int height){
		Image scaled = image.getScaledInstance(width, height, Image.SCALE_FAST);
		image = toBufferedImage(scaled);
		return image;
	}

	/**
	 * Metodo usado para redimensionar a las imagenes
	 * @exception NullPointerException si no esta bien definida la ruta "in" podria ser un puntero nulo
	 * @param this_path
	 * @param width
	 * @param height
	 * @return image
	 */
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
