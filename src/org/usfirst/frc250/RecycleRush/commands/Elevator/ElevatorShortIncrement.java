package org.usfirst.frc250.RecycleRush.commands.Elevator;

import org.usfirst.frc250.RecycleRush.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorShortIncrement extends CommandGroup {
    
    public  ElevatorShortIncrement() {
    	addSequential(new _ElevatorSetRatchetUp());
    	addSequential(new _ElevatorSetRelativeSetpoint(RobotMap.kELEVATOR_SHORT_INCREMENT_DISTANCE));
    }
}
