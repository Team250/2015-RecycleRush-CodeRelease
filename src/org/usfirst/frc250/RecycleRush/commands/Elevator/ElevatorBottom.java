package org.usfirst.frc250.RecycleRush.commands.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorBottom extends CommandGroup {
    
    public  ElevatorBottom () {
        addSequential(new _ElevatorSetRatchetDown());
        addSequential(new _ElevatorSetSetpoint(0));
        addSequential(new _ElevatorSetRatchetUp());
    }
}
