package com.thehe.WHGRL.map;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import com.thehe.WHGRL.utils.Vector;
import com.thehe.WHGRL.utils.Window;

public class Tile {
	
	public static int tileCount;
	public static double size;
	
	public Vector position;
	public Color tileColor;
	public Rectangle2D body;
	
	public TileType tileType;

	public Tile(TileType tileType) {
		
		tileCount++;
		size = Window.width / (double) Map.GRID_WIDTH;
		
		this.position = new Vector();
		this.tileType = tileType;
		setTileColor();
		
		System.out.println(size);
		
	}
	
	public void setTileColor() {
		
		if (tileType == TileType.REGULAR) {
			tileColor = (tileCount % 2 == 1) ? new Color(231, 228, 255) : new Color(248, 247, 255);
			return;
		}
		
		if(tileType == TileType.SPAWN || tileType == TileType.GOAL) {
			tileColor = new Color(188, 255, 183);
			return;
		}
		
		tileColor = new Color(182, 176, 250);
			
	}
	
	public void render(Graphics2D graphics2D) {
		
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		body = new Rectangle2D.Double(position.x, position.y, size, size);
		
		graphics2D.setColor(tileColor);
		graphics2D.fill(body);
		
	}
	

}
