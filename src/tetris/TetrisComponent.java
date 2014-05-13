package com.SafirN.Sirtet;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.SafirN.Sirtet.Board.shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class TetrisComponent extends JComponent implements BoardListener {
	
	private Board gameBoard;
	private HashMap<shapes, Color> colorMap;
	
	private final static int WINDOW_WIDTH = 367;
	private final static int WINDOW_HEIGHT = 612;
	private final static int BLOCKSIZE = 20;
	
	public TetrisComponent(Board board, HashMap<shapes, Color> map) {
		gameBoard = board;
		colorMap = map;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		for(int i = 1, k = 0 ; i < gameBoard.getHeight()-1 ; i++, k++) {
			for(int j = 1, u = 0 ; j < gameBoard.getWidth()-1 ; j++, u++) {
				g2d.setColor(colorMap.get(gameBoard.getBoardLocation(i, j)));
				g2d.fillRect(u * BLOCKSIZE, k * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
				g2d.setColor(Color.BLACK);
				g2d.drawRect(u * BLOCKSIZE, k * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
				System.out.println(gameBoard.getBoardLocation(i, j).toString() + ", " + g2d.getColor());
			}
		}
		
		if(gameBoard.getFalling() != null) {
			for(int i = 0 ; i < gameBoard.getFallingY().length ; i++) {
				for(int j = 0 ; j < gameBoard.getFallingX().length ; j++) {
					if(gameBoard.getFalling().getArray()[i][j] != shapes.NOTHING) {
						g2d.setColor(colorMap.get(gameBoard.getFalling().getArray()[i][j]));
						g2d.fillRect((gameBoard.getFallingX()[j] - 1) * BLOCKSIZE, (gameBoard.getFallingY()[i] - 1) * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
						g2d.setColor(Color.BLACK);
						g2d.drawRect((gameBoard.getFallingX()[j] - 1) * BLOCKSIZE, (gameBoard.getFallingY()[i] - 1) * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
					}
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
