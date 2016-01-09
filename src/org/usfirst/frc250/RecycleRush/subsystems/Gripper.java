// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc250.RecycleRush.subsystems;

import org.usfirst.frc250.RecycleRush.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Gripper extends Subsystem {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DoubleSolenoid gripper = RobotMap.gripperGripper;
    AnalogInput transducer = RobotMap.gripperTransducer;
    Compressor compressor = RobotMap.gripperCompressor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Gripper(){
    	openGripper();
    }
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
		
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void closedLoopControl(){
		compressor.setClosedLoopControl(true);
	}
	public void openGripper() {
		gripper.set(RobotMap.kGRIPPER_OPEN);
	}

	public void closeGripper() {
		gripper.set(RobotMap.kGRIPPER_CLOSE);
	}
	
	public boolean isGripperOpen() {
		return gripper.get() == RobotMap.kGRIPPER_OPEN;
	}
	
	public boolean isGripperClosed() {
		return !isGripperOpen();
	}
	
	public double getCompressorVoltage () {
		return transducer.getVoltage();
	}
	
	public void smartDashboardPush () {
		SmartDashboard.putNumber("PSI", transducer.getVoltage()*4);
		SmartDashboard.putBoolean("PSI_Good", transducer.getVoltage() > 4);
		SmartDashboard.putBoolean("PSI_Too_Low", transducer.getVoltage() < 2);
		SmartDashboard.putBoolean("Gripper_Position", isGripperClosed());
	}
}