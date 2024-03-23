package com.thehe.WHGRL.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RectangularShape;

import com.thehe.WHGRL.utils.Vector;

public class Entity {
	
	public RectangularShape body;
	
	public Color entityColor;
	public Color outlineColor;
	
	public Vector position;
	public Vector velocity;
	
	public double size;
	public double outlineSize;
	
	public double outlineOffset;
	public double sizeOffset;
	
	public double opacity;
	
	public Entity() {
		position = new Vector();
		velocity = new Vector();
		
	}
	
	public void render(Graphics2D graphics2D) {
		
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		body.setFrame(position.x - outlineOffset, position.y - outlineOffset, outlineSize, outlineSize);
		
		graphics2D.setColor(outlineColor);
		graphics2D.fill(body);
		
		body.setFrame(position.x - sizeOffset, position.y - sizeOffset, size, size);
		
		graphics2D.setColor(entityColor);
		graphics2D.fill(body);
		
	}

}
