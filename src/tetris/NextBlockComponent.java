package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JComponent;

public class NextBlockComponent extends JComponent implements BoardListener {

	private static final long serialVersionUID = 1L;
	private Board gameBoard;
	private HashMap<SquareType, Color> colorMap;
	private SquareType[][] displayArray;
	
	private static final int BLOCKSIZE = 20;
	
	public NextBlockComponent(Board board, HashMap<SquareType, Color> map) {
		gameBoard = board;
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
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		
		for(int y = 0 ; y < displayArray.length ; y++) {
			for(int x = 0 ; x < displayArray[y].length ; x++) {
				g2d.setColor(colorMap.get(displayArray[x][y]));
				g2d.fillRect(x * BLOCKSIZE, y * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
			}
		}
		if(gameBoard.getNextFalling() != null) {
			paintNextFalling(g2d);
		}
	}
	
	private void paintNextFalling(Graphics g2d) {
		Poly poly = gameBoard.getNextFalling();
        for (int y = 0; y < poly.getPolyLength(); y++) {
        	for(int x = 0; x < poly.getPolyLength(); x++ ) {
        		if(poly.getPoly()[x][y] != SquareType.EMPTY) {
        			g2d.setColor(this.colorMap.get(poly.getPoly()[x][y]));
        			g2d.fillRect((BLOCKSIZE) * ((((displayArray.length/2) - (poly.getPolyLength()/2))) + x), (BLOCKSIZE) * ((((displayArray.length/2) - (poly.getPolyLength()/2))) + y), BLOCKSIZE, BLOCKSIZE);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect((BLOCKSIZE) * ((((displayArray.length/2) - (poly.getPolyLength()/2))) + x), (BLOCKSIZE) * ((((displayArray.length/2) - (poly.getPolyLength()/2))) + y), BLOCKSIZE, BLOCKSIZE);
        		}
        	}
        }
	}
	
	@Override
	public Dimension getPreferredSize() {
		super.getPreferredSize();
		return new Dimension(displayArray.length * BLOCKSIZE, displayArray.length * BLOCKSIZE);
	}
	
	public void boardChanged() {
		this.repaint();
	}
}