// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc250.RecycleRush.subsystems;

import org.usfirst.frc250.RecycleRush.Robot;
import org.usfirst.frc250.RecycleRush.RobotMap;
import org.usfirst.frc250.RecycleRush.SwerveModule;
import org.usfirst.frc250.RecycleRush.Utilities;
import org.usfirst.frc250.RecycleRush.commands.DriveFolder.Drive;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController fRDMotor = RobotMap.drivetrainFRDMotor;
    SpeedController fRAMotor = RobotMap.drivetrainFRAMotor;
    SpeedController fLDMotor = RobotMap.drivetrainFLDMotor;
    SpeedController fLAMotor = RobotMap.drivetrainFLAMotor;
    SpeedController rRDMotor = RobotMap.drivetrainRRDMotor;
    SpeedController rRAMotor = RobotMap.drivetrainRRAMotor;
    SpeedController rLDMotor = RobotMap.drivetrainRLDMotor;
    SpeedController rLAMotor = RobotMap.drivetrainRLAMotor;
    Encoder fLEncoder = RobotMap.drivetrainFLEncoder;
    Encoder fREncoder = RobotMap.drivetrainFREncoder;
    Encoder rREncoder = RobotMap.drivetrainRREncoder;
    Encoder rLEncoder = RobotMap.drivetrainRLEncoder;
    DigitalInput resetButton = RobotMap.drivetrainResetButton;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	SwerveModule frontLeft, frontRight, rearLeft, rearRight;

	private static final double kFront = 35.5;
	private static final double kSide = 24;
    private static final double kRadiusToWheel = Math.hypot(kFront, kSide);
    private static double driveMultiplier = RobotMap.kDRIVE_FACTOR;
    private double orientation = 0;
    private double waFRprev, waFLprev, waRRprev, waRLprev;
    private boolean isFieldOriented = true;
    private boolean isResetDisabled = false;

	public boolean isFieldOriented() {
		return isFieldOriented;
	}

	public void setFieldOriented(boolean isFieldOriented) {
		this.isFieldOriented = isFieldOriented;
	}

	public Drivetrain() {
		super("Drivetrain");
		frontLeft = new SwerveModule(fLEncoder, fLAMotor, fLDMotor, -90);
		frontRight = new SwerveModule(fREncoder, fRAMotor, fRDMotor, 90);
		rearLeft = new SwerveModule(rLEncoder, rLAMotor, rLDMotor, -90);
		rearRight = new SwerveModule(rREncoder, rRAMotor, rRDMotor, -90);
	}

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	/**
	 * Drive method that allows for movement and rotation at the same time
	 * @param forward is Y component of the direction vector (-1 to 1)
	 * @param strafe is the X component of the direction vector (-1 to 1)
	 * @param rotateCW is the rotation amount with positive in the CW direction (rad/s)
	 */
	public void realMathDrive(double forward, double strafe, double rotateCW){
	 	
    	double A = strafe - rotateCW * (kSide/kRadiusToWheel);
    	double B = strafe + rotateCW * (kSide/kRadiusToWheel);
    	double C = forward - rotateCW * (kFront/kRadiusToWheel);
    	double D = forward + rotateCW * (kFront/kRadiusToWheel);
    	
    	double wsFR = Math.hypot(B, C);
    	double wsFL = Math.hypot(B, D);
    	double wsRL = Math.hypot(A, D);
    	double wsRR = Math.hypot(A, C);
    	
    	double maxWS = wsFR;
    	if(wsFL > maxWS) maxWS = wsFL;
    	if(wsRL > maxWS) maxWS = wsRL;
    	if(wsRR > maxWS) maxWS = wsRR;
    	if(maxWS>1){
    		wsFR/=maxWS;
    		wsFL/=maxWS;
    		wsRL/=maxWS;
    		wsRR/=maxWS;
    	}
    	
    	double waFR, waFL, waRL, waRR;
    	
    	if (maxWS < 0.05) {
    		waFR = waFRprev;
    		waFL = waFLprev;
    		waRR = waRRprev;
    		waRL = waRLprev;
    	} else { 
    		waFR = Math.toDegrees(Math.atan2(B, C));
        	waFL = Math.toDegrees(Math.atan2(B, D));
        	waRL = Math.toDegrees(Math.atan2(A, D));
        	waRR = Math.toDegrees(Math.atan2(A, C));
    		waFRprev = waFR;
    		waFLprev = waFL;
    		waRRprev = waRR;
    		waRLprev = waRL;
    	}
    	
    	frontRight.drive(wsFR*driveMultiplier);
    	frontLeft.drive(wsFL*driveMultiplier);
    	rearLeft.drive(wsRL*driveMultiplier);
    	rearRight.drive(wsRR*driveMultiplier);
    	
    	frontRight.setAngle(-waFR);
    	frontLeft.setAngle(-waFL);
    	rearLeft.setAngle(-waRL);
    	rearRight.setAngle(-waRR);
    }
	
	/**
	 * Calls real math drive with gyro for field oriented driving, and corrects for drift. 
	 * @param forward is the Y component of the direction vector (-1 to 1)
	 * @param strafe is the X component of the direction vector (-1 to 1)
	 * @param gyro is the gyroscope heading in degrees, with cw being positive and 0 being forward
	 */
	public void fieldOrientedDrive(double forward, double strafe, double gyro) {
    	gyro = Math.toRadians(gyro);
    	double newForward = forward * Math.cos(gyro) + strafe * Math.sin(gyro);
    	strafe = -forward * Math.sin(gyro) + strafe * Math.cos(gyro);
    	forward = newForward;
    	double rotation = -Utilities.angleDifference(orientation, Math.toDegrees(gyro))*0.05;
		realMathDrive(forward, strafe, rotation);
    }
	
	public void drive(double speed) {
		frontLeft.drive(speed);
		frontRight.drive(speed);
		rearLeft.drive(speed);
		rearRight.drive(speed);
	}
	
	/**
	 * Stops driving
	 */
	public void stopDriving() {
    	realMathDrive(0,0,0);
    }
	
	/**
	 * Alternates between slow and fast speed as defined in RobotMap
	 */
	public void changeSpeed() {
    	if (driveMultiplier == RobotMap.kDRIVE_FACTOR){
    		driveMultiplier = RobotMap.kDRIVE_SLOW_FACTOR;
    	}
    	else {
    		driveMultiplier = RobotMap.kDRIVE_FACTOR;
    	}
    }
	
	/**
	 * Returns the target orientation value in degrees (0-360)
	 * @return target orientation
	 */
	public double getOrientation () {
    	return orientation;
    }
	
	/**
	 * Sets a target orientation
	 * @param orient is the target orientation in degrees
	 */
	public void setOrientation (double orient) {
    	orientation = Utilities.formatAngle(orient);
    }
	
	public boolean isAtOrientation () {
		return Math.abs(Utilities.angleDifference(orientation, Robot.purpleSensor.getHeading())) < 4; 
	}
	
	/**
	 * Sets wheels to encoder zero, for use before rebooting
	 */
	public void setToZero() {
    	waFLprev = 0;
    	waFRprev = 0;
    	waRLprev = 0;
    	waRRprev = 0;
    	frontLeft.setToZero();
    	frontRight.setToZero();
    	rearLeft.setToZero();
    	rearRight.setToZero();
    }
	
	public void resetEncoders() {
		frontLeft.resetEncoder();
		frontRight.resetEncoder();
		rearLeft.resetEncoder();
		rearRight.resetEncoder();
	}
	
	public boolean getResetButton() {
		return !resetButton.get();
	}
	
	public boolean isResetDisabled() {
		return isResetDisabled;
	}
	
	public void disableResetButton() {
		isResetDisabled = false;
	}
	
	/**
	 * Returns true if all wheel speeds are zero
	 * @return whether all wheels are stopped
	 */
	public boolean isStopped() {
		return frontLeft.getSpeed() == 0 && frontRight.getSpeed() == 0 && rearLeft.getSpeed() == 0 && rearRight.getSpeed() == 0;
	}
	
	public void changePID(int magP, double maxChangeP, int magI, double maxChangeI, int magD, double maxChangeD) {
    	frontLeft.changePID(magP, maxChangeP, magI, maxChangeI, magD, maxChangeD);
    	frontRight.changePID(magP, maxChangeP, magI, maxChangeI, magD, maxChangeD);
    	rearLeft.changePID(magP, maxChangeP, magI, maxChangeI, magD, maxChangeD);
    	rearRight.changePID(magP, maxChangeP, magI, maxChangeI, magD, maxChangeD);
    }
    
    public void setPID(double p, double i, double d) {
    	frontLeft.setPID(p, i, d);
    	frontRight.setPID(p, i, d);
    	rearLeft.setPID(p, i, d);
    	rearRight.setPID(p, i, d);
    }
    
    /**
     * Called in RobotInit to initialize dashboard values
     */
    public void smartDashboardInit() {
//    	SmartDashboard.putNumber("Kp", frontLeft.getKp());
//    	SmartDashboard.putNumber("Ki", frontLeft.getKi());
//    	SmartDashboard.putNumber("Kd", frontLeft.getKd());
    }
    
    /**
     * Called in TelopPeriodic and DisabledPeriodic to update the dashboard values
     */
    public void smartDashboardPush () {
    	SmartDashboard.putNumber("LF_Angle", frontLeft.getAngleWithInversion());
    	SmartDashboard.putNumber(" LF_Speed", frontLeft.getSpeed());
    	SmartDashboard.putNumber("LB_Angle", rearLeft.getAngleWithInversion());
    	SmartDashboard.putNumber("RF_Angle", frontRight.getAngleWithInversion());
    	SmartDashboard.putNumber("RF_Speed", frontRight.getSpeed());
    	SmartDashboard.putNumber("RB_Angle", rearRight.getAngleWithInversion());
    	SmartDashboard.putNumber("RB_Speed", rearRight.getSpeed());
    	SmartDashboard.putNumber("Orient", orientation);
    }
}