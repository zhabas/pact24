import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {
  
	public static void main(String[] argv) throws Exception {

    int width = 100;
    int height = 100;

    /**
     * BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
     * bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
     */
   
	}
	
	public final void initFromTiffFile(File inputfile) {
		
		BufferedImage image ;
		
		try {
			image = ImageIO.read(inputfile);
			
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

}