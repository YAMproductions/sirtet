package tetris;

public class Poly {

	private SquareType[][] poly;
	
	/**
	 * A general class to make all type of blocks
	 * @param br
	 * @param width
	 * @param height
	 */
	
	public Poly(SquareType[][] poly){
		this.poly = poly;
				
	}
	/**
	 * returns a blocktype
	 * @return
	 */
	
	public SquareType[][] getPoly(){
		return poly;
		
	}
	/**
	 * returns the length of the block
	 * @return
	 */
	public int getPolyLength(){
		return poly.length;
	}
	
	/**
	 * Rotates the block 
	 */
	public void rotateRight(){
		
		
	}
	
	
}
