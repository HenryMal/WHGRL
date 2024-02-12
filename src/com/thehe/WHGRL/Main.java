package com.thehe.WHGRL;

import java.awt.*;

import com.thehe.WHGRL.utils.Window;

public class Main {
	
	public static void main(String[] args) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		Window window = new Window(screenSize);
		window.start();
		
	}

}
