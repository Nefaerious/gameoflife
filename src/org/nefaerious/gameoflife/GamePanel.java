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
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener{

	ArrayList<Cell> list = new ArrayList<Cell>();
	Timer timer;
	Cell[][] celllist;
	int speed;
	
	public GamePanel(){
		timer = new Timer(1000/90, this);
		timer.start();
		int x = 1;
		int y = 1;
		speed = 60;
		celllist = new Cell[120][120];
		for(int i = 0; i < 120; i++){
			for(int a = 0; a < 120; a++){
				Cell c = new Cell(x, y);
				list.add(c);
				celllist[i][a] = c;
				x+= 5;
			}
			x = 1;
			y +=5;
		}
		
		celllist[64][63].setAlive();
		celllist[62][63].setAlive();
		celllist[63][62].setAlive();
		celllist[63][64].setAlive();
		
		this.addMouseListener(this);
		this.addKeyListener(this);
		setFocusable(true);
	}
	
	
	public void update(){
		
		for(int i = 0; i < 120; i++){
			for(int a = 0; a < 120; a++){
				int s = 0;
				if(i == 0 && a == 0){
					s+= celllist[i][a+1].ifAlive()?1:0;
					s+= celllist[i+1][a].ifAlive()?1:0;
					s+= celllist[i+1][a+1].ifAlive()?1:0;
				}else if(i == 0 && a == 119){
					s+= celllist[i][a-1].ifAlive()?1:0;   //4
					s+= celllist[i+1][a-1].ifAlive()?1:0; //6
					s+= celllist[i+1][a].ifAlive()?1:0;   //7
				}else if(i == 119 && a == 0){
					s+= celllist[i-1][a].ifAlive()?1:0;   //2
					s+= celllist[i-1][a+1].ifAlive()?1:0; //3
					s+= celllist[i][a+1].ifAlive()?1:0;   //5
				}else if(i == 119 && a == 119){
					s+= celllist[i-1][a-1].ifAlive()?1:0; //1
					s+= celllist[i-1][a].ifAlive()?1:0;   //2
					s+= celllist[i][a-1].ifAlive()?1:0;   //4
				}else if(i == 0){
					s+= celllist[i][a-1].ifAlive()?1:0;   //4
					s+= celllist[i][a+1].ifAlive()?1:0;   //5
					s+= celllist[i+1][a-1].ifAlive()?1:0; //6
					s+= celllist[i+1][a].ifAlive()?1:0;   //7
					s+= celllist[i+1][a+1].ifAlive()?1:0; //8
				}else if(a == 0){
					s+= celllist[i-1][a].ifAlive()?1:0;
					s+= celllist[i-1][a+1].ifAlive()?1:0;
					s+= celllist[i][a+1].ifAlive()?1:0;
					s+= celllist[i+1][a].ifAlive()?1:0;
					s+= celllist[i+1][a+1].ifAlive()?1:0;
				}else if(i == 119){
					s+= celllist[i-1][a-1].ifAlive()?1:0; //1
					s+= celllist[i-1][a].ifAlive()?1:0;   //2
					s+= celllist[i-1][a+1].ifAlive()?1:0; //3
					s+= celllist[i][a-1].ifAlive()?1:0;   //4
					s+= celllist[i][a+1].ifAlive()?1:0;   //5
				}else if (a == 119){
					s+= celllist[i-1][a-1].ifAlive()?1:0; //1
					s+= celllist[i-1][a].ifAlive()?1:0;   //2
					s+= celllist[i][a-1].ifAlive()?1:0;   //4
					s+= celllist[i+1][a-1].ifAlive()?1:0; //6
					s+= celllist[i+1][a].ifAlive()?1:0;   //7
				}else{
					s+= celllist[i-1][a-1].ifAlive()?1:0; //1
					s+= celllist[i-1][a].ifAlive()?1:0;   //2
					s+= celllist[i-1][a+1].ifAlive()?1:0; //3
					s+= celllist[i][a-1].ifAlive()?1:0;   //4
					s+= celllist[i][a+1].ifAlive()?1:0;   //5
					s+= celllist[i+1][a-1].ifAlive()?1:0; //6
					s+= celllist[i+1][a].ifAlive()?1:0;   //7
					s+= celllist[i+1][a+1].ifAlive()?1:0; //8
				}
				celllist[i][a].checkAlive(s); 
			}
		}
		
		repaint();	
	}
	
	public void paintComponent(Graphics g){
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 601, 601);
		
		g.setColor(Color.DARK_GRAY);
		for(int i = 0; i < list.size()-1; i++){
			g.drawRect(list.get(i).getX(), list.get(i).getY(), list.get(i).getWidth(), list.get(i).getHeight());
				if(list.get(i).ifAlive()){
					g.setColor(Color.YELLOW);
					g.fillRect(list.get(i).getX(), list.get(i).getY(), list.get(i).getWidth(), list.get(i).getHeight());
				}
				g.setColor(Color.DARK_GRAY);
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x =  e.getX();
		int y = e.getY()-3;
		for(int i = 0; i < list.size()-1; i++){
			if(list.get(i).isPoint(x, y)){
				list.get(i).setAlive();
			}
		}	
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
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			speed+=10;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(speed -10 <= 0){
				throw new IllegalArgumentException("Won't work!");
			}else
			speed-=10;
		}
		System.out.println(speed);
		timer.setDelay(1000/speed);
	}


	@Override
	public void keyPressed(KeyEvent e) {
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
