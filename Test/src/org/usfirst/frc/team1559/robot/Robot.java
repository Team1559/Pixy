package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	Joystick pad;
	Talon leftMotor;
	Talon rightMotor;
	PixyController p;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println("Robot Init");
		pixy = new Pixy();
		pad = new Joystick(0);
		leftMotor = new Talon(6);
		rightMotor = new Talon(8);
		p = new PixyController(pixy);
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
		pixy.pixyReset();
		count = 0;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		leftMotor.set(-p.autoCenter());
		rightMotor.set(p.autoCenter());
	}

	public void teleopInit() {
		System.out.println("Teleop Init");
		z = true;
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		if (z == true) {
			System.out.println("Teleop Periodic");
			z = false;
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		System.out.println("Test Periodic");

	}

}
