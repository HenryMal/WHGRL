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

}
