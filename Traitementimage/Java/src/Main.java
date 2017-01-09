import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {
  
	public static void main(String[] argv) {

		Main.initFromTiffFile();
		
	}
	
	public final static void initFromTiffFile() {
		
		try {
			BufferedImage bimage = ImageIO.read(new File("test.tif"));
			System.out.println(bimage.getRGB(0,0));
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

}