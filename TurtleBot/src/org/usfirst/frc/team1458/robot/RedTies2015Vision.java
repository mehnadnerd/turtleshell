package org.usfirst.frc.team1458.robot;

import com.team1458.turtleshell.TurtleVision;

public class RedTies2015Vision extends TurtleVision {

	private static RedTies2015Vision instance;

	private RedTies2015Vision() {

	}

	public static RedTies2015Vision getInstance() {
		if (instance == null) {
			instance = new RedTies2015Vision();
		}
		return instance;
	}

}
