package org.usfirst.frc.team1458.robot;

import com.team1458.turtleshell.Input;
import com.team1458.turtleshell.TurtleRobot;
import com.team1458.turtleshell.TurtleSmartTankChassis;
import com.team1458.turtleshell.TurtleTankChassis;

public class Robot extends TurtleRobot {

	public Robot() {

	}

	public void robotInit() {
		chassis = RedTies2015Chassis.getInstance();
		auto = RedTies2015Auto1.getInstance();

	}

	public void autonomous() {
		// Put the code to initialise autonomous here.
		auto.init();
		while (isAutonomous() && isEnabled()) {
			// This is the main loop for autonomous.
			auto.calculate(new double[] { ((TurtleSmartTankChassis) chassis).getLeftEncoder(),
					((TurtleSmartTankChassis) chassis).getLeftEncoder() });
			chassis.allDrive(auto.getMotors());
		}
	}

	public void operatorControl() {
		// Put the code to initialise operator control here.

		while (isOperatorControl() && isEnabled()) {
			// This is the main loop for operator control.
			((TurtleTankChassis) chassis).tankDrive(Input.getLPower(), Input.getRPower());
		}
	}

	public void test() {
		// Code for testing mode
	}
}