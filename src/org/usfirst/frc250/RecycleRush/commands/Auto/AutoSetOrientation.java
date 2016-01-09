package org.usfirst.frc250.RecycleRush.commands.Auto;

import org.usfirst.frc250.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSetOrientation extends Command {
	double orient;
    public AutoSetOrientation(double orient) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.orient = orient;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setOrientation(orient);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.isAtOrientation();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
