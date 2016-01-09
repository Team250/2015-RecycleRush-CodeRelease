package org.usfirst.frc250.RecycleRush.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoNothingAuto extends CommandGroup {
    
    public  DoNothingAuto() {
    	addSequential(new AutonomousInitialize());
    }
}
