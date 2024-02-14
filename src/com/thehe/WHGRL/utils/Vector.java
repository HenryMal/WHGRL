package com.thehe.WHGRL.utils;

public class Vector {
	
	public double x;
	public double y;
	
	public Vector() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void addVector(Vector vector) {
		x += vector.x;
		y += vector.y;
	}
	
	public void subtractVector(Vector vector) {
		x -= vector.x;
		y -= vector.y;
	}
	
	public void scaleVector(double scalar) {
		x *= scalar;
		y *= scalar;
	}

}
