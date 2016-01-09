package org.usfirst.frc250.RecycleRush.commands.Elevator;

import org.usfirst.frc250.RecycleRush.Robot;
import org.usfirst.frc250.RecycleRush.RobotMap;
import org.usfirst.frc250.RecycleRush.subsystems.Elevator.RatchetDirectionEnum;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorDrive extends Command {

	public ElevatorDrive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.manipulator.getMagnitude() > 0.6) {
			if (Robot.oi.manipulator.getY() < 0) {
				if (Robot.elevator.getRatchetDirection() == RatchetDirectionEnum.UP) {
					if (Robot.gripper.isGripperClosed()) {
						Robot.elevator
								.setSpeed(RobotMap.kELEVATOR_MANUAL_UP_LOADED_SPEED);
					} else {
						Robot.elevator
								.setSpeed(RobotMap.kELEVATOR_MANUAL_UP_UNLOADED_SPEED);
					}
				} else {
					Robot.elevator.setRatchetDirection(RatchetDirectionEnum.UP);
				}
			} else {
				if (Robot.elevator.getRatchetDirection() == RatchetDirectionEnum.DOWN) {
					if (Robot.gripper.isGripperClosed()) {
						Robot.elevator.slowCrash();
					} else {
						Robot.elevator
								.setSpeed(RobotMap.kELEVATOR_FREEFALL_DOWN_SPEED);
					}
				} else {
					Robot.elevator
							.setRatchetDirection(RatchetDirectionEnum.DOWN);
				}
			}
		} else {
			Robot.elevator.setSpeed(0);
			if (Robot.elevator.getRatchetDirection() != RatchetDirectionEnum.UP) {
				Robot.elevator.setRatchetDirection(RatchetDirectionEnum.UP);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
