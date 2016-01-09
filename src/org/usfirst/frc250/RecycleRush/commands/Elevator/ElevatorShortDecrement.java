package org.usfirst.frc250.RecycleRush.commands.Elevator;

import org.usfirst.frc250.RecycleRush.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorShortDecrement extends CommandGroup {
    
    public  ElevatorShortDecrement() {
    	addSequential(new _ElevatorSetRatchetDown());
    	addSequential(new _ElevatorSetRelativeSetpoint(-RobotMap.kELEVATOR_SHORT_INCREMENT_DISTANCE));
    }
}
