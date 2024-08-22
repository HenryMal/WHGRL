package com.thehe.WHGRL.entity.obstacles;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

import com.thehe.WHGRL.entity.Entity;
import com.thehe.WHGRL.utils.MathUtils;
import com.thehe.WHGRL.utils.Time;
import com.thehe.WHGRL.utils.Vector;
import com.thehe.WHGRL.utils.phases.Phase;

public class Obstacle extends Entity {
	
	public List<Phase> phases;
	
	public int phasesIndex;
	public int clampIndex;
	public int tick;
	
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
		
		
//		
//		if(!phases.get(phasesIndex).isCoordinateOfRotationEmpty()) {
//			
//			position.x = phases.get(phasesIndex).coordinateOfRotation.x + phases.get(phasesIndex).radius * Math.cos(phases.get(phasesIndex).angle);
//			position.y = phases.get(phasesIndex).coordinateOfRotation.y + phases.get(phasesIndex).radius * Math.sin(phases.get(phasesIndex).angle);
//			phases.get(phasesIndex).angle += phases.get(phasesIndex).angleVelocity;
//			
//		}
//
//		tick++;


		super.tick();

	}

}
