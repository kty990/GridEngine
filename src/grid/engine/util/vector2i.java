package me.grid.engine.util;

public class vector2i {

	private int x, y;
	
	public vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void set(vector2i v) {
		this.x = v.getX();
		this.y = v.getY();
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void incrementX() {
		this.x++;
	}
	
	public void decrementX() {
		this.x--;
	}
	
	public void incrementY() {
		this.y++;
	}
	
	public void decrementY() {
		this.y--;
	}
	
	public void display() {
		System.out.println("X: " + Integer.toString(x) + " | Y: " + Integer.toString(y));
	}
	
}
