package com.thehe.WHGRL.utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import com.thehe.WHGRL.entity.Obstacle;
import com.thehe.WHGRL.entity.Player;
import com.thehe.WHGRL.map.Map;
import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.map.TileType;

public class Window implements Runnable, KeyListener {
	
	public int width;
	public int height;
	
	// window boilerplate
	public JFrame window;
	public Canvas canvas;
	public BufferStrategy bufferStrategy;
	
	// long time
	public long lastSecond;
	public long lastFrame;
	public long now;
	
	// actual times for rendering and ticking
	public long frameTime;
	public long tickTime;
	public long tickTimeRemaining;
	
	// keeping track of TPS and FPS
	public int frames;
	public int ticks;
	public int FPS;
	public int TPS;
	
	public boolean running;
	
	// tests
	Tile test;
	Tile test2;
	Player player;
	Obstacle obstacle;
	Map map;
	
	public Window(Dimension screenSize) throws FileNotFoundException {
		
		
		map = new Map("maps/level_1.txt");
		
		width = Tile.SIZE * Map.GRID_WIDTH;
		height = Tile.SIZE * Map.GRID_HEIGHT;
		
		lastSecond = System.nanoTime();
		lastFrame = System.nanoTime();
		
		GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration();
		
		window = new JFrame("WHG");
		canvas = new Canvas(graphicsConfiguration);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setPreferredSize(new Dimension(width, height));
		
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setSize(new Dimension(width, height));
		canvas.addKeyListener(this);
		canvas.setFocusable(true);
		
		window.add(canvas);
		window.pack();
		
		window.setVisible(true);
		canvas.setVisible(true);
		
		window.setResizable(false);

	
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		
		player = new Player();
		obstacle = new Obstacle();
		
		Vector something = new Vector(map.spawnTiles.get(0).position.x, map.spawnTiles.get(0).position.y);
		Vector somethingTwo = new Vector(map.spawnTiles.get(map.spawnTiles.size() - 1).position.x + Tile.SIZE,
				map.spawnTiles.get(map.spawnTiles.size() - 1).position.y + Tile.SIZE);
		
		something.addVector(somethingTwo);
		
		something.x /= 2;
		something.y /= 2;
		
		
		player.position.x = something.x;
		player.position.y = something.y;
		

		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running) {
			
			now = System.nanoTime();
			
			if(now - lastSecond >= Time.NANOSECOND_PER_SECOND) {
				
				FPS = frames;
				TPS = ticks;
				frames = 0;
				ticks = 0;
				
				lastSecond = now;
				
				System.out.println("FRAMES: " + FPS + " TICKS: " + TPS);
				
			}
			
			frameTime = now - lastFrame;
			lastFrame = now;
			
			tickTime = frameTime + tickTimeRemaining;
			if(tickTime >= Time.NANOSECOND_PER_TICK) {
				ticks++;
				tick();
				tickTime -= Time.NANOSECOND_PER_TICK;
			}
			
			tickTimeRemaining = tickTime;

			frames++;
			render();
			
//			try {
//				long sleepTime = ((16 * 1000000) - (System.nanoTime() - now)) / 1000000;
//				Thread.sleep(sleepTime < 0 ? 0 : sleepTime);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				
			
		}
		
	}
	
	public void render() {
		
		Graphics2D graphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
		graphics2D.setColor(Color.WHITE);
		graphics2D.fillRect(0, 0, width, height);
		

		map.render(graphics2D);
		player.render(graphics2D);
		
		graphics2D.dispose();
		
		// buffer can be lost for many reasons.
		// this is just to make sure we dont waste time by showing something that isnt even there
		if(!bufferStrategy.contentsLost()) {
			bufferStrategy.show();
		}
		
		
	}
	
	public void tick() {
		map.tick();
	}
	
	public void start() {
		new Thread(this).start();
		running = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// HAVE THE SCENE HANDLE SUM

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
