package com.thehe.WHGRL.entity;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import com.thehe.WHGRL.utils.Vector;

public class Obstacle extends Entity {
	
	public List<Vector> phases;
	public int phasesIndex;
	
	public Obstacle(Vector position, Vector velocity) {
		super(position, velocity, 0.25, 0.5);
		
		phases = new ArrayList<Vector>();
		phasesIndex = 0;
		
		body = new Ellipse2D.Double();

		entityColor = new Color(0, 0, 255, (int) opacity);
	
	}
	
	@Override
	public void render(Graphics2D graphics2D) {	
		super.render(graphics2D);	
	}
	
	public void tick() {

		if (position.equals(phases.get((phasesIndex)))) {
			
			phasesIndex = (phasesIndex + 1) % phases.size();
			velocity.scaleVector(-1);

		}

		position.addVector(velocity);
		
		super.tick();

	}

}
