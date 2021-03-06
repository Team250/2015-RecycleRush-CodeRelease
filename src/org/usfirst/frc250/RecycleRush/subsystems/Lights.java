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

import org.usfirst.frc250.RecycleRush.LightStrip;
import org.usfirst.frc250.RecycleRush.RobotMap;
import org.usfirst.frc250.RecycleRush.commands.Utilities.LightPush;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lights extends Subsystem {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	
	LightStrip light1, light2;
	
	public Lights() {
		light1 = new LightStrip(196);
		light2 = new LightStrip(196);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new LightPush());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void elevator (int percent) {
		light1.setByte(percent+100);
		light2.setByte(percent+100);
	}
	
	public void send () {
		if (RobotMap.ds.isDisabled()) {
			light1.setByte(1);
			light2.setByte(1);
		} else if (RobotMap.ds.isAutonomous()) {
			light1.setByte(2);
			light2.setByte(2);
		} else if (RobotMap.ds.isOperatorControl()) {
			if (RobotMap.ds.getAlliance() == DriverStation.Alliance.Red) {
				light1.setByte(3);
				light2.setByte(3);
			} else {
				light1.setByte(4);
				light2.setByte(4);
			}
		} else {
			light1.setByte(0);
			light2.setByte(0);
		}
		light1.sendByte();
		light2.sendByte();
	}
}
