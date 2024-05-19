package com.thehe.WHGRL.entity;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import com.thehe.WHGRL.utils.MathUtils;
import com.thehe.WHGRL.utils.Phase;
import com.thehe.WHGRL.utils.Vector;

public class Obstacle extends Entity {
	
	public List<Phase> phases;
	
	public int phasesIndex;
	public int clampIndex;
	
	public Obstacle(Vector position, Vector velocity, List<Phase> phases) {
		super(position, velocity, 0.25, 0.5);
		
		this.phases = phases;
		
		phasesIndex = 0;
		clampIndex = phases.size() - 1;
		
		body = new Ellipse2D.Double();

		entityColor = new Color(0, 0, 255, (int) opacity);
	
	}
	
	@Override
	public void render(Graphics2D graphics2D) {	
		super.render(graphics2D);	
	}
	
	public void tick() {

		if ((phases.get(phasesIndex).positionEquals(position))) {

			velocity.setVector(phases.get(phasesIndex).velocity);
			phasesIndex = (phasesIndex + 1) % phases.size();
			clampIndex = (clampIndex + 1) % phases.size();

		}

		position.addVector(velocity);
		
		position.x = MathUtils.clamp(
				Math.min(phases.get(clampIndex).position.x, phases.get(((1 + clampIndex)) % phases.size()).position.x),
				Math.max(phases.get(clampIndex).position.x, phases.get(((1 + clampIndex)) % phases.size()).position.x),
				position.x
		);
		
		position.y = MathUtils.clamp(
				Math.min(phases.get(clampIndex).position.y, phases.get(((1 + clampIndex)) % phases.size()).position.y),
				Math.max(phases.get(clampIndex).position.y, phases.get(((1 + clampIndex)) % phases.size()).position.y),
				position.y
		);
		
		super.tick();

	}

}
