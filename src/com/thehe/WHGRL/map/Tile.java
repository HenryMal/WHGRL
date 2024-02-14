package com.thehe.WHGRL.map;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import com.thehe.WHGRL.utils.Vector;
import com.thehe.WHGRL.utils.Window;

public class Tile {
	
	public static final int SIZE = 35;
	public static int tileCount;
	
	public Vector position;
	public Color tileColor;
	public Rectangle2D body;
	
	public TileType tileType;

	
	public Tile(TileType tileType, int positionX, int positionY) {
		
		tileCount++;
		
		this.position = new Vector(positionY * SIZE, positionX * SIZE);
		this.tileType = tileType;
		setTileColor(positionX, positionY);
		
		
	}
	
	public void setTileColor(int positionX, int positionY) {
		
		if (tileType == TileType.REGULAR) {
			
			boolean isXEven = positionX % 2 == 0;
			boolean isYEven = positionY % 2 == 0;
				
			tileColor = new Color(231, 228, 255);
			if(isXEven == isYEven) {
				tileColor = new Color(248, 247, 255);
			}

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
		
		body = new Rectangle2D.Double(position.x, position.y, SIZE, SIZE);
		
		graphics2D.setColor(tileColor);
		graphics2D.fill(body);
		
	}
	

}
