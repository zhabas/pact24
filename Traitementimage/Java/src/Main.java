
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

public class Main {
  
	public static void main(String[] argv) {

		Main.initFromJpg();
		
	}
	
	public final static void initFromJpg() {
		
		File testjpg = new File("Traitementimage/Java/data/test.jpg");
		
		try {
			BufferedImage bimg = ImageIO.read(testjpg);
			System.out.println(bimg.getHeight());
			System.out.println(bimg.getWidth());
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
		
	}
	
	
	public final static void initFromTiffFile() {
		
		File testim = new File("Traitementimage/Java/data/test.tif");
		
		try {
			BufferedImage bimage = ImageIO.read(testim);
			//System.out.println(bimage.getRGB(0,0));
			System.out.println(bimage.getHeight());
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
	
	
	public final static void initFromTiffFile2() {
		
		File testim = new File("Traitementimage/Java/data/test.tif");
		
		try {
			FileInputStream in = new FileInputStream(testim);
			FileChannel channel = in.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
			channel.read(buffer);
			String tiffEncodedImg = Base64.encode(buffer.array()); 
			System.out.println(tiffEncodedImg);
			in.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
	
	public static void canGetTiffDecoder()
	{
	    Iterator<ImageReader> reader = ImageIO.getImageReadersByFormatName("TIFF");
	    assertNotNull(reader);
	    assertTrue("No tiff decoder", reader.hasNext());
	}
	

}