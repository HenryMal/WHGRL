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
	
	public Vector(Vector vector) {
		this.setVector(vector);
	}
	
	public void setVector(Vector vector) {
		this.x = vector.x;
		this.y = vector.y;
	}
	
	public void setVector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void addVector(Vector vector) {
		this.x += vector.x;
		this.y += vector.y;
	}
	
	public void addVector(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void subtractVector(Vector vector) {
		this.x -= vector.x;
		this.y -= vector.y;
	}
	
	public void subtractVector(double x, double y) {
		this.x -= x;
		this.y -= y;
	}
	
	public void scaleVector(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}
	
	public boolean equals(Vector vector) {
		return (x == vector.x && y == vector.y);
	}

}
