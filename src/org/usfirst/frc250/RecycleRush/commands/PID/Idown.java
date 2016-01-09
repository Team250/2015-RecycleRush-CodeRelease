package org.usfirst.frc250.RecycleRush.commands.PID;

import org.usfirst.frc250.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Idown extends Command {

    public Idown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//		Robot.drivetrain.changeI(-1, .0001);
		Robot.drivetrain.changePID(1,  0, -1, 0.0001, 1, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
