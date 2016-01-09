package org.usfirst.frc250.RecycleRush.commands.Elevator;

import org.usfirst.frc250.RecycleRush.commands.Utilities.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorYogiStackDown extends CommandGroup {
    
    public  ElevatorYogiStackDown() {
        addSequential(new _ElevatorSetRatchetDown());
        addSequential(new _ElevatorTurboDecrement());
        addSequential(new RumbleJoystick());
        addSequential(new EmptyCommand());
    }
}
