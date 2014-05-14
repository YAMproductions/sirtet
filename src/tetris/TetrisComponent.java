package tetris;

import javax.swing.JComponent;

import tetris.input.Keyboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

public class TetrisComponent extends JComponent implements BoardListener {
	
	private static final long serialVersionUID = 1L;
	private Board gameBoard;
	private HashMap<SquareType, Color> colorMap;
    private Keyboard keyboard;
	
	private final static int BLOCKSIZE = 20;
	
	public TetrisComponent(Board board, HashMap<SquareType, Color> map) {
		gameBoard = board;
		colorMap = map;
		keyboard = new Keyboard(board);
		this.addKeyListener(keyboard);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		for(int y = 1, k = 0 ; y < gameBoard.getHeight()-1 ; y++, k++) {
			for(int x = 1, u = 0 ; x < gameBoard.getWidth()-1 ; x++, u++) {
				g2d.setColor(colorMap.get(gameBoard.getSquaretype(x, y)));
				g2d.fillRect(u * BLOCKSIZE, k * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
				g2d.setColor(Color.BLACK);
				g2d.drawRect(u * BLOCKSIZE, k * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
			}
		}
		
        if(gameBoard.getFalling() != null)
        	paintFalling(g2d);    
	}
    
    /**
     * Paints the falling tetromino
     * @param g2d
     */
    private void paintFalling(Graphics2D g2d) {
        Poly poly = gameBoard.getFalling();
        for (int x = 0; x < poly.getPolyLength(); x++) {
        	for(int y = 0; y < poly.getPolyLength(); y++ ) {
        		if(poly.getPoly()[x][y] != SquareType.EMPTY) {
        			g2d.setColor(this.colorMap.get(poly.getPoly()[x][y]));
        			g2d.fillRect((BLOCKSIZE) * (gameBoard.getFallingPosition().x + x-1),  //compensate for OUTSIDE block
                            (BLOCKSIZE) * (gameBoard.getFallingPosition().y + y-1), BLOCKSIZE, BLOCKSIZE);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect((BLOCKSIZE) * (gameBoard.getFallingPosition().x + x-1),  //compensate for OUTSIDE block
                            (BLOCKSIZE) * (gameBoard.getFallingPosition().y + y-1), BLOCKSIZE, BLOCKSIZE);
        		}
        	}
        }  
    }
	
	@Override
	public Dimension getPreferredSize() {
		super.getPreferredSize();
		return new Dimension((gameBoard.getWidth() - 2) * BLOCKSIZE, (gameBoard.getHeight() -2) * BLOCKSIZE);
	}

	@Override
	public void boardChanged() {
		this.repaint();
	}
}
