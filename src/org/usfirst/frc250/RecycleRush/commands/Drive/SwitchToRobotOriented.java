package org.usfirst.frc250.RecycleRush.commands.DriveFolder;

import org.usfirst.frc250.RecycleRush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchToRobotOriented extends Command {

    public SwitchToRobotOriented() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setFieldOriented(false);
    	Robot.camera.switchToRobotCamera();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setFieldOriented(true);
    	Robot.drivetrain.setOrientation(Robot.purpleSensor.getHeading());
    	Robot.camera.switchToDSCamera();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
