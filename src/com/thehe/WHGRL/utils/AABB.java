package com.thehe.WHGRL.utils;

public class AABB {
	
	public double minX;
	public double minY;
	public double maxX;
	public double maxY;
	
	public AABB(double minX, double minY, double maxX, double maxY) { 
		
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		
	}
	
	public boolean collides(AABB other) {
		
		return (other.minX <= maxX &&
				other.minY <= maxY &&
				other.maxX >= minX &&
				other.maxY >= minY);
		
	}
	
	public boolean collides(double minX, double minY, double maxX, double maxY) {
		
		return (minX <= this.maxX &&
				minY <= this.maxY &&
				maxX >= this.minX &&
				maxY >= this.minY);
		
	}
	
}
