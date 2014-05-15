package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * MainMenu Class
 * 
 * @author YAM Productions
 * @version 1
 *
 */
public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton startButton;
	private JButton aboutButton;
	private BoardTest bTest;

	
	public MainMenu(BoardTest bTest){
		super("Main Menu");
		this.bTest = bTest;
		createMainMenu();
		
	}
	/**
	 * Builds the main menu frame and its content
	 */
	private void createMainMenu(){
		setUpFrame();
		
		setUpBackground(); //Also creates buttons
		
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	/**
	 * Setting up frame
	 */
	private void setUpFrame(){
		this.setLayout(new BorderLayout());
		
	}
	/**
	 * Creating and setting up background
	 */
	private void setUpBackground(){	
		JLabel background = new JLabel(new ImageIcon(
				"src/CMON2.png")
		);
		
		background.setLayout(new GridBagLayout());
		//Adds buttons
		makeButtons(background);
		
		this.getContentPane().add(background);
		
	}
	
	private void rmMainMenu(){
		this.dispose();
	}
	
	/**
	 * Making a popup window for about
	 */
	private void aboutPopUp(){	
		JFrame popUpFrame = new JFrame("About");
		popUpFrame.setLayout(new BorderLayout());
		JTextArea about = new JTextArea();
		about.setText("A Inda Project "
				+ "\n"
				+ "\n"
				+ "Author: YAM Production"
				+ "\n"
				+ "\n"
				+ "HOW-TO:"
				+ "\n"
				+ "Fill the grid with blocks and try to earn points by filling a row.    "
				+ "\n"
				+ "\n"
				+ "Controls: "
				+ "\n"
				+ "\n"
				+ "UP Arrow - Rotate"
				+ "\n"
				+ "LEFT/RIGHT Arrow - move left/right"
				+ "\n"
				+ "DOWN Arrow - speed fall"
				+ "\n"
				+ "SPACE - instant fall"
				+ "\n"
				+ "P - Pause/Unpause "
				+ "\n"
				+ "N - New game");
		
		about.setBackground(Color.BLACK);
		about.setForeground(Color.WHITE);
		about.setBorder(new EmptyBorder(10,0,10,20));
		popUpFrame.setBackground(Color.BLACK);
		popUpFrame.add(about, BorderLayout.CENTER);
		popUpFrame.pack();
		popUpFrame.setLocationRelativeTo(null);
		popUpFrame.setResizable(false);
		popUpFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		popUpFrame.setVisible(true);
	}
	/**
	 * Creating the buttons and it's Actions
	 * @param background
	 */
	private void makeButtons(JLabel background){
		
		startButton = new JButton(new ImageIcon(
				"src/TetrisStartButton.png")
				);
	
		aboutButton = new JButton(new ImageIcon(
				"src/TetrisAboutButton.png")
				);
		
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				rmMainMenu();
				bTest.createBoard();
				bTest.run();
				
				
				
			}
		});	
		aboutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				aboutPopUp();
			}
		});	
		
		//Adding buttons to buttonPanel
		background.add(startButton);
		//background.add(scoreButton, Box.TOP_ALIGNMENT);
		background.add(aboutButton);

	}
}
