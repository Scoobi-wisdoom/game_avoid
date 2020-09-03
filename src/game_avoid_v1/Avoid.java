package game_avoid_v1;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;

public class Avoid extends Applet implements Runnable, KeyListener {
	final static int WIDTH = 700;
	final int HEIGHT = 500;
	Thread thread;
	Plane p;
	Ball b1, b2, b3, b4, b5,  b6;
	boolean gameStarted, gameOver;
	Graphics gfx;
	Image img;
	
	public void init() {
		this.resize(WIDTH, HEIGHT);
		gameStarted = false;
		gameOver = false;
		this.addKeyListener(this);
		p = new Plane();
		b1 = new Ball();
		b2 = new Ball();
		b3 = new Ball();
		b4 = new Ball();
		b5 = new Ball();
		b6 = new Ball();
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0,0, WIDTH, HEIGHT);
		
		// If any of the balls collide with the plane, the plane's height or width is to be increased.
		if(b1.planeCollision(p) || b2.planeCollision(p) || b3.planeCollision(p) || b4.planeCollision(p) || b5.planeCollision(p) || b6.planeCollision(p)) {
			if(p.getHeight() <= HEIGHT) {
				p.increaseHeight();
			} else {
				p.increaseWidth();
			}
		}
		
		// If both of plane's width and height exceed the screen size, the game is over. 
		if(p.getWidth() > WIDTH && p.getHeight() > HEIGHT) {
			gameOver = true;
		}
		if(gameOver) {
			gfx.setColor(Color.red);
			gfx.drawString("Game Over", 300, 250);
		}else {
			p.draw(gfx);
			b1.draw(gfx);
			b2.draw(gfx);
			b3.draw(gfx);
			b4.draw(gfx);
			b5.draw(gfx);
			b6.draw(gfx);
		}
		
		if(!gameStarted) {
			gfx.setColor(Color.white);
			gfx.drawString("Avoid", 340, 100);
			gfx.drawString("Press Enter to Begin..", 310, 130);
		}
		g.drawImage(img, 0, 0, this);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void run() {
		for(;;) {
			if(gameStarted) {
				p.move();
				b1.move();
				b2.move();
				b3.move();
				b4.move();
				b5.move();
				b6.move();
			}
			
			repaint();
			try {
				Thread.sleep(10);			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p.setUpAccel(true);
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p.setDownAccel(true);
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			p.setLeftAccel(true);
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			p.setRightAccel(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p.setUpAccel(false);
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p.setDownAccel(false);
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			p.setLeftAccel(false);
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			p.setRightAccel(false);
		}
	}
	
	public void keyTyped(KeyEvent arg0) {
	}

	public static int getWIDTH() {
		return WIDTH;
	}


}
