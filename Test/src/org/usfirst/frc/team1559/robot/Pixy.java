package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Pixy {
	SerialPort pixy;
	Port port = Port.kMXP;
	
	public Pixy(){
		pixy = new SerialPort(19200, port);	
	}
	
	public void read(){
		pixy.read(13);
	}
	
	

}
