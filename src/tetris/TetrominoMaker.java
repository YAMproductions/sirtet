package com.SafirN.Sirtet;

import com.SafirN.Sirtet.Board.shapes;

public class TetrominoMaker {
	
	private static int numberOfTypes;
	
	public TetrominoMaker() {
		numberOfTypes = 7;
	}
	
	public Poly getPoly(int type) {
		switch(type) {
			case 0: {
				return new Poly(makeL());
			}
			case 1: {
				return new Poly(makeInvertedL());
			}
			case 2: {
				return new Poly(makeS());
			}
			case 3: {
				return new Poly(makeZ());
			}
			case 4: {
				return new Poly(makeT());
			}
			case 5: {
				return new Poly(makeI());
			}
			case 6: {
				return new Poly(makeO());
			}
		}	
		return null;
	}
	
	private shapes[][] makeL() {
		shapes[][] piece = new shapes[][] {
				{	shapes.NOTHING, 	shapes.NOTHING, 	shapes.L 	   },
				{	shapes.L, 			shapes.L, 			shapes.L	   },
				{	shapes.NOTHING, 	shapes.NOTHING, 	shapes.NOTHING }
		};
		return piece;
	}
	
	private shapes[][] makeInvertedL() {
		shapes[][] piece = new shapes[][]{
				{	shapes.INVERTEDL, 	shapes.NOTHING, 	shapes.NOTHING   },
				{	shapes.INVERTEDL, 	shapes.INVERTEDL, 	shapes.INVERTEDL },
				{	shapes.NOTHING, 	shapes.NOTHING, 	shapes.NOTHING   }
		};
		return piece;
	}
	
	private shapes[][] makeS() {
		shapes[][] piece = new shapes[][]{
				{	shapes.NOTHING, 	shapes.S, 			shapes.S       },
				{	shapes.S, 			shapes.S, 			shapes.NOTHING },
				{	shapes.NOTHING, 	shapes.NOTHING, 	shapes.NOTHING }
		};
		return piece;
	}
	
	private shapes[][] makeZ() {
		shapes[][] piece = new shapes[][]{
				{	shapes.Z, 			shapes.Z, 			shapes.NOTHING },
				{	shapes.NOTHING,		shapes.Z, 			shapes.Z 	   },
				{	shapes.NOTHING, 	shapes.NOTHING, 	shapes.NOTHING }
		};
		return piece;
	}
	
	private shapes[][] makeT() {
		shapes[][] piece = new shapes[][] {
				{	shapes.NOTHING,		shapes.T,			shapes.NOTHING },
				{	shapes.T, 			shapes.T, 			shapes.T	   },
				{	shapes.NOTHING,		shapes.NOTHING,		shapes.NOTHING }
		};
		return piece;
	}
	
	private shapes[][] makeI() {
		shapes[][] piece = new shapes[][] {
			{	shapes.NOTHING,		shapes.NOTHING,		shapes.NOTHING,		shapes.NOTHING },
			{	shapes.I, 			shapes.I, 			shapes.I,			shapes.I	   },
			{	shapes.NOTHING,		shapes.NOTHING,		shapes.NOTHING,		shapes.NOTHING },
			{	shapes.NOTHING, 	shapes.NOTHING, 	shapes.NOTHING, 	shapes.NOTHING }
		};
		return piece;
	}
	
	private shapes[][] makeO() {
		shapes[][] piece = new shapes[][] {
			{	shapes.O,			shapes.O },
			{	shapes.O, 			shapes.O },
		};
		return piece;
	}
	
	public static int getNumberOfTypes() {
		return numberOfTypes;
	}	
}
