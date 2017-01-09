import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;


public class ImageBinaireIO {
	
	private File imagebinaire ;
	
	public ImageBinaireIO(File imagebinaire){
		
		this.imagebinaire = imagebinaire ;
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
