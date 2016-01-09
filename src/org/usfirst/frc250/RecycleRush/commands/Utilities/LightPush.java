package org.usfirst.frc250.RecycleRush.commands.Utilities;

import org.usfirst.frc250.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LightPush extends Command {

    public LightPush() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lights);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lights.send();
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
