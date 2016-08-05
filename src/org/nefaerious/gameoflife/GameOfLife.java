package org.nefaerious.gameoflife;

import javax.swing.JFrame;

/**
 * 
 * @author Sammy Shin
 *
 */
public class GameOfLife extends JFrame{

	final private int WIDTH = 601;
	final private int HEIGHT = 601;
	
	public GameOfLife(){
		
		JFrame frame = new JFrame("Conway's game of life by Sammy Shin");
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new GamePanel());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
	
	public static void main(String[] args){
		GameOfLife life = new GameOfLife();
	}
	
}
