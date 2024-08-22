package com.thehe.WHGRL.utils.phases;

import com.thehe.WHGRL.map.Tile;
import com.thehe.WHGRL.utils.Vector;

public class OSPhase extends Phase {
	

	public OSPhase(Vector position, Vector velocity, int delay) {
		this.position = new Vector(position);
		this.velocity = new Vector(velocity);
		
		this.position.scaleVector(Tile.SIZE);
		this.position.subtractVector(Tile.SIZE / 2, Tile.SIZE / 2);
		
		this.delay = delay;
	}
	


}
