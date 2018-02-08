package org.frogforce503.FRC2013.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frogforce503.FRC2013.angler.AngleUpCommand;
import org.frogforce503.FRC2013.angler.AutoCalibrateAnglerCommandGroup;
import org.frogforce503.FRC2013.shooter.GotoSpeedCommand;
import org.frogforce503.FRC2013.shooter.SetShooterStateCommand;


/**
 *
 * @author Bryce Paputa
 */
public class AutonStartGroup extends CommandGroup {
    

    public AutonStartGroup() {
        addSequential(new GotoSpeedCommand(2200));
        addSequential(new SetShooterStateCommand(true));
        addSequential(new AutoCalibrateAnglerCommandGroup());
        addSequential(new AngleUpCommand(.66, 1));
    }
}
