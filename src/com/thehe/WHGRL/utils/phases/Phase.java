package com.thehe.WHGRL.utils.phases;

import com.thehe.WHGRL.utils.Vector;

public class Phase {
	
	public Vector position;
	public Vector velocity;
	public double angleSpeed;
	public double duration;
	public int delay;
	
	public boolean positionEquals(Vector other) {
		return position.equals(other);
	}
	
}
