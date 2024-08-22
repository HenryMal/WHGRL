package com.thehe.WHGRL.utils;

import java.awt.*;
import java.awt.image.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import com.thehe.WHGRL.Game;
import com.thehe.WHGRL.map.Map;
import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.input.Input;

public class Window implements Runnable {
	
	public int width;
	public int height;
	
	// window boilerplate
	public static JFrame window;
	public static Canvas canvas;
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
	
	public Game game;

	
	public Window(Dimension screenSize) throws FileNotFoundException {
		
		game = new Game();
		
		width =  (int) (Tile.SIZE * Map.GRID_WIDTH);
		height = (int) (Tile.SIZE * Map.GRID_HEIGHT);
		
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
		canvas.setFocusable(true);
		
		window.add(canvas);
		window.pack();
		
		window.setVisible(true);
		window.setFocusable(true);
		canvas.setVisible(true);
		
		window.setResizable(false);

	
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
	
		
		Input.init();

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
				try {
					tick();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		

		game.render(graphics2D);
		
		graphics2D.dispose();
		
		// buffer can be lost for many reasons.
		// this is just to make sure we dont waste time by showing something that isnt even there
		if(!bufferStrategy.contentsLost()) {
			bufferStrategy.show();
		}
		
		
	}
	
	public void tick() throws FileNotFoundException {
		game.tick();
	}
	
	public void start() {
		new Thread(this).start();
		running = true;
	}






}
