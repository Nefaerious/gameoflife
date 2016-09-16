package org.nefaerious.gameoflife;

public class Cell {

	int x, y, width, height;
	boolean alive;
	
	public Cell(){
		x = 0;
		y = 0;
		width = 5;
		height = 5;
		alive = false;
	}
	
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
		width = 5;
		height = 5;
		alive = false;	
	}
	
	public void checkAlive(int nei){
		int neighbors = nei;
		switch(neighbors){
		case 0: alive = false; break;
		case 1: alive = false; break;
		case 2: if(alive){alive = true;} break;
		case 3: alive = true; break;
		case 4: alive = false; break;
		case 5: alive = false; break;
		case 6: alive = false; break;
		case 7: alive = false; break;
		case 8: alive = false; break;
		}
	}
	public boolean isPoint(int xs, int ys){
		if(this.x < xs && this.x + width > xs && this.y < ys && this.y + height > ys ){
			return true;
		}
		return false;
	}
	
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public boolean ifAlive(){
		return alive;
	}
	public void setAlive(boolean value){
		alive = value;
	}
	
	
	
}
