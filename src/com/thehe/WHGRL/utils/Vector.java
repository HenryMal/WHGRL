package com.thehe.WHGRL.utils;

public class Vector {
	
	public int x;
	public int y;
	
	public Vector() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector(int x, int y) {
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
