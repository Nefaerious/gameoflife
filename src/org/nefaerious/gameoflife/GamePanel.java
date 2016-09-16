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
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener, KeyListener{

	List<Cell> list = new ArrayList<Cell>();
	Timer timer;
	Cell[][] celllist;
	int speed;
	
	public GamePanel(){
		timer = new Timer(1000/90, this);//keep it at 1000/90
		//timer.start();
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
		
//		celllist[100][63].setAlive(true);
//		celllist[101][63].setAlive(true);
//		celllist[102][63].setAlive(true);
//		
//		celllist[64][63].setAlive(true);
//		celllist[62][63].setAlive(true);
//		celllist[63][62].setAlive(true);
//		celllist[63][64].setAlive(true);

		
		this.addMouseListener(this);
		this.addKeyListener(this);
		setFocusable(true);
	}
	
	
	public void update(){
		Cell[][] tempest = new Cell[120][120];
		
		// Thai fixes things here.
		List<Cell> tempraylist = new ArrayList<>();
		
		// Just replicated the old cells because the state of the cell
		// is a function of its previous state. (That's something I figured out!)
		for(int i = 0; i < 120; i++){
			for(int a = 0; a < 120; a++){
				Cell old = celllist[i][a];
				Cell c = new Cell(old.x, old.y);
				c.setAlive(old.ifAlive());
				tempraylist.add(c);
				tempest[i][a] = c;
			}
		}
		
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
					tempest[i][a].checkAlive(s); 
				
			}
		}
		
		// Overwrite the old arrays and lists.
		celllist = tempest;
		list = tempraylist;
		
	}
	
	public void render() {		
		repaint();	
	}
	
	public void paintComponent(Graphics g){
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 601, 601);
		
		g.setColor(Color.DARK_GRAY);
		for(int i = 0; i < list.size()-1; i++){
			g.drawRect(list.get(i).getX(), list.get(i).getY(), list.get(i).getWidth(), list.get(i).getHeight());
				if(list.get(i).ifAlive()){
					g.setColor(Color.CYAN);
					g.fillRect(list.get(i).getX(), list.get(i).getY(), list.get(i).getWidth(), list.get(i).getHeight());
				}
				g.setColor(Color.DARK_GRAY);
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		render();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x =  e.getX();
		int y = e.getY()-3;
		for(int i = 0; i < list.size()-1; i++){
			if(list.get(i).isPoint(x, y)){
				list.get(i).setAlive(true);
			}
		}
		render();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			timer.stop();
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			timer.start();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
					
			int sC = 30;
			int sR = 30;
			
			celllist[sC][sR].setAlive(true);
			celllist[sC][sR+1].setAlive(true);
			celllist[sC+1][sR].setAlive(true);
			celllist[sC+1][sR+1].setAlive(true);
			celllist[sC][sR+10].setAlive(true);
			celllist[sC+1][sR+10].setAlive(true);
			celllist[sC+2][sR+10].setAlive(true);
			celllist[sC-1][sR+11].setAlive(true);
			celllist[sC-2][sR+12].setAlive(true);
			celllist[sC-2][sR+13].setAlive(true);
			celllist[sC+3][sR+11].setAlive(true);
			celllist[sC+4][sR+12].setAlive(true);
			celllist[sC+4][sR+13].setAlive(true);
			celllist[sC-1][sR+15].setAlive(true);
			celllist[sC][sR+16].setAlive(true);
			celllist[sC+3][sR+15].setAlive(true);
			celllist[sC+2][sR+16].setAlive(true);
			celllist[sC+1][sR+16].setAlive(true);
			celllist[sC+1][sR+17].setAlive(true);
			celllist[sC+1][sR+14].setAlive(true);	
			celllist[sC][sR+20].setAlive(true);
			celllist[sC-1][sR+20].setAlive(true);
			celllist[sC-2][sR+20].setAlive(true);
			celllist[sC][sR+21].setAlive(true);
			celllist[sC-1][sR+21].setAlive(true);
			celllist[sC-2][sR+21].setAlive(true);	
			celllist[sC+1][sR+22].setAlive(true);
			celllist[sC-3][sR+22].setAlive(true);
			celllist[sC+1][sR+24].setAlive(true);
			celllist[sC+2][sR+24].setAlive(true);
			celllist[sC-3][sR+24].setAlive(true);
			celllist[sC-4][sR+24].setAlive(true);		
			celllist[sC-2][sR+34].setAlive(true);
			celllist[sC-2][sR+35].setAlive(true);
			celllist[sC-1][sR+34].setAlive(true);
			celllist[sC-1][sR+35].setAlive(true);

		}
		
		// The space key can be used as a toggle button for time. ~ Thai
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (timer.isRunning()) timer.stop();
			else timer.start();
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
	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
