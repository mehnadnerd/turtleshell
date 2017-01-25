package org.usfirst.frc.team1458.robot.constants;

import com.team1458.turtleshell2.util.TurtleLogger;
import com.team1458.turtleshell2.util.TurtlePIDConstants;

/**
 * Constants for Robot
 * All inner classes are defined as "static final" so static variables can be defined
 *
 * @author asinghani
 */
public class RobotConstants {

	public static boolean PRACTICE_ROBOT = true;
	
	/**
	 * Shooter-related constants
	 */
	public static final class Shooter {
		public static final int MOTOR_PORT = -1;
	}

	/**
     * USB ports
     */
    public static final class UsbPorts {
	    public static final int XBOX_CONTROLLER = 3;
	    public static final int LEFT_STICK = 1;
	    public static final int RIGHT_STICK = 0;
    }

	/**
	 * RoboRIO sensor ports
	 */
	public static final class Sensors {
		public static final int NAVX_PORT = 1; // TODO: Find correct port
		public static final int PRACTICE_ROBOT_DIO = 0;
	}

	/**
	 * Straight Drive PID
	 */
	public static final class StraightDrivePID {
		public static final TurtlePIDConstants PID_CONSTANTS = new TurtlePIDConstants(.0015, 0, .0001, .0001);
		public static final double kLR = 0.00005;
		public static final double TOLERANCE = 2;
	}

	/**
	 * Turning PID
	 */
	public static final class TurnPID {
		public static final TurtlePIDConstants PID_CONSTANTS = new TurtlePIDConstants(.0015, 0, .0001, .0001);
		public static final double TOLERANCE = 3;

		public static final double TURN_SPEED = 0.5;
	}

	/**
	 * Gear Alignment PID
	 */
	public static final class GearPID {
		public static final TurtlePIDConstants PID_CONSTANTS = new TurtlePIDConstants(.0015, 0, 0, 0);

		public static final double SPEED = 0.5;

		public static final int CAMERA_WIDTH = 1280;
	}

	/**
	 * Misc
	 */
	public static final int LOGGER_MODE = TurtleLogger.PLAINTEXT;
	public static final double JOYSTICK_DEADBAND = 0.05;
	public static final boolean USE_XBOX_CONTROLLER = false;

	public static final double MOTOR_DEADBAND = 0.05; // Only for logging to SmartDashboard
	public static final double COLLISION_THRESHOLD = 1.5f;

	/**
	 * Constructor so RobotConstants can't be initialised
	 *
	 * @throws IllegalStateException when called
 	 */
	private RobotConstants() {
		throw new IllegalStateException("RobotConstants cannot be initialized. Something very bad has happened.");
	}
}