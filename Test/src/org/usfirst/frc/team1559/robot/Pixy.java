package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Pixy {
	SerialPort pixy;
	Port port = Port.kMXP;
	byte[] rawData;

	public Pixy() {
		pixy = new SerialPort(19200, port);
	}

	public void read() {
		rawData = pixy.read(13);
		String data = new String(rawData);
		System.out.println(rawData);
		// System.out.println(data);
	}

}