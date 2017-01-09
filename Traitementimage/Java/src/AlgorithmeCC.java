import java.util.ArrayList;


public class AlgorithmeCC {	
	
	
	
	private static int[][] firstPhase(BufferedImage bimage){
		
		int[] predecessor = new int[30];
		int h = bimage.getHeight() ;
		int w = bimage.getWidth();
		int[][] imagecompo = new  int[h][w] ;
		imagecompo[0][0]=0;
		for (int i=0; i<h ; i++){
			for(int j=0; j<w ; j++){	//browse from top to bottom, from left to right
				if (j>0) {	//if not on the upper edge
					if (imagecompo[i][j-1] != 0){	//if upper neighbor already is in a connected component
						imagecompo[i][j] = imagecompo[i][j-1]; //go in the same component
				if(i>0){ //if not on the left edge
					if (image.get(i-1).get(j) != 0) //if left neighbor differs from upper neighbor
						predecessor[image.get(i-1).get(j)] = image.get(i).get(j-1);
						}
					}
				}
				if (j>0) {
					
				}
			}
		}
	}
	
	
}
