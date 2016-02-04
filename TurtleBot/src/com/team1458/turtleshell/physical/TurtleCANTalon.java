package com.team1458.turtleshell.physical;

import com.team1458.turtleshell.MotorValue;
import com.team1458.turtleshell.TurtleMotor;

import edu.wpi.first.wpilibj.CANTalon;

public class TurtleCANTalon implements TurtleMotor {
	private CANTalon talon;
	private boolean isReversed = false;
	
	public TurtleCANTalon(int digitalID) {
		talon = new CANTalon(digitalID);
	}
	public TurtleCANTalon(int digitalID, boolean isReversed) {
		talon = new CANTalon(digitalID);
		this.isReversed=isReversed;
	}
	
	@Override
	public void set(MotorValue power) {
		talon.set((isReversed ? -1 : 1) * power.getValue());

	}

	@Override
	public MotorValue get() {
		return new MotorValue((isReversed ? -1 : 1) * talon.get());
	}

	@Override
	public boolean isReversed() {
		return isReversed;
	}

}