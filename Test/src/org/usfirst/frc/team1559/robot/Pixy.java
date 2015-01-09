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
	public void readPacket() {
		byte[] rawData = new byte[12];
		int[] dataToParse = new int[12];
		byte[] waitForSync = new byte[2];
		int[] syncWait = new int[2];
		pixy.reset();
		waitForSync = pixy.read(2);
		for(int i = 0; i < waitForSync.length; i++){
			syncWait[i] = ((int) waitForSync[i] & 0xff);
		}
		int syncWord = ((syncWait[1] << 8) | syncWait[0]);
//		System.out.println(syncWord);
		if (syncWord == 0xaa55){
			System.out.println("We Did It Mom!!");
			for(int noti = 0; noti < rawData.length; noti++){
				dataToParse[noti] = ((int) rawData[noti] & 0xff);
			}
			Checksum = ((dataToParse[1] << 8) | dataToParse[0]);
			Signature = ((dataToParse[3] << 8) | dataToParse[2]);
			X = ((dataToParse[5] << 8) | dataToParse[4]);
			Y = ((dataToParse[7] << 8) | dataToParse[6]);
			Width = ((dataToParse[9] << 8) | dataToParse[8]);
			Height = ((dataToParse[11] << 8) | dataToParse[10]);
			if (Checksum == Signature + X + Y + Width + Height){
				System.out.println("Packet is Valid Yay");
			}
		}
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