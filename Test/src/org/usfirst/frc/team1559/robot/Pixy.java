package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Pixy {
	SerialPort pixy;
	Port port = Port.kMXP;
	int[] actualData;
	String cheese;
	int Signature;
	int X;
	int Y;
	int Height;
	int Width;
	int Checksum;

	public Pixy() {
		pixy = new SerialPort(19200, port);
		pixy.setReadBufferSize(14);
	}
	
	public int cvt(byte upper, byte lower) {
		return (((int)upper & 0xff) << 8) | ((int)lower & 0xff);
	}
	
	public boolean readPacket() {
		byte[] rawData = new byte[12];
		byte[] waitForSync = new byte[2];
		
		pixy.reset();
		
		waitForSync = pixy.read(2);
		int syncWord = cvt(waitForSync[1], waitForSync[0]);
	    
		if (syncWord == 0xaa55){
			rawData = pixy.read(12);
			 
			Checksum = cvt(rawData[1], rawData[0]);
			Signature = cvt(rawData[3], rawData[2]);
			X = cvt(rawData[5], rawData[4]);
			Y = cvt(rawData[7], rawData[6]);
			Width = cvt(rawData[9], rawData[8]);
			Height = cvt(rawData[11], rawData[10]);
			
			if (Checksum == Signature + X + Y + Width + Height){
				System.out.println("Packet is Valid Yay");
				return true;
			}
			else{ 
				System.out.println("Checksum didn't work :(");
				return false;
			}
		}
	
		else return false;
	}
	public int getSignature() {
		return Signature;
	}
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	public int getHeight() {
		return Height;
	}
	public int getWidth() {
		return Width;
	}

} 