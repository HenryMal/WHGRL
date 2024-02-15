package com.thehe.WHGRL.entity;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.Vector;

public class Obstacle extends Entity {
	
	public List<Vector> phases;
	public int phasesIndex;
	
	public Obstacle() {
		super();
		
		phases = new ArrayList<Vector>();
		phasesIndex = 0;
		
		size = Tile.SIZE / 4;
		outlineSize = Tile.SIZE / 2;

	
		body = new Ellipse2D.Double();
		entityColor = new Color(0, 0, 255);
		
		
		
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
		
		

		
		

	}

}
