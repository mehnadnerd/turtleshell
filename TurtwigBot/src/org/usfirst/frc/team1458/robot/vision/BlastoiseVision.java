package org.usfirst.frc.team1458.robot.vision;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1458.robot.constants.RobotConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Vision detection code for 2017
 *
 * @author asinghani
 */
public class BlastoiseVision {
	private VideoSource videoSource;
	private static int uniqueId = 0;

	public static final Object lock = new Object();

	private List<Rect> contours = new ArrayList<>();

	/**
	 * Instantiates BlastoiseVision with a VideoSource object. This method is not recommended.
	 * @param videoSource
	 */
	public BlastoiseVision(VideoSource videoSource) {
		this.videoSource = videoSource;
		videoSource.setResolution(RobotConstants.Vision.CAMERA_WIDTH, RobotConstants.Vision.CAMERA_HEIGHT);
		
		VisionThread visionThread = new VisionThread(videoSource, new DetectTargetPipeline(), pipeline -> {
			synchronized (lock) {
				processContours(pipeline.filterContours0Output());
			}
		});
		visionThread.start();
	}

	/**
	 * Instantiates BlastoiseVision with a MJPG stream url. This is the recommended method.
	 * @param streamUrl
	 */
	public BlastoiseVision(String streamUrl) {
		this(new HttpCamera(streamUrl+uniqueId, streamUrl, HttpCamera.HttpCameraKind.kMJPGStreamer));
		uniqueId++;
		try {
			CameraSetup.initialSetup("localhost", 5800);
			CameraSetup.startVision("localhost", 5800);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiates BlastoiseVision with a USB camera. USE AT YOUR OWN RISK, THIS HAS A HIGH CHANCE OF CAUSING MEMORY LEAKS!
	 * Also, drivers will not be able to see camera feed on driver station if this is used.
	 * @param cameraNumber
	 */
	public BlastoiseVision(int cameraNumber) {
		this(new UsbCamera(cameraNumber+""+uniqueId, cameraNumber));
	}

	protected void processContours(List<MatOfPoint> data) {
		contours.clear();
		if (!data.isEmpty()) {
			for(MatOfPoint matOfPoint : data) {
				contours.add(Imgproc.boundingRect(matOfPoint));
			}
		}
	}

	public ArrayList<Rect> getContours() {
		synchronized (lock) {
			return new ArrayList<>(contours);
		}
	}

	public double getShooterTargetDistance() {
		ArrayList<Rect> contours = getContours();
		if(contours.size() < 2){
			return -1;
		}
		Collections.sort(contours, (Rect r1, Rect r2) -> ((int) (r1.area() - r2.area())) );
		Collections.reverse(contours);

		assert contours.size() >= 2;

		double xCoord = ((double) (contours.get(0).x + contours.get(1).x)) / 2.0;
		double yCoord = ((double) (contours.get(0).y + contours.get(1).y)) / 2.0;

		SmartDashboard.putNumber("X of target", xCoord);
		SmartDashboard.putNumber("Y of target", yCoord);

		double cameraHeightDifference = (78 - RobotConstants.Shooter.Camera.MOUNT_HEIGHT);
		double pixelDifference = (RobotConstants.Shooter.Camera.HEIGHT_PX - yCoord) / RobotConstants.Shooter.Camera.HEIGHT_PX;
		double angle = RobotConstants.Shooter.Camera.MOUNT_ANGLE +
				(RobotConstants.Shooter.Camera.HEIGHT_FOV * pixelDifference);
		angle = Math.toRadians(angle);
		double distance = cameraHeightDifference * (1 / Math.tan(angle));

		SmartDashboard.putNumber("DistanceToBoiler", distance);

		return distance;
	}

	public double getShooterTargetX() {
		ArrayList<Rect> contours = getContours();
		if(contours.size() < 2){
			return -1;
		}
		Collections.sort(contours, (Rect r1, Rect r2) -> ((int) (r1.area() - r2.area())) );
		Collections.reverse(contours);

		assert contours.size() >= 2;

		double xCoord = ((double) (contours.get(0).x + contours.get(1).x)) / 2.0;
		double yCoord = ((double) (contours.get(0).y + contours.get(1).y)) / 2.0;

		SmartDashboard.putNumber("X of target", xCoord);
		SmartDashboard.putNumber("Y of target", yCoord);

		return xCoord;
	}
	
	public void close() {
		videoSource.free();
	}
}
