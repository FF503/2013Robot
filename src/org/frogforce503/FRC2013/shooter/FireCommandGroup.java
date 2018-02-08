package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 * @author Bryce Paputa
 */
public class FireCommandGroup extends CommandGroup {
    

    public FireCommandGroup() {
        addSequential(new LaunchCommandGroup());
        addSequential(new DecrementNumFrisbeesCommand());
        addSequential(new CheckFrisbeesAndFireCommand());
    }
}
