package tetris;

import java.awt.Color;
import java.util.HashMap;


/**
 * 
 * @author Daniel
 *
 */
public class BoardTest implements Runnable {
	
	private static final Color PURPLE_COLOR = new Color(128,0,128);
	private static final Color OLIVE_COLOR = new Color(128,128,0);
	private HashMap<SquareType, java.awt.Color> mColorMap;
	private Board board;
	private TetrisComponent tComponent;
	private boolean gameOver;
	private Thread thread;
	private NextBlockComponent nextBlock;


	public BoardTest() {
		gameOver = false;
		setUpColorMap();
		createBoard();
		thread = new Thread(this);
		thread.start();
		run();
	}
	
	private void createBoard() {
		board = new Board(20, 32);
		thread = new Thread(this);
		tComponent = new TetrisComponent(board, mColorMap);
		nextBlock = new NextBlockComponent(board, mColorMap);
		new TetrisFrame(board, tComponent, nextBlock);
	}
	
	/**
	 * The game loop
	 */
	public void run() {
		while(!this.gameOver) {
			board.updateBoard();
		}	
	}
	
    /**
     * Method setting up the Color Map
     */
    private void setUpColorMap(){
        this.mColorMap = new HashMap<SquareType, java.awt.Color>();
        this.mColorMap.put(SquareType.I, Color.CYAN);
        this.mColorMap.put(SquareType.J, Color.BLUE);
        this.mColorMap.put(SquareType.L, Color.ORANGE);
        this.mColorMap.put(SquareType.O, OLIVE_COLOR);
        this.mColorMap.put(SquareType.Z, Color.RED);
        this.mColorMap.put(SquareType.S, Color.GREEN);
        this.mColorMap.put(SquareType.T, PURPLE_COLOR);
        this.mColorMap.put(SquareType.EMPTY, Color.WHITE);
        this.mColorMap.put(SquareType.OUTSIDE, Color.BLACK);
    }
		
	public static void main(String[] args) {
		new BoardTest();
	}

}
