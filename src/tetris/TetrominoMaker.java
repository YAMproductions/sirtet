package tetris;

/**
 * The class TetrominoMaker is used in order to generate new pieces
 * for the game. Every time a new random piece is required this method
 * is called, generates the piece and returns it.
 * @author YAM - YOLO APP MAKERS
 * @version 1.0	(2014-05-14)
 *
 */
public class TetrominoMaker {
	
	private static int numberOfTypes;	//Amount of different blocks
	
	public TetrominoMaker() {
		numberOfTypes = 7;
	}
	
	public Poly getPoly(int type) {
		switch(type) {
			case 0: {
				return new Poly(makeL()); //creates an L-piece
			}
			case 1: {
				return new Poly(makeJ()); //creates a J-piece
			}
			case 2: {
				return new Poly(makeS()); //creates an S-piece
			}
			case 3: {
				return new Poly(makeZ()); //creates a Z-piece
			}
			case 4: {
				return new Poly(makeT()); //creates a T-piece
			}
			case 5: {
				return new Poly(makeI()); //creates an I-piece
			}
			case 6: {
				return new Poly(makeO()); //creates an O-piece
			}
			//default : {
			//	return new Poly(makeO()); //creates an O-piece
			//}
			
		}
		return null;
	}
	
	/**
	 * Constructs a 2-dimensional array of enums. This particular array
	 * will be an L-shaped piece on the board.
	 * @return returns the 2-dimensional array of the L-piece.
	 */
	private SquareType[][] makeL() {
		SquareType[][] piece = new SquareType[][] {
				{	SquareType.EMPTY, 		SquareType.L, 		SquareType.EMPTY 	   },
				{	SquareType.EMPTY, 		SquareType.L, 		SquareType.EMPTY	   },
				{	SquareType.L, 			SquareType.L, 		SquareType.EMPTY  	   }
		};
		return piece;
	}
	
	/**
	 * Constructs a 2-dimensional array of enums. This particular array
	 * will be a J-shaped piece on the board.
	 * @return returns the 2-dimensional array of the J-piece.
	 */
	private SquareType[][] makeJ() {
		SquareType[][] piece = new SquareType[][]{
				{	SquareType.J, 			SquareType.J, 		SquareType.EMPTY       },
				{	SquareType.EMPTY, 		SquareType.J, 		SquareType.EMPTY 	   },
				{	SquareType.EMPTY, 		SquareType.J, 		SquareType.EMPTY   	   }
		};
		return piece;
	}
	
	/**
	 * Constructs a 2-dimensional array of enums. This particular array
	 * will be an S-shaped piece on the board.
	 * @return returns the 2-dimensional array of the S-piece.
	 */
	private SquareType[][] makeS() {
		SquareType[][] piece = new SquareType[][]{
				{	SquareType.EMPTY, 	SquareType.S, 			SquareType.EMPTY    },
				{	SquareType.S, 		SquareType.S, 			SquareType.EMPTY 	},
				{	SquareType.S, 		SquareType.EMPTY, 		SquareType.EMPTY 	}
		};
		return piece;
	}
	
	/**
	 * Constructs a 2-dimensional array of enums. This particular array
	 * will be a Z-shaped piece on the board.
	 * @return returns the 2-dimensional array of the Z-piece.
	 */
	private SquareType[][] makeZ() {
		SquareType[][] piece = new SquareType[][]{
				{	SquareType.Z, 			SquareType.EMPTY, 	SquareType.EMPTY 	},
				{	SquareType.Z,			SquareType.Z, 		SquareType.EMPTY 	},
				{	SquareType.EMPTY, 		SquareType.Z, 		SquareType.EMPTY 	}
		};
		return piece;
	}
	
	/**
	 * Constructs a 2-dimensional array of enums. This particular array
	 * will be a T-shaped piece on the board.
	 * @return returns the 2-dimensional array of the T-piece.
	 */
	private SquareType[][] makeT() {
		SquareType[][] piece = new SquareType[][] {
				{	SquareType.EMPTY,		SquareType.T,			SquareType.EMPTY 	},
				{	SquareType.T, 			SquareType.T, 			SquareType.EMPTY    },
				{	SquareType.EMPTY,		SquareType.T,			SquareType.EMPTY	}
		};
		return piece;
	}
	
	/**
	 * Constructs a 2-dimensional array of enums. This particular array
	 * will be an I-shaped piece on the board.
	 * @return returns the 2-dimensional array of the I-piece.
	 */
	private SquareType[][] makeI() {
		SquareType[][] piece = new SquareType[][] {
			{	SquareType.EMPTY,		SquareType.I,		SquareType.EMPTY,		SquareType.EMPTY 	},
			{	SquareType.EMPTY,		SquareType.I, 		SquareType.EMPTY,		SquareType.EMPTY	},
			{	SquareType.EMPTY,		SquareType.I,		SquareType.EMPTY,		SquareType.EMPTY 	},
			{	SquareType.EMPTY, 		SquareType.I, 		SquareType.EMPTY, 		SquareType.EMPTY 	}
		};
		return piece;
	}
	
	/**
	 * Constructs a 2-dimensional array of enums. This particular array
	 * will be an O-shaped piece on the board.
	 * @return returns the 2-dimensional array of the O-piece.
	 */
	private SquareType[][] makeO() {
		SquareType[][] piece = new SquareType[][] {
			{	SquareType.O,			SquareType.O },
			{	SquareType.O, 			SquareType.O },
		};
		return piece;
	}
	
	/**
	 * A method in order to retrieve information about the number of various
	 * available pieces.
	 * @return returns the amount of available pieces.
	 */
	public static int getNumberOfTypes() {
		return numberOfTypes;
	}	
}
