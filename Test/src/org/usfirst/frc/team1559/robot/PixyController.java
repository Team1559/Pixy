package org.usfirst.frc.team1559.robot;


public class PixyController {
	Pixy pixy;
	PixyPacket pkt;
	double error;
	public final double ratio = 109/80;
	public PixyController(Pixy p){
		pixy = new Pixy();
		pkt = new PixyPacket();
	}
	public double autoCenter(){
		pkt = null;
		try{
			pkt = pixy.readPacket(1);
		} catch (PixyException e){
			e.printStackTrace();
		}
		if (pkt != null){
			if ((ratio-.1) <= (pkt.Height/pkt.Width) && (ratio+.1) >= (pkt.Height/pkt.Width)){
				if (pkt.X < 150 || pkt.X > 170){
					error = 160-pkt.X;
					error = error/160;
				}
				else{
					error = 0;
				}
			}
			else if((pkt.Height/pkt.Width) > (ratio+0.1)){
				error = 0;
			}
			else{
				error = -error;
			}
		}
		else{
			error = 0;
		}
		return error;
	}
}