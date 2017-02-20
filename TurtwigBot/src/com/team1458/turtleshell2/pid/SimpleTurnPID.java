package com.team1458.turtleshell2.pid;

import com.team1458.turtleshell2.util.PIDConstants;
import com.team1458.turtleshell2.util.types.Angle;
import com.team1458.turtleshell2.util.types.MotorValue;
import com.team1458.turtleshell2.util.types.Tuple;

/**
 * A PID class for turning, uses only a gyro sensor
 * 
 * @author asinghani
 */
public class SimpleTurnPID {
	private final PID pid;

	public SimpleTurnPID(Angle angle, Angle deadband, PIDConstants constants) {
		pid = new PID(constants, angle.getDegrees(), deadband.getDegrees());

	}

	public Tuple<MotorValue, MotorValue> newValue(Angle currentAngle) {
		double value = pid.newValue(currentAngle.getDegrees());

		return new Tuple<>(new MotorValue(value), new MotorValue(-1 * value));
	}

	public boolean atTarget() {
		return pid.atTarget();
	}
}