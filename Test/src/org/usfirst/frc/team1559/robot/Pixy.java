package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Pixy {
	SerialPort pixy;
	Port port = Port.kMXP;
	PixyPacket[] packets;

	public Pixy() {
		pixy = new SerialPort(19200, port);
		pixy.setReadBufferSize(14);
		packets = new PixyPacket[7];
	}
	
	public int cvt(byte upper, byte lower) {
		return (((int)upper & 0xff) << 8) | ((int)lower & 0xff);
	}
	public void pixyReset(){
		pixy.reset();
	}
	
	public void ClearPacket(int Signature) {
		packets[Signature] = null;
	}
	
	public PixyPacket readPacket(int Signature) {
		int Checksum;
		int Sig;
		byte[] rawData = new byte[32];
		rawData = pixy.read(32);
		int i = 0;
		
		while (i <= 16) {
			int syncWord = cvt(rawData[i+1], rawData[i+0]);
		    
			if (syncWord == 0xaa55) {
				syncWord = cvt(rawData[i+3], rawData[i+2]);
				if (syncWord != 0xaa55)
					i -= 2;
					Checksum = cvt(rawData[i+5], rawData[i+4]);					
					Sig = cvt(rawData[i+7], rawData[i+6]);
					
					packets[Sig - 1] = new PixyPacket();
					packets[Sig - 1].X = cvt(rawData[i+9], rawData[i+8]);
					packets[Sig - 1].Y = cvt(rawData[i+11], rawData[i+10]);
					packets[Sig - 1].Width = cvt(rawData[i+13], rawData[i+12]);
					packets[Sig - 1].Height = cvt(rawData[i+15], rawData[i+14]);
					
					if (Checksum != Sig + packets[Sig - 1].X + packets[Sig - 1].Y + packets[Sig - 1].Width + packets[Sig - 1].Height) {
						System.out.println("Checksum error");
						packets[Sig - 1] = null;
					}
					break;	
			}
			i++;
		}
		PixyPacket pkt = packets[Signature];
		packets[Signature] = null;
		return pkt;
	}
} 