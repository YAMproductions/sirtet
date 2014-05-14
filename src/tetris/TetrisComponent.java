package tetris;

import javax.swing.JComponent;

import tetris.input.Keyboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

/**
 * TetrisComponent is used to paint the tetris and update 
 * it every time something happens in the game, ranging 
 * from a player action to a tick were the falling block
 * falls down one step in the game board.
 * @author YAM - YOLO APP MAKERS
 * @version 1.0	(2014-05-14)
 *
 */

public class TetrisComponent extends JComponent implements BoardListener {
	
	private static final long serialVersionUID = 1L;
	private Board board;							// variable to keep track of board and be able to let board make changes to itself.
	private HashMap<SquareType, Color> colorMap;	// a map to determine colors to be painted on the board and the falling block.
    private Keyboard keyboard;
	
	private final static int BLOCKSIZE = 20; 		// the constant pixel-height and width of blocks.
	
	public TetrisComponent(Board board, HashMap<SquareType, Color> map) {
		this.board = board;
		colorMap = map;
		keyboard = new Keyboard(board);
		this.addKeyListener(keyboard);
	}
	
	/**
	 * Paints the board and falling piece in the frame.
	 * The method is automatically called when the this object is initialized.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		for(int y = 1, k = 0 ; y < board.getHeight()-1 ; y++, k++) {
			for(int x = 1, u = 0 ; x < board.getWidth()-1 ; x++, u++) {
				g2d.setColor(colorMap.get(board.getSquaretype(x, y)));
				g2d.fillRect(u * BLOCKSIZE, k * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
				g2d.setColor(Color.BLACK);
				g2d.drawRect(u * BLOCKSIZE, k * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
			}
		}
		
        if(board.getFalling() != null)
        	paintFalling(g2d);    
	}
    
	/**
	 * Help method to paintComponent(), paints the falling block.
	 * @param g2d is the graphics object used to paint the block.
	 */
    private void paintFalling(Graphics2D g2d) {
        Poly poly = board.getFalling();
        for (int x = 0; x < poly.getPolyLength(); x++) {
        	for(int y = 0; y < poly.getPolyLength(); y++ ) {
        		if(poly.getPoly()[x][y] != SquareType.EMPTY) {
        			g2d.setColor(this.colorMap.get(poly.getPoly()[x][y]));
        			g2d.fillRect((BLOCKSIZE) * (board.getFallingPosition().x + x-1),  //compensate for OUTSIDE block
                            (BLOCKSIZE) * (board.getFallingPosition().y + y-1), BLOCKSIZE, BLOCKSIZE);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect((BLOCKSIZE) * (board.getFallingPosition().x + x-1),  //compensate for OUTSIDE block
                            (BLOCKSIZE) * (board.getFallingPosition().y + y-1), BLOCKSIZE, BLOCKSIZE);
        		}
        	}
        }  
    }
	
    /**
	 * A method to retrieve the preferred size of the component.
	 * @return returns a dimension object with the preferred size of the component in pixel-precision.
	 */
	@Override
	public Dimension getPreferredSize() {
		super.getPreferredSize();
		return new Dimension((board.getWidth() - 2) * BLOCKSIZE, (board.getHeight() -2) * BLOCKSIZE);
	}

	/**
	 * The method gets notified if the anything visual has changed
	 * and repaints the entire component correctly in order 
	 * to display what is happening in the game.
	 */
	@Override
	public void boardChanged() {
		this.repaint();
	}
}
