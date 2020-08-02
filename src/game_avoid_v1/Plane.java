package game_avoid_v1;

import java.awt.Graphics;
import java.awt.Color;

public class Plane {
	double x, y, xVel, yVel;
	final double GRAVITY = 0.94;
	boolean upAccel, downAccel, leftAccel, rightAccel;
	public Plane() {
		upAccel = false; downAccel = false; 
		leftAccel = false; rightAccel = false;
		y = 210; yVel = 0;
		x = 350; xVel = 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 10, 20);
	}
	
	public void move() {
		if(upAccel) {
			yVel -= 2;
		} else if(downAccel) {
			yVel += 2;
		} else if(!upAccel && !downAccel) {
			yVel *= GRAVITY;
		}
		
		if(yVel >= 5) {
			yVel = 5;
		} else if(yVel <= -5) {
			yVel = -5;
		}
		y += yVel;
		
		if(leftAccel) {
			xVel -= 2;
		} else if(rightAccel) {
			xVel += 2;
		} else if(!leftAccel && !rightAccel) {
			xVel *= GRAVITY;
		}
		
		if(xVel >= 5) {
			xVel = 5;
		} else if(xVel <= -5) {
			xVel = -5;
		}
		x += xVel;
			
			
		if(y<0)
			y = 0;
		if(y>480)
			y = 480;
		if(x<0)
			x = 0;
		if(x>690)
			x = 690;
	}
	
	public void setUpAccel(boolean input) {
		upAccel = input;
	}
	
	public void setDownAccel(boolean input) {
		downAccel = input;
	}
	
	public void setLeftAccel(boolean input) {
		leftAccel = input;
	}
	
	public void setRightAccel(boolean input) {
		rightAccel = input;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}

}
