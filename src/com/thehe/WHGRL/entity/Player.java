package com.thehe.WHGRL.entity;

import java.awt.Color;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import com.thehe.WHGRL.map.Tile;

public class Player extends Entity {
	
	// set speed
	// do handle collision
	
	
	public Player() {
		super();
		
		
		size = Tile.SIZE / 2;
		outlineSize = Tile.SIZE * 0.67;

		body = new Rectangle2D.Double();
		entityColor = new Color(255, 0, 0);
		
	}
	
	@Override
	public void render(Graphics2D graphics2D) {
		super.render(graphics2D);
		
	}
	
	public void tick() {
		
	}

}
