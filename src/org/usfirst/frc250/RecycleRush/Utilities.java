package org.usfirst.frc250.RecycleRush;

public class Utilities {
	public static double motorRamp(double maxChange, double target, double current) {
		if (maxChange + current > target && current - maxChange < target) {
			return target;
		} else if (current < target) {
			return current + maxChange;
		} else if (current > target) {
			return current - maxChange;
		} else {
			return 0;
		}
	}
	
	public static double motorRamp(double maxAcceleration, double maxDeccelaration, double target, double current) {
		if ((current >= 0 && target - current > 0) || (current <= 0 && target - current < 0)) {
			return motorRamp(maxAcceleration, target, current);
		} else {
			return motorRamp(maxDeccelaration, target, current);
		}
	}
	
	public static double formatAngle (double angle) {
		return (angle % 360 + 360) % 360;
	}
	
	public static double tangentAngle (double opposite, double adjacent) {
		return Math.toDegrees(Math.atan(opposite / adjacent));
	}
	
	public static double angleDifference (double a, double b) {
		a = formatAngle(a);
		b = formatAngle(b);
		double d = a-b;
		if (d > 180) {
    		d -= 360;
    	}
    	if (d < -180) {
    		d += 360;
    	}
		return d;
	}
}
