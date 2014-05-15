package tetris;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

/**
 * Game Board Class
 * 
 * @author YAM Productions
 * @version 1
 *
 */
public class Board {
	
	private int TICK_COUNT = 200;
	private SquareType[][] squares;
	private final int height;
	private final int width;
	private Random rand;
	private Poly falling;
	private Poly nextFalling;
	private Point fallingPosition; //top left corner of the Polyomino
	private boolean gameOver;
	private ArrayList<BoardListener> boardListeners; 
	private int score;
	private int level;
	private int fallenTetrominoCounter;
	private boolean pause;
	
    /**
     * The tick Action, determines which function to call when the game updates.
     */
    @SuppressWarnings("serial")
	private final Action tick = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
            tick();
        }
    };
    
    /**
     * The timer that decides when to update the game.
     */
    private final Timer clockTimer = new Timer(TICK_COUNT, tick);
	
	/**
	 * Constructor 1
	 * Empty Board
	 * @param height
	 * @param width
	 */
	public Board(int width, int height) {
		this.rand = new Random();
		this.height = height;
		this.width = width;
		boardListeners = new ArrayList<BoardListener>();
		squares = new SquareType[width][height];
		falling = null;
		fallingPosition = new Point(0,0);
		gameOver = false;
		score = 0;
		createEmptyBoard(width, height);
		level = 1;
		fallenTetrominoCounter = 0;
		pause = false;
	}
		
	/**
	 * Creates a random game board
	 * @param cols
	 * @param rows
	 */
	public void createRandomBoard(int cols, int rows) {
		for(int y = 0; y < cols; y++) {
			for(int x = 0; x < rows; x++) {
				if(y == 0 || x == 0) {
					squares[y][x] = SquareType.OUTSIDE;
				}else if(y == cols-1 || x == rows-1) {
					squares[y][x] = SquareType.OUTSIDE;
				}else {
					squares[y][x] = SquareType.values()[rand.nextInt(SquareType.values().length -1)];
				}
			}
		}
		notifyListeners();
	}
	
	/**
	 * Creates an empty game board
	 * @param cols
	 * @param rows
	 */
	private void createEmptyBoard(int cols, int rows) {
		for(int y = 0; y < cols; y++) {
			for(int x = 0; x < rows; x++) {
				if(y == 0 || x == 0) {
					squares[y][x] = SquareType.OUTSIDE;
				}else if(y == cols-1 || x == rows-1) {
					squares[y][x] = SquareType.OUTSIDE;
				}else {
					squares[y][x] = SquareType.EMPTY;
				}
			}
		}
		notifyListeners();
	}
	
	public int getHeight() {
		return height;	
	}
	
	public int getWidth() {
		return width;	
	}
	
	/**
	 * Returns the SquareType at x, y coordinate
	 * @param x
	 * @param y
	 * @return
	 */
	public SquareType getSquaretype(int x, int y) { 
		return squares[x][y];
	}
	
	/**
	 * Sets a SquareType at given x and y
	 * @param x
	 * @param y
	 * @param type
	 */
	private void setSquareTypeAt(int x, int y, SquareType type) {
		this.squares[x][y] = type;
	}
	
	public SquareType[][] getBoard() {
		return squares;
	}
	
	public void setFalling(Poly p, int y, int x) {
		falling = p;
	}
	
	public Poly getFalling() {
		return falling;
	}
				
	public Point getFallingPosition() {
		return fallingPosition;
	}
	
	public void addBoardListener(BoardListener bl) {
		boardListeners.add(bl);
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setPause() {
		pause = !pause;
	}
	
	public boolean getPause() {
		return pause;
	}
	
	public Poly getNextFalling() {
		return nextFalling;
	}
	
	/**
	 * Notify all classes that "listens" to board changes
	 */
	private void notifyListeners() {
		if(boardListeners.isEmpty()) {
			return;
		}else {
			for(BoardListener element: boardListeners) {
				element.boardChanged();
			}
		}
	}
	
	/**
	 * Creates a new falling Poly
	 * @return
	 */
	private Poly createNewFalling() {
        TetrominoMaker tetroMaker = new TetrominoMaker();
        Poly falling;
        int temp = rand.nextInt(SquareType.values().length -2);
        if(this.nextFalling == null) {
        	falling = tetroMaker.getPoly(temp);
        	nextFalling = tetroMaker.getPoly(temp);
        } else {
        	falling = nextFalling;
        	nextFalling = tetroMaker.getPoly(temp);
        }
        //centers the polly
        this.fallingPosition.setLocation(getWidth()/2 - falling.getPolyLength()/2, 0);
        return falling;
    }
	
	/**
	 * Player movement of the Poly and game input from keyboard
	 * @param i
	 */
	public void playerMovePoly(int i) {
		//37 = left
		if(i == 37 && !getPause())
			tryMoveX(this.falling, 0);
		//39 = right
		if(i == 39 && !getPause())
			tryMoveX(this.falling, 1);
		//40 = down
		if(i == 40 && this.falling != null && !getPause())
			tryMove(this.falling);
		
		//38 = rotate the Poly to the right
		if(i == 38 && this.falling != null && !getPause()) {
			Poly temp = this.falling.rotateRight();
			if(this.movePolyY(temp) && this.movePolyRight(temp) )
				this.falling = temp;
		}
		
		//80 = pause the game / unpause
		if(i == 80) this.pause = !pause;
		
		//32 = move the Poly as far down as possible
		if(i == 32 && this.falling != null && pause == false) {
			while(tryMove(this.falling));
		}
		
		this.notifyListeners();
	}
	
	/**
	 * Try's to move the falling Poly
	 * @param poly
	 * @return
	 */
	private boolean tryMove(Poly poly) {		
		if(movePolyY(poly)){
			this.fallingPosition.setLocation(this.fallingPosition.getX(), this.fallingPosition.getY() +1);
			return true;
		}
		return false;
	}
	
	/**
	 * Try's to move the falling Poly
	 * @param poly
	 * @param i: if i == 0 --> move left, if i == 1 --> move right
	 */
	private void tryMoveX(Poly poly, int i) {
		if(poly == null)
			return;
		//Move left
		if(i == 0 && movePolyLeft(poly))
			this.fallingPosition.setLocation(this.fallingPosition.getX() -1 , this.fallingPosition.getY());
		//Move right
		if(i == 1 && movePolyRight(poly))
			this.fallingPosition.setLocation(this.fallingPosition.getX() +1 , this.fallingPosition.getY());
	}
		
	/**
	 * Checks if it is possible to move the Poly to the right
	 * @param poly
	 * @param point
	 * @return
	 */
	private boolean movePolyRight(Poly poly) {
		boolean clearToMove = true;
		for(int x = 0; x < poly.getPolyLength(); x++){
			for(int y = 0; y < poly.getPolyLength(); y++){
				if(poly.getPoly()[x][y] != SquareType.EMPTY) {
					if(!(this.getSquaretype(this.fallingPosition.x + x + 1, this.fallingPosition.y + y) == SquareType.EMPTY)) 
						return false;		
				}
			}
		}
		return clearToMove;
	}
	
	/**
	 * Checks if it is possible to move the Poly to the left
	 * @param poly
	 * @param point
	 * @return
	 */
	private boolean movePolyLeft(Poly poly) {
		boolean clearToMove = true;
		for(int x = 0; x < poly.getPolyLength(); x++){
			for(int y = 0; y < poly.getPolyLength(); y++){
				if(poly.getPoly()[x][y] != SquareType.EMPTY) {
					if(!(this.getSquaretype(this.fallingPosition.x + x - 1, this.fallingPosition.y + y) == SquareType.EMPTY)) 
						return false;		
				}
			}
		}
		return clearToMove;
	}
	
	/**
	 * Moves the poly on the y-axis
	 * @param poly
	 * @param point
	 * @return
	 */
	private boolean movePolyY(Poly poly) {
		boolean clearToMove = true;
		for(int x = 0; x < poly.getPolyLength(); x++){
			for(int y = 0; y < poly.getPolyLength(); y++){
				if(poly.getPoly()[x][y] != SquareType.EMPTY) {
					if(!(this.getSquaretype(this.fallingPosition.x + x, this.fallingPosition.y + y +1) == SquareType.EMPTY)) //true if empty
						return false;
				}
			}
		}
		return clearToMove;
	}
	
	/**
	 * Adds a fallen Poly to the game board
	 * @param poly
	 */
	private void addPolyToBoard(Poly poly) {
		for(int x = 0; x < poly.getPolyLength(); x++) {
			for(int y = 0; y < poly.getPolyLength(); y++) {
				if(poly.getPoly()[x][y] != SquareType.EMPTY) {
					squares[this.getFallingPosition().x + x][this.getFallingPosition().y +y] = poly.getPoly()[x][y];
				}
			}
		}
		fallenTetrominoCounter++;
		//checks fallenTetro to determin level
		level();
	}
	
	/**
	 * Checks if a row is full
	 * If multiple rows are filled the player receives a "multi score"
	 * @param poly
	 */
	private void checkRows(Poly poly) {
		int multiScore = 0;
		int newScore = 0;
		for(int y = 1; y < this.height -1 ; y++) {
			boolean fullRow = true;
			for(int x = 1; x < this.width -1; x++) {
				if(this.squares[x][y] == SquareType.EMPTY)
					fullRow = false;
			}
			if(fullRow) { 
				removeRow(y);
				newScore += 100;
				multiScore++;
			}
		}
		if(multiScore > 0) newScore = newScore * multiScore;
		this.score += newScore;
		this.notifyListeners();
	}
	
	/**
	 * Removes a row
	 * @param y
	 */
	private void removeRow(int y) {
		for(int x = 1; x < this.width -1; x++) {
			this.squares[x][y] = SquareType.EMPTY;
		}
		pushRows(y);
	}
	
	/**
	 * Pushes the rows over the deleted row down
	 * @param maxY
	 */
	private void pushRows(int y) {
		System.out.println(y);
        for (int i = y; i > 0 ; i--) {
            for (int x = 1; x < this.width - 1; x++) {
            	if(this.getSquaretype(x, i - 1) == SquareType.OUTSIDE) return;
                this.setSquareTypeAt(x, i, this.getSquaretype(x, i - 1));
            }
        }
    }
	
	/**
	 * Sets the level of the game
	 */
	private void level() {
		if(fallenTetrominoCounter == 20) {
			if(TICK_COUNT > 50) { 
				TICK_COUNT -= 10;
				this.level++;
			}
			fallenTetrominoCounter = 0;
			System.out.println(TICK_COUNT);
		}
	}
	public int getLevel() {
		return level;
	}
	 
	/**
	 * Methods that gets called upon a game tick update.
	 */
    public void updateBoard(){
    	if(pause){
    		clockTimer.stop();
    		return;
    	}
        clockTimer.setCoalesce(true);
        clockTimer.setDelay(TICK_COUNT);
        clockTimer.start();
    }
	
	/**
	 * Updates the game board
	 */
	public void tick() {
		//checks rows
		checkRows(this.falling);
		//Game over
		if(this.gameOver == true)
			return;
		//Create a new falling Poly
		if(falling == null){
			Poly tempFalling = createNewFalling();
			if(tryMove(tempFalling)) {
				this.falling = tempFalling;	
			}else {
				System.out.println("Game over");
				this.gameOver = true;
			}
		//Try to move the falling Poly	
		}else {
			if(tryMove(this.falling)) {
				
			}else {
				addPolyToBoard(this.falling);
				this.falling = null;
			}
		}
		notifyListeners();
	}
	
	public boolean gameOver() {
		return this.gameOver;
	}
	
	/**
	 * Clears the board and resets the game variables
	 */
	public void clearBoard() {
		this.gameOver = false;
		this.falling = null;
		this.pause = false; //just to be sure
		this.level = 1;
		this.score = 0;
		this.TICK_COUNT = 200;
		createEmptyBoard(this.width, this.height);
	}
}
