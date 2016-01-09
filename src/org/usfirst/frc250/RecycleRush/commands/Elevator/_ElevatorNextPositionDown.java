package org.usfirst.frc250.RecycleRush.commands.Elevator;

import org.usfirst.frc250.RecycleRush.Robot;
import org.usfirst.frc250.RecycleRush.subsystems.Elevator.RatchetDirectionEnum;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class _ElevatorNextPositionDown extends Command {

    public _ElevatorNextPositionDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.setPIDControl(true);
		Robot.elevator.setElevatorTargetPosition(Robot.elevator.findNextPosition(false));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return Robot.elevator.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.setPIDControl(false);
    	Robot.elevator.setRatchetDirection(RatchetDirectionEnum.UP);
		Robot.elevator.setSpeed(0);
	}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
