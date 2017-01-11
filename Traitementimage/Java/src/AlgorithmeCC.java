import java.awt.image.BufferedImage;
import java.util.Arrays;


public class AlgorithmeCC {	
	
	static int[] predecessor = new int[512]; //class variable used by both methods
	
	public static int[][] firstPhase(BufferedImage bimg){
		
		int compteurcompo = 1;
		 
		int h = bimg.getHeight() ;
		int w = bimg.getWidth();
		int[][] newimg = new  int[h][w] ;
		for (int i=0; i<h ; i++){
			for(int j=0; j<w ; j++){	//browse from top to bottom, from left to right
				
				if (bimg.getRGB(i, j) == -16777216) {
					newimg[i][j] = 0 ;
				}
				else {
					if (i==0) { //first line
						if (j==0) { //first column
							newimg[i][j] = compteurcompo ;
						}
						else {
							if (newimg[i][j-1] != 0) newimg[i][j] = newimg[i][j-1] ;
							else {
								compteurcompo += 1 ;
								newimg[i][j] = compteurcompo ;
							}
						}
					}
					else {
						if (j == 0) { //first column
							if (newimg[i-1][j] != 0) newimg[i][j] = newimg[i-1][j] ;
							else {
								compteurcompo += 1 ;
								newimg[i][j] = compteurcompo ;
							}
						}
						else { //not first column nor first line
							if (newimg[i-1][j] != 0) {
								newimg[i][j] = newimg[i-1][j] ;
								if (newimg[i][j-1] != 0 && newimg[i][j-1] != newimg[i-1][j]) {
									AlgorithmeCC.predecessor[newimg[i][j-1]] = newimg[i-1][j] ;
									//System.out.println(Arrays.toString(predecessor));
								}
							}
							else {
								if (newimg[i][j-1] != 0) newimg[i][j] = newimg[i][j-1] ;
								else {
									compteurcompo += 1 ;
									newimg[i][j] = compteurcompo;
								}
							}
						}
					}
				}
			}
		}
		return newimg ;
		
	}
	
	public static void secondPhase(int[][] img) {
		
		int[] tab = new int[predecessor.length] ;
		int tmp ;
		int compteur = 0 ;
		int h = img.length ;
		int w = img[0].length ;
		
		for (int i=0; i< predecessor.length ; i++) {
			tmp = i ;
			while (predecessor[tmp] != 0) {
				tmp = predecessor[tmp] ;
			}
			predecessor[i] = tmp ;
		}
		for (int i=0; i< predecessor.length ; i++) {
			if (predecessor[i] == 0) predecessor[i] = i ;
		}
		
		for (int i=0; i< predecessor.length ; i++) {
			for (int j=i; j< predecessor.length ; j++) {
				if (predecessor[j] == predecessor[i] ) tab[j] = compteur ;
			}
			compteur += 1 ;
		}
		
		for (int i=0; i<h ; i++){
			for(int j=0; j<w ; j++){	
				img[i][j]= tab[img[i][j]] ;
			}
		}
		
		System.out.println(Arrays.deepToString(img));
	}
	
}
