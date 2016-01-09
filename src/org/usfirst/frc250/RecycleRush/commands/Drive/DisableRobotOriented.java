package org.usfirst.frc250.RecycleRush.commands.DriveFolder;

import org.usfirst.frc250.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DisableRobotOriented extends Command {

    public DisableRobotOriented() {
    	/* TODO: Combine this and EnableRobotOriented to a single "while held" command */
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setFieldOriented(true);
    	Robot.drivetrain.setOrientation(Robot.purpleSensor.getHeading());
    	Robot.camera.switchToDSCamera();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
