package com.thehe.WHGRL.entity.obstacles;

import java.awt.Graphics2D;
import java.util.List;

import com.thehe.WHGRL.utils.MathUtils;
import com.thehe.WHGRL.utils.Time;
import com.thehe.WHGRL.utils.Vector;
import com.thehe.WHGRL.utils.phases.Phase;

public class RotationalObstacle extends Obstacle {
	
	public Vector pointOfRotation;
	public double radius;
	public double angle;
	public double duration;
	public double angleSpeed;
	
	public RotationalObstacle(Vector position, Vector pointOfRotation, List<Phase> phases) {
		super(position, new Vector(), phases);
		
		this.pointOfRotation = new Vector(pointOfRotation);
		
		this.radius = MathUtils.distance(this.pointOfRotation, this.position);
		this.angle = Math.atan(position.y / position.x);
		
		this.angleSpeed = phases.get(phasesIndex).angleSpeed;

	}
	
	@Override
	public void render(Graphics2D graphics2D) {
		super.render(graphics2D);
	}
	
	public void tick() {
		
		if (duration >= phases.get(phasesIndex).duration * Time.TPS) {
			
			angleSpeed = 0;
			tick++;
			
			
			if(tick >= phases.get(phasesIndex).delay * Time.TPS) {
				
				phasesIndex = (phasesIndex + 1) % phases.size();
				clampIndex = (clampIndex + 1) % phases.size();
				
				duration = phases.get(phasesIndex).duration;
				angleSpeed = phases.get(phasesIndex).angleSpeed;
				tick = 0;
				
			}
			
		}
		
		position.x = pointOfRotation.x + radius * Math.cos(angle);
		position.y = pointOfRotation.y + radius * Math.sin(angle);
		
		angle += angleSpeed;
		
		if(angle >= 2.0 * Math.PI) {
			angle = 0;
		}
		
		duration++;
		
		super.tick();
	}

}
