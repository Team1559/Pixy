package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Pixy {
	SerialPort pixy;
	Port port = Port.kMXP;
	byte[] rawData;
	int[] parsedData;
	String cheese;
	int syncWord;

	public Pixy() {
		pixy = new SerialPort(19200, port);
		pixy.setReadBufferSize(14);
		rawData = new byte[14];
		parsedData = new int[14];
	}
	public void read() {
		pixy.reset();
		rawData = pixy.read(14);
		for(int i = 0; i < rawData.length; i++){
			parsedData[i] = ((int) rawData[i] & 0xff);
		}
		syncWord = ((parsedData[1] << 8) | parsedData[0]);
		System.out.println(syncWord);
		if (syncWord == 0xaa55){
			System.out.println("We Did It Mom!!");
		}
	}

} 