package org.usfirst.frc250.RecycleRush.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto1 extends CommandGroup {
    
    public  Auto1() {
    	addSequential(new AutonomousInitialize());
    	addSequential(new AutoDriveForward(3.5));
    	addSequential(new StopDriving());
    }
}
