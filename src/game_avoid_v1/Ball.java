package game_avoid_v1;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	double xVel, yVel, x, y;
	int radius = 35;
	
	public Ball() {
		x = Avoid.getWIDTH()/2;
		y = 10;
		xVel = getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
	}
	
	public double getRandomSpeed() {
		return (Math.random() * 3 +2);
	}
	
	public int getRandomDirection() {
		double rand = Math.random();
		if(rand < 0.5)
			return 1;
		else
			return -1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x-10, (int)y-10, getRadius()*2, getRadius()*2);
	}
	
	// This part is to be revised and improved.
	public boolean planeCollision(Plane p) {
		int index = 0;
		if(getY() >= p.getY() && getY() <= p.getY() + p.getHeight()) {
			if(getX() <= p.getX() && getX() + getRadius() >= p.getX()) {
				++index;				
			} else if(getX() > p.getX() && getX() <= p.getX() + p.getWidth() + getRadius()) {
				++index;
			}
		} 
		return index > 0;
	}
	
	public void move() {
		x += xVel;
		y += yVel;
		
		if(y<10)
			yVel = -yVel;
		if(y>490)
			yVel = -yVel;
		if(x<10)
			xVel = -xVel;
		if(x>690)
			xVel = -xVel;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public int getRadius() {
		return (int)radius;
	}

}
