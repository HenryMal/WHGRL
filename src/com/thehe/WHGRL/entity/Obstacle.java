package com.thehe.WHGRL.entity;

import java.awt.*;
import java.awt.geom.*;

import com.thehe.WHGRL.map.Tile;

public class Obstacle extends Entity {
	
	public Obstacle() {
		super();
		
		size = Tile.size / 2;
		outlineSize = Tile.size * 0.67;
		
		body = new Ellipse2D.Double();
		entityColor = new Color(0, 0, 255);
		
	}

	
	@Override
	public void render(Graphics2D graphics2D) {	
		super.render(graphics2D);	
	}
	
	public void tick() {
		
	}

}
