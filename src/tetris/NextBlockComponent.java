package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JComponent;

/**
 * The class NextBlockComponent is used to paint
 * the next coming block and it's background. The
 * component is meant to get notified when a change 
 * is made in order to repaint itself.
 * @author YAM - YOLO APP MAKERS
 * @version 1.0	(2014-05-14)
 */
public class NextBlockComponent extends JComponent implements BoardListener {

	private static final long serialVersionUID = 1L;
	private Board board;							// variable to keep track of board and be able to let board make changes to itself.
	private HashMap<SquareType, Color> colorMap;	// a map to determine colors to be painted on the board and the falling block.
	private SquareType[][] displayArray;			// the array in which the block is painted.
	
	private static final int BLOCKSIZE = 20;		// the constant pixel-height and width of blocks.
	
	public NextBlockComponent(Board board, HashMap<SquareType, Color> map) {
		this.board = board; 
		colorMap = map;		
		displayArray = new SquareType[][] {
				{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY },
				{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY },
				{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY },
				{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY },
				{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY },
				{ SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY }
		};
	}
	
	/**
	 * Paints the background to the next falling piece.
	 * The method is automatically called when the this object is initialized.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		
		for(int x = 0 ; x < displayArray.length ; x++) {
			for(int y = 0 ; y < displayArray[x].length ; y++) {
				g2d.setColor(colorMap.get(displayArray[x][y]));
				g2d.fillRect(x * BLOCKSIZE, y * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
			}
		}
		if(board.getNextFalling() != null) {
			paintNextFalling(g2d);
		}
	}
	
	/**
	 * Help method to paintComponent(), paints the next block.
	 * @param g2d is the graphics object used to paint the block.
	 */
	private void paintNextFalling(Graphics g2d) {
		Poly poly = board.getNextFalling();
        for (int x = 0; x < poly.getPolyLength(); x++) {
        	for(int y = 0; y < poly.getPolyLength(); y++ ) {
        		if(poly.getPoly()[x][y] != SquareType.EMPTY) {
        			g2d.setColor(this.colorMap.get(poly.getPoly()[x][y]));
        			g2d.fillRect((BLOCKSIZE) * ((((displayArray.length/2) - (poly.getPolyLength()/2))) + x), (BLOCKSIZE) * ((((displayArray.length/2) - (poly.getPolyLength()/2))) + y), BLOCKSIZE, BLOCKSIZE);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect((BLOCKSIZE) * ((((displayArray.length/2) - (poly.getPolyLength()/2))) + x), (BLOCKSIZE) * ((((displayArray.length/2) - (poly.getPolyLength()/2))) + y), BLOCKSIZE, BLOCKSIZE);
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
		return new Dimension(displayArray.length * BLOCKSIZE, displayArray.length * BLOCKSIZE);
	}
	
	/**
	 * The method gets notified if the anything visual has changed
	 * and repaints the entire component correctly in order 
	 * to display what is happening in the game.
	 */
	public void boardChanged() {
		this.repaint();
	}
}
