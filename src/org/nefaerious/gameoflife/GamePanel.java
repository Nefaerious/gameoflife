package org.nefaerious.gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener{

	ArrayList<Cell> list = new ArrayList<Cell>();
	Timer timer;
	
	public GamePanel(){
		timer = new Timer(1000/60, this);
		timer.start();
		int x = 1;
		int y = 1;
		for(int i = 0; i < 120; i++){
			for(int a = 0; a < 120; a++){
				list.add(new Cell(x, y));
				y+= 5;
			}
			y = 1;
			x +=5;
		}
		
		this.addMouseListener(this);
		this.addKeyListener(this);
		setFocusable(true);
	}
	
	
	public void update(){

		
		repaint();	
	}
	
	public void paintComponent(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 601, 601);
		
		g.setColor(Color.DARK_GRAY);
		for(int i = 0; i < list.size()-1; i++){
			g.drawRect(list.get(i).getX(), list.get(i).getY(), list.get(i).getWidth(), list.get(i).getHeight());
				if(list.get(i).ifAlive()){
					g.setColor(Color.YELLOW);
					g.fillRect(list.get(i).getX(), list.get(i).getY(), list.get(i).getWidth(), list.get(i).getHeight());
				}
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}


	@Override
	public void keyPressed(KeyEvent e) {
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
