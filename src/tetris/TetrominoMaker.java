package tetris;


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
			default : {
				return new Poly(makeO());
			}
		}	
	}
	
	private SquareType[][] makeL() {
		SquareType[][] piece = new SquareType[][] {
				{	SquareType.EMPTY, 	SquareType.EMPTY, 	SquareType.L 	   },
				{	SquareType.L, 			SquareType.L, 			SquareType.L	   },
				{	SquareType.EMPTY, 	SquareType.EMPTY, 	SquareType.EMPTY }
		};
		return piece;
	}
	
	private SquareType[][] makeInvertedL() {
		SquareType[][] piece = new SquareType[][]{
				{	SquareType.J, 	SquareType.EMPTY, 	SquareType.EMPTY   },
				{	SquareType.J, 	SquareType.J, 	SquareType.J },
				{	SquareType.EMPTY, 	SquareType.EMPTY, 	SquareType.EMPTY   }
		};
		return piece;
	}
	
	private SquareType[][] makeS() {
		SquareType[][] piece = new SquareType[][]{
				{	SquareType.EMPTY, 	SquareType.S, 			SquareType.S       },
				{	SquareType.S, 			SquareType.S, 			SquareType.EMPTY },
				{	SquareType.EMPTY, 	SquareType.EMPTY, 	SquareType.EMPTY }
		};
		return piece;
	}
	
	private SquareType[][] makeZ() {
		SquareType[][] piece = new SquareType[][]{
				{	SquareType.Z, 			SquareType.Z, 			SquareType.EMPTY },
				{	SquareType.EMPTY,		SquareType.Z, 			SquareType.Z 	   },
				{	SquareType.EMPTY, 	SquareType.EMPTY, 	SquareType.EMPTY }
		};
		return piece;
	}
	
	private SquareType[][] makeT() {
		SquareType[][] piece = new SquareType[][] {
				{	SquareType.EMPTY,		SquareType.T,			SquareType.EMPTY },
				{	SquareType.T, 			SquareType.T, 			SquareType.T	   },
				{	SquareType.EMPTY,		SquareType.EMPTY,		SquareType.EMPTY }
		};
		return piece;
	}
	
	private SquareType[][] makeI() {
		SquareType[][] piece = new SquareType[][] {
			{	SquareType.EMPTY,		SquareType.EMPTY,		SquareType.EMPTY,		SquareType.EMPTY },
			{	SquareType.I, 			SquareType.I, 			SquareType.I,			SquareType.I	   },
			{	SquareType.EMPTY,		SquareType.EMPTY,		SquareType.EMPTY,		SquareType.EMPTY },
			{	SquareType.EMPTY, 	SquareType.EMPTY, 	SquareType.EMPTY, 	SquareType.EMPTY }
		};
		return piece;
	}
	
	private SquareType[][] makeO() {
		SquareType[][] piece = new SquareType[][] {
			{	SquareType.O,			SquareType.O },
			{	SquareType.O, 			SquareType.O },
		};
		return piece;
	}
	
	public static int getNumberOfTypes() {
		return numberOfTypes;
	}	
}
