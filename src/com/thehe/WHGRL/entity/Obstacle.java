package com.thehe.WHGRL.entity;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import com.thehe.WHGRL.utils.MathUtils;
import com.thehe.WHGRL.utils.Vector;

public class Obstacle extends Entity {
	
	public List<Vector> phases;
	public int phasesIndex;
	public int clampIndex;
	
	public Obstacle(Vector position, Vector velocity) {
		super(position, velocity, 0.25, 0.5);
		
		phases = new ArrayList<Vector>();
		phasesIndex = 0;
		clampIndex = 0;
		
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
		clampIndex = (phasesIndex / 2) * 2;  // division by 2 so we can group phases by 2. so like 0, 1 or 2, 3
		
		
		position.x = MathUtils.clamp(
				Math.min(phases.get(clampIndex).x, phases.get(((1 + clampIndex)) % phases.size()).x),
				Math.max(phases.get(clampIndex).x, phases.get(((1 + clampIndex)) % phases.size()).x),
				position.x
		);
		
		position.y = MathUtils.clamp(
				Math.min(phases.get(clampIndex).y, phases.get(((1 + clampIndex)) % phases.size()).y),
				Math.max(phases.get(clampIndex).y, phases.get(((1 + clampIndex)) % phases.size()).y),
				position.y
		);

		
		super.tick();

	}

}
