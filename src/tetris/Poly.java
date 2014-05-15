package tetris;

public class Poly {
	private SquareType[][] poly;

	/**
	 * A general class to make all type of blocks
	 * @param poly
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
		Poly newPoly = new Poly(new SquareType[this.getPolyLength()][this.getPolyLength()]);	    
	    for (int r = 0; r < this.getPolyLength(); r++) {
	        for (int c = 0; c < this.getPolyLength(); c++){
	            newPoly.poly[this.getPolyLength() - 1 - c][r] = this.poly[r][c];
	        }
	    }    
	    return newPoly;
	}
}
