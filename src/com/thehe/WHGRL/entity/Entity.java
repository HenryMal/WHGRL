package com.thehe.WHGRL.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RectangularShape;

import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.Vector;

public abstract class Entity {
	
	public RectangularShape body;
	
	public Color entityColor;
	
	public Vector position;
	public Vector velocity;
	
	public double size;
	public double outlineSize;
	
	public Entity() {
		position = new Vector();
		velocity = new Vector();
		
		size = Tile.SIZE / 2;
		outlineSize = Tile.SIZE * 0.67;
	}
	
	public void render(Graphics2D graphics2D) {
		
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		body.setFrame(position.x - outlineSize / 2, position.y - outlineSize / 2, outlineSize, outlineSize);
		
		graphics2D.setColor(Color.BLACK);
		graphics2D.fill(body);
		
		body.setFrame(position.x - size / 2, position.y - size / 2, size, size);
		
		graphics2D.setColor(entityColor);
		graphics2D.fill(body);
		
	}

}
