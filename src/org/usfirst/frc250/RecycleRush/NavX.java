package org.usfirst.frc250.RecycleRush;

import com.kauailabs.nav6.frc.*;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class NavX {
	SerialPort serial;
	IMUAdvanced imu;
	
	public NavX(){
		try {
			serial = new SerialPort(57600, SerialPort.Port.kMXP);
			imu = new IMUAdvanced(serial);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (imu != null) {
			LiveWindow.addSensor("IMU", "gyro", imu);
		}
	}
	
	public float getYaw() {
		return imu.getYaw();
	}
	
	public void test () {
		System.out.println(
				"Pitch: " + Math.round(imu.getPitch()) +
				" Roll: " + Math.round(imu.getRoll()) +
				" Yaw: " + Math.round(imu.getYaw())
				);
		
	}
	
	public void zeroYaw() {
		imu.zeroYaw();
	}
}
