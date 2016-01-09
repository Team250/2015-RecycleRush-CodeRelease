package org.usfirst.frc250.RecycleRush.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto2 extends CommandGroup {
    
    public  Auto2() {
    	addSequential(new AutonomousInitialize());
    	addSequential(new AutoDriveBackward(2));
    	addSequential(new StopDriving());
    }
}
