package org.usfirst.frc250.RecycleRush.commands.Elevator;

import org.usfirst.frc250.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class _ElevatorNextPositionUp extends Command {

	public _ElevatorNextPositionUp() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.elevator.setPIDControl(true);
		Robot.elevator.setElevatorTargetPosition(Robot.elevator.findNextPosition(true));
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
		Robot.elevator.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
