package org.frogforce503.FRC2013.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frogforce503.FRC2013.shooter.CheckFrisbeesAndFireCommand;
import org.frogforce503.FRC2013.shooter.GotoPositionCommandGroup;
import org.frogforce503.FRC2013.shooter.ResetNumFrisbeesCommand;
import org.frogforce503.FRC2013.shooter.Shooter.SmartPosition;


/**
 *
 * @author Bryce Paputa
 */
public class SmartAutonGroup extends CommandGroup {
    

    public SmartAutonGroup() {
        addSequential(new AutonStartGroup());
        addSequential(new GotoPositionCommandGroup(SmartPosition.OBJ));
        addSequential(new ResetNumFrisbeesCommand(5));
        addSequential(new CheckFrisbeesAndFireCommand());
    }
}
