package com.thehe.WHGRL;

import java.awt.*;
import java.io.FileNotFoundException;

import com.thehe.WHGRL.utils.Window;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		System.out.println(screenSize.toString());
		
		Window window = new Window(screenSize);
		window.start();
		
	}

}
