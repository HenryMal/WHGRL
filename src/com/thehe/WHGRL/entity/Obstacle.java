package com.thehe.WHGRL.entity;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.Vector;

public class Obstacle extends Entity {
	
	public Vector topLeft;
	public Vector topRight;
	public Vector bottomLeft;
	public Vector bottomRight;
	
	public List<Vector> phases;
	public int phasesIndex;
	
	public Obstacle() {
		super();
		
		phases = new ArrayList<Vector>();
		phasesIndex = 0;
		
		size = Tile.SIZE / 4;
		outlineSize = Tile.SIZE / 2;
		outlineOffset = outlineSize / 2;
		sizeOffset = size / 2;
		
		calculateCornerPositions();

		body = new Ellipse2D.Double();
		
		opacity = 255;
		
		entityColor = new Color(0, 0, 255, (int) opacity);
		outlineColor = new Color(0, 0, 0, (int) opacity);
	
	}
	
	public void calculateCornerPositions() {
		topLeft = new Vector(position.x - outlineOffset, position.y - outlineOffset);
		topRight = new Vector(position.x - outlineOffset + outlineSize, position.y - outlineOffset);
		bottomLeft = new Vector(position.x - outlineOffset, position.y - outlineOffset + outlineSize);
		bottomRight = new Vector(position.x - outlineOffset + outlineSize, position.y - outlineOffset + outlineSize);
	}
	
	public boolean collides(double x, double y) {
		return (x >= topLeft.x &&
				y >= topLeft.y &&
				x < bottomRight.x &&
				y < bottomRight.y);
	}

	
	@Override
	public void render(Graphics2D graphics2D) {	
		super.render(graphics2D);	
	}
	
	public void tick() {

		// a very hacky solution, will fix later

		if (position.equals(phases.get((phasesIndex)))) {
			phasesIndex += 1;
			velocity.scaleVector(-1);
			
			if(phasesIndex >= phases.size()) {
				phasesIndex = 0;
			}
		}

		position.addVector(velocity);
		
		calculateCornerPositions();
		
	

	}

}
