import java.util.ArrayList;

public class ImageBinaire {

	private ArrayList<ArrayList<Integer>> imagebinaire ;
	
	public ImageBinaire(ArrayList<ArrayList<Integer>> imagebinaire){
		
		this.imagebinaire = imagebinaire ;
	}
	
	private int[][] main(ArrayList<ArrayList<Integer>> image){
		
		int[] predecessor = new int[30];
		int x = image.size() ;
		int y = image.get(0).size();
		int[][] imagecompo = new  int[x][y] ;
		for (int i=0; i<x ; i++){
			for(int j=0; j<y ; j++){	//browse from top to bottom, from left to right
				if (j>0) {	//if not on the upper edge
					if (image.get(i).get(j-1) != 0){	//if upper neighbor already is in a connected component
						imagecompo[i][j] = image.get(i).get(j-1); //go in the same component
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
