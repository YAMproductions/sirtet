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
	public Poly rotateRight(){
		SquareType[][] rotatedPoly = new SquareType[getPolyLength()][getPolyLength()];
		for(int y = 0; y < poly.length; y++){
			
			for(int x = 0; x < poly[y].length; x++){
				
				rotatedPoly[poly.length - y -1][x] = poly[x][y];
				
			}
			
		}
		poly = rotatedPoly;	
		return new Poly(poly);
	}
	
	
}
