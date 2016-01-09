package org.usfirst.frc250.RecycleRush.commands.Elevator;

import org.usfirst.frc250.RecycleRush.RobotMap;
import org.usfirst.frc250.RecycleRush.commands.Utilities.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorYogiStackUp extends CommandGroup {
    
    public  ElevatorYogiStackUp() {
        addSequential(new _ElevatorSetRatchetUp());
        addSequential(new _ElevatorSetSetpoint(RobotMap.kELEVATOR_YOGI_LIFT_POSITION));
        addSequential(new RumbleJoystick());
        addSequential(new EmptyCommand());
    }
}
