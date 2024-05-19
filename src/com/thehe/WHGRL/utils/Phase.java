package com.thehe.WHGRL.utils;

import com.thehe.WHGRL.map.Tile;

public class Phase {
	
	public Vector position;
	public Vector velocity;
	
	public double delay;
	
	public Phase(Vector position, Vector velocity) {
		this.position = new Vector(position);
		this.velocity = new Vector(velocity);
		
		this.position.scaleVector(Tile.SIZE);
		this.position.subtractVector(Tile.SIZE / 2, Tile.SIZE / 2);
	}
	
	public boolean positionEquals(Vector other) {
		return position.equals(other);
	}
	
}
