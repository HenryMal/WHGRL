package com.thehe.WHGRL.utils;

public class MathUtils {
	
	public static double clamp(double clampMin, double clampMax, double value) {
		
		if(value < clampMin) {
			return clampMin;
		}
		
		if (value > clampMax) {
			return clampMax;
		}
		
		return value;
		
	}
	
	public static double distance(Vector vectorOne, Vector vectorTwo) {
		return Math.sqrt((vectorTwo.x - vectorOne.x) * (vectorTwo.x - vectorOne.x) + (vectorTwo.y - vectorOne.y) * (vectorTwo.y - vectorOne.y));
	}

}
