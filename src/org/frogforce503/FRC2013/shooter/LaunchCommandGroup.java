package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 * @author Bryce Paputa
 */
public class LaunchCommandGroup extends CommandGroup {
    

    public LaunchCommandGroup() {
        setInterruptible(false);
        addSequential(new WaitForShooterSpeedCommand());
        addParallel(new OverRideSpeedControlCommand());
        addSequential(new RetractFiringMechanismCommand());
        addSequential(new ExtendFiringMechanismCommand());
    }
}
