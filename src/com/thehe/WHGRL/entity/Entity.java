package com.thehe.WHGRL.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RectangularShape;

import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.AABB;
import com.thehe.WHGRL.utils.Vector;

public class Entity {
	
	public RectangularShape body;
	
	public Color entityColor;
	public Color outlineColor;
	
	public Vector position;
	public Vector velocity;
	
	public AABB hitbox;
	
	public double size;
	public double outlineSize;
	
	public double outlineOffset;
	public double sizeOffset;
	
	public double opacity;
	
	
	public Entity(Vector position, Vector velocity, double sizeScale, double outlineScale) {
		
		this.position = new Vector(position.x, position.y);
		this.velocity = new Vector(velocity.x, velocity.y);
	
		size = Tile.SIZE * sizeScale;
		outlineSize = Tile.SIZE * outlineScale;
		
		opacity = 255;
		outlineColor = new Color(0, 0, 0, (int) opacity);
		
		calculateOffsets();
		calculateAABB();
	}
	
	public void calculateOffsets() {
		outlineOffset = outlineSize / 2;
		sizeOffset = size / 2;
	}
	
	public void calculateAABB() {
		
		hitbox = new AABB(
				position.x - outlineOffset, 
				position.y - outlineOffset, 
				position.x - outlineOffset + outlineSize,
				position.y - outlineOffset + outlineSize);
		
	}
	
	public boolean collides(Entity entity) {
		return hitbox.collides(entity.hitbox);
	}
	
	public boolean collides(double minX, double minY, double maxX, double maxY) {
		return hitbox.collides(minX, minY, maxX, maxY);
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
	
	public void tick() {
		calculateAABB();
	}

}
