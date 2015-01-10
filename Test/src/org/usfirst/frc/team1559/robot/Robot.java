package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	boolean x;
	boolean y;
	boolean z;
	Pixy pixy;
	int count = 0;
	final int halfBand = 3;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println("Robot Init");
		pixy = new Pixy();

	}

	public void disabledInit() {
		System.out.println("Disabled Init");
		x = true;

	}

	public void disabledPeriodic() {
		if (x == true) {
			System.out.println("Disabled Periodic");
		}
		x = false;
	}

	public void autonomousInit() {
		System.out.println("Autonomous Init");
		y = true;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		if (y == true) {
			System.out.println("Autonomous Periodic");
		}
		y = false;

	}

	public void teleopInit() {
		System.out.println("Teleop Init");
		z = true;
		pixy.pixyReset();
		count = 0;
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		if (z == true) {
			System.out.println("Teleop Periodic");
			z = false;
		}
		
		PixyPacket pkt = pixy.readPacket(1);
		if (pkt != null){
			if(count++ < 100){
				return;
			}
			
			count = 0;
			
			System.out.println("The X position of object is " + pkt.X);
			//System.out.println("The Y position of object is " + pixy.getY());
			//System.out.println("The width of object is " + pixy.getWidth());
			//System.out.println("The height of object is " + pixy.getHeight());
			//System.out.println("The signature of object is " + pixy.getSignature());
			int objectX = pkt.X;
			
			if (objectX < 160 - halfBand){
				System.out.println("Right!");
			} else if (objectX > 160 + halfBand){
				System.out.println("Left!");
			} else {
				System.out.println("Centered!");
			}
		} 
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		System.out.println("Test Periodic");

	}

}
