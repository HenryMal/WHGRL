package com.thehe.WHGRL.utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

public class Window implements Runnable, KeyListener {
	
	public static double width;
	public static double height;
	
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
	
	public Window(Dimension screenSize) {
		
		width = screenSize.getWidth() / 2.0;
		height = screenSize.getHeight() / 2.0;

		
		lastSecond = System.nanoTime();
		lastFrame = System.nanoTime();
		
		GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration();
		
		window = new JFrame("cartpole");
		canvas = new Canvas(graphicsConfiguration);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setPreferredSize(new Dimension((int) width, (int) height));
		
		canvas.setPreferredSize(new Dimension((int) width, (int) height));
		canvas.setSize(new Dimension((int) width, (int) height));
		canvas.addKeyListener(this);
		canvas.setFocusable(true);
		
		window.add(canvas);
		window.pack();
		
		window.setVisible(true);
		canvas.setVisible(true);
		
		window.setResizable(false);

	
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		
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
		graphics2D.fillRect(0, 0, (int) width, (int) height);
		
		graphics2D.dispose();
		
		// buffer can be lost for many reasons.
		// this is just to make sure we dont waste time by showing something that isnt even there
		if(!bufferStrategy.contentsLost()) {
			bufferStrategy.show();
		}
		
		
	}
	
	public void tick() {
		
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
