package org.usfirst.frc250.RecycleRush.commands.Auto;

import org.usfirst.frc250.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveLeft extends Command {
	double time;
    public AutoDriveLeft(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.fieldOrientedDrive(0, 1, Robot.purpleSensor.getHeading());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
