import java.util.ArrayList;

public class ImageBinaire {

	private ArrayList<ArrayList<Integer>> imagebinaire ;
	
	public ImageBinaire(ArrayList<ArrayList<Integer>> imagebinaire){
		
		this.imagebinaire = imagebinaire ;
	}
	
	private int[][] main(ArrayList<ArrayList<Integer>> image){
		
		int[] predecessor = new int[30];
		int h = image.size() ;
		int w = image.get(0).size();
		int[][] imagecompo = new  int[h][w] ;
		imagecompo[0][0]=0;
		for (int i=0; i<h ; i++){
			for(int j=0; j<w ; j++){	//browse from top to bottom, from left to right
				if (j>0) {	//if not on the upper edge
					if (image.get(i).get(j-1) != 0){	//if upper neighbor already is in a connected component
						imagecompo[i][j] = image.get(i).get(j-1); //go in the same component
				if(i>0){ //if not on the left edge
					if (image.get(i-1).get(j) != 0) //if left neighbor differs from upper neighbor
						predecessor[image.get(i-1).get(j)] = image.get(i).get(j-1);
					if vfgeszfes
						}
					}
				}
				if (j>0) {
					
				}
			}
		}
	}
	
	
}
