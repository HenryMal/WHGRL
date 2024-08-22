package com.thehe.WHGRL.utils.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.thehe.WHGRL.utils.Window;

public class Input {
	
	public static Key leftKey = new Key();
	public static Key rightKey = new Key();
	public static Key upKey = new Key();
	public static Key downKey = new Key();
	
	public static void init() {
		
		Window.canvas.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					leftKey.pressed = true;
				}
				
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					rightKey.pressed = true;
				}
				
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					upKey.pressed = true;
				}
				
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					downKey.pressed = true;
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					leftKey.pressed = false;
				}
				
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					rightKey.pressed = false;
				}
				
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					upKey.pressed = false;
				}
				
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					downKey.pressed = false;
				}
				
			}
			
		});
		
	}

}
