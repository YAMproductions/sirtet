import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class TetrisFrame extends JFrame implements BoardListener {
	private Board board;
	private JFrame frame;
	public JTextField displayLevel;
	public JTextField displayScore;
	public JLabel gameStatus;
	private TetrisComponent game;
	
	
	public TetrisFrame(Board board){
		//Testing the layout
		this.board = board;
		game = new TetrisComponent(board);
		
	
		//Creating a frame
		makeFrame(board);
		
		
	
		
		//Panel for board
		makeBoardPanel();
		
		/**
		JPanel boardPanel = new JPanel();
		boardPanel.setBorder(new EmptyBorder(10,10,0,0));
		boardPanel.setLayout(new BorderLayout());
		boardPanel.setMaximumSize(frame.getSize());
		boardPanel.add(game);
	*/
		
		
		
		//Creating Side panel for game information
		JPanel sidePanel = new JPanel();
		sidePanel.setBorder(new EmptyBorder(10,0,0,0));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		JLabel testarBara = new JLabel("Blooooooock");
		testarBara.setBorder(new EmptyBorder(10,10,10,10));
		//background.add(sidePanel, BorderLayout.EAST);
			
		
		//Next block box		
		JPanel nextBlock = new JPanel();
		nextBlock.setLayout(new BorderLayout());
		nextBlock.setMaximumSize(new Dimension(80,80));
		nextBlock.setBackground(Color.BLUE);
		nextBlock.add(testarBara, BorderLayout.CENTER);
		
		// Game status label
		gameStatus = new JLabel("Game is running");
		gameStatus.setBorder(new EmptyBorder(0,10,10,0));
		
		//Creating Level Information
		displayLevel = new JTextField("30");
		displayLevel.setEditable(false);	
		JLabel levelLabel = new JLabel("Level:");
		JPanel levelPanel = new JPanel();	
		levelPanel.setLayout(new BorderLayout());
		levelPanel.setMaximumSize(new Dimension(80, 20));
		levelPanel.setBackground(Color.RED);
		levelPanel.add(levelLabel, BorderLayout.CENTER);
		levelPanel.add(displayLevel, BorderLayout.EAST);
		
		//Createing Score Information
		displayScore = new JTextField("799");
		JLabel scoreLabel = new JLabel("Score: ");
		JPanel scorePanel = new JPanel();
		
		displayScore.setEditable(false);
		scorePanel.setLayout(new BorderLayout());
		scorePanel.setMaximumSize(new Dimension(80, 20));
		scorePanel.setBackground(Color.CYAN);
		scorePanel.add(scoreLabel, BorderLayout.CENTER);
		scorePanel.add(displayScore, BorderLayout.EAST);
		
		
		
		//Adding information to the Side Panel
		sidePanel.add(nextBlock, Box.TOP_ALIGNMENT);
		sidePanel.add(Box.createVerticalStrut(25));
		sidePanel.add(levelPanel, Box.TOP_ALIGNMENT);
		sidePanel.add(Box.createVerticalStrut(25));
		sidePanel.add(scorePanel, Box.TOP_ALIGNMENT);
		
		
		
		
		//Adding everything to the main frame
		frame.add(gameStatus, BorderLayout.PAGE_END);
		frame.add(sidePanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	
	 	/**
	 	 * Creates a frame
	 	 * @param board
	 	 */
		public void makeFrame(Board board){
			frame = new JFrame();
			frame.setSize(board.getWidth() + 100, board.getHeight() + 80);
			frame.setLayout(new BorderLayout());
			makeMenuBar(frame);
			
			
		}
		
		public void makeBoardPanel(){
			JPanel boardPanel = new JPanel();
			boardPanel.setBorder(new EmptyBorder(10,10,0,0));
			boardPanel.setLayout(new BorderLayout());
			boardPanel.setMaximumSize(frame.getSize());
			boardPanel.add(game);
			frame.add(boardPanel, BorderLayout.CENTER);
			
		}
	
	
	
		public void makeMenuBar(JFrame frame){
			//Creates a menubar
			JMenuBar menuBar = new JMenuBar();
			//Adding to the main frame
			frame.setJMenuBar(menuBar);
			
			//Creates and adds a Menu button
			JMenu menu = new JMenu("Meny");
			menuBar.add(menu);
			
			//Creates and adds menu items
			
			//Start a new game
			JMenuItem startGame = new JMenuItem("New Game");
			menu.add(startGame);
			//Listener
			startGame.addActionListener(new ActionListener(){ 
	        	public void actionPerformed(ActionEvent e) { /** Starta ett nytt spel*/; }
	        	});
			
			//Pausa spel
			JMenuItem pauseGame = new JMenuItem("Pause");
			menu.add(pauseGame);
			pauseGame.addActionListener(new ActionListener(){ 
	        	public void actionPerformed(ActionEvent e) { board.setPause; }
	        	});
			
			// Quit game
			JMenuItem quitGame = new JMenuItem("Quit");
			menu.add(quitGame);
			//Gör så att det går att klicka
			quitGame.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) { quitGame();}
			});
			
			
		}
		
	
	public void quitGame(){
		System.exit(0);
	}
		
		
	@Override
	public void boardChanged (){
		// Kollar alla labels och updates så den kan uppdatera boarden
		if(setPause){
			gameStatus.setText("Game is paused");
		}
		
			
		
			
		}
		
	}

	
	
	
	
}
