package com.thehe.WHGRL.entity.obstacles;

import java.awt.Graphics2D;
import java.util.List;

import com.thehe.WHGRL.utils.MathUtils;
import com.thehe.WHGRL.utils.Vector;
import com.thehe.WHGRL.utils.phases.Phase;

public class StandardObstacle extends Obstacle {

	public StandardObstacle(Vector position, Vector velocity, List<Phase> phases) {
		super(position, velocity, phases);
	}
	
	@Override
	public void render(Graphics2D graphics2D) {	
		super.render(graphics2D);	
	}
	
	public void tick() {
		
		if ((phases.get(phasesIndex).positionEquals(position)) && tick >= phases.get(phasesIndex).delay * 60) {

			velocity.setVector(phases.get(phasesIndex).velocity);
			phasesIndex = (phasesIndex + 1) % phases.size();
			clampIndex = (clampIndex + 1) % phases.size();
			tick = 0;
			
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
		
		tick++;
		
		super.tick();
		
	}

}
