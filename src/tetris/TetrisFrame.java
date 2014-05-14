package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TetrisFrame extends JFrame implements BoardListener {
	private static final long serialVersionUID = 1L;
	private Board board;
	private JLabel displayLevel;
	private JLabel displayScore;
	private int trackScore;
	private int trackLevel;
	private JLabel gameStatus;
	private JLabel popUpStatus;
	private JMenuItem pauseGame;
	private TetrisComponent game;
	private NextBlockComponent nextComponent;

	/**
	 * This Class creates a GUI for the game itself.
	 * 
	 * @param board
	 * @param game
	 */

	public TetrisFrame(Board board, TetrisComponent game, NextBlockComponent nextBlock) { // l��gg till
															// component

		this.board = board;
		this.game = game;
		this.nextComponent = nextBlock;

		board.addBoardListener(this);

		// Creating a frame
		makeFrame(board);

		// Panel for board
		makeBoardPanel();

		// Side Panel for information
		makeSidePanel();

		
		// Creating PopUp Label
		popUpStatus = new JLabel("Status");
		popUpStatus.setFont(popUpStatus.getFont().deriveFont(64f));

		// Setting up the frame
		
		setGameStatus();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setFocusable(true);		
		this.pack();
		this.setLocationRelativeTo(null); // centers the frame
		this.setVisible(true);
		this.game.requestFocusInWindow();

	}
	/**
	 * Creates a frame as an ground
	 * 
	 * @param board
	 */
	private void makeFrame(Board board) {
		this.setLayout(new BorderLayout());
		makeMenuBar(this);
	}
	/**
	 * Creates a panel postioned right Game information will be stored in this
	 * panel.
	 */
	private void makeSidePanel() {
		JLabel nextBlockLabel = new JLabel("Next block:");
		nextBlockLabel.setBorder(new EmptyBorder(0,0,0,10));
		JPanel sidePanel = new JPanel();
		sidePanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

		// Adding information
		sidePanel.add(nextBlockLabel);
		setNextBlock(sidePanel);
		setDisplayLevel(sidePanel);
		setDisplayScore(sidePanel);
		this.add(sidePanel, BorderLayout.EAST);
	}

	/**
	 * Creates the Board panel holding the game itself
	 */
	private void makeBoardPanel() {
		JPanel boardPanel = new JPanel();
		boardPanel.setBorder(new EmptyBorder(10, 10, 0, 0));
		boardPanel.setLayout(new BorderLayout());
		boardPanel.add(game);
		this.add(boardPanel, BorderLayout.CENTER);

	}

	/**
	 * Creates the menu bar and buttons
	 * 
	 * @param frame
	 */
	private void makeMenuBar(JFrame frame) {
		// Creates a menubar
		JMenuBar menuBar = new JMenuBar();
		// Adding to the main frame
		frame.setJMenuBar(menuBar);

		// Creates and adds a Menu button
		JMenu menu = new JMenu("Meny");
		menuBar.add(menu);

		// Creates and adds menu items

		// Start a new game
		JMenuItem startGame = new JMenuItem("New Game");
		menu.add(startGame);
		// Listener
		startGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.clearBoard();
			}
		});

		// Pause Game
		pauseGame = new JMenuItem("Pause");
		menu.add(pauseGame);
		pauseGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.setPause();
				setPauseStatus();
			}
		});

		// Quit game
		JMenuItem quitGame = new JMenuItem("Quit");
		menu.add(quitGame);
		// Creates a action when clicked
		quitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitGame();
			}
		});

	}
	/**
	 * Gives the player information about the games running state
	 * paused/running
	 */

	private void setGameStatus() {
		// Game status label
		gameStatus = new JLabel("Game is running");
		gameStatus.setBorder(new EmptyBorder(0, 10, 10, 0));
		this.add(gameStatus, BorderLayout.PAGE_END);
	}
	/**
	 * Displays game score
	 * @param sidePanel
	 */
	private void setDisplayScore(JPanel sidePanel) {
		this.trackScore = 0;
		displayScore = new JLabel(trackScore + "");
	
		JLabel scoreLabel = new JLabel("Score: ");
		JPanel scorePanel = new JPanel();
		
		displayScore.setForeground(Color.RED);
		scorePanel.setLayout(new FlowLayout());

		
		scorePanel.add(scoreLabel, FlowLayout.LEFT);
		scorePanel.add(displayScore, FlowLayout.CENTER);
		sidePanel.add(scorePanel, Box.TOP_ALIGNMENT);

	}
	/**
	 * Displays the upcoming block.
	 * @param sidePanel
	 */
	private void setNextBlock(JPanel sidePanel) {
		// Next block box
		JPanel nextBlock = new JPanel();
		nextBlock.add(nextComponent);
		sidePanel.add(nextBlock);
		
	}
	/**
	 * Displays the current level 
	 * @param sidePanel
	 */
	private void setDisplayLevel(JPanel sidePanel) {
		this.trackLevel = 0;
		displayLevel = new JLabel(trackLevel + "");
		
		JLabel levelLabel = new JLabel("Level:");
		JPanel levelPanel = new JPanel();
		displayLevel.setForeground(Color.RED);
		levelPanel.setLayout(new FlowLayout());
		levelPanel.add(levelLabel, FlowLayout.LEFT);
		levelPanel.add(displayLevel, FlowLayout.CENTER);
		sidePanel.add(levelPanel);

	}
	/**
	 * Updates the pause status
	 */
	private void setPauseStatus() {
		if(board.getPause()){
			gameStatus.setText("<html>Game is <font color='red'> paused </font></html>");
			pauseGame.setText("Unpause	P");
			
		}else{
			gameStatus.setText("<html>Game is <font color='red'> running </font></html>");
			pauseGame.setText("Pause" + " " + "P");
		}
	}
	/**
	 * Quits the game
	 */
	private void quitGame() {
		System.exit(0);
	}

	@Override
	public void boardChanged() {
		// Kollar alla labels och updates s������ den kan uppdatera boarden
		setPauseStatus();
		
		if(board.getScore() != trackScore){
			trackScore = board.getScore();
			displayScore.setText(trackScore + "");
		}
		if(board.getLevel() != trackLevel){
			trackLevel = board.getLevel();
			displayLevel.setText(trackLevel + "");
		}
		if(board.gameOver()){
			gameStatus.setText("Game Over");
		}

		this.repaint();

	}

}
