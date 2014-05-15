package tetris.output;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

public class HighScore {
	
	private int highScore;
	
	public HighScore() {
	}
	
	/**
	 * 
	 * @param n
	 */
	public void addScore(int n) {
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHighScore() {
		return this.highScore;
	}
	
	/**
	 * Writes the score to the supplied file
	 */
	private void write() {
		/*
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		*/
	}
	
	/**
	 * 
	 */
	private void read() {
		
	}
	
}
