package org.frogforce503.FRC2013.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frogforce503.FRC2013.shooter.CheckFrisbeesAndFireCommand;
import org.frogforce503.FRC2013.shooter.GotoPositionCommandGroup;
import org.frogforce503.FRC2013.shooter.ResetNumFrisbeesCommand;
import org.frogforce503.FRC2013.shooter.Shooter.ShooterPosition;


/**
 *
 * @author Bryce Paputa
 */
public class BackLegAutonGroup extends CommandGroup {
    

    public BackLegAutonGroup() {
        ShooterPosition pos = ShooterPosition.BACK_LEG;
        addSequential(new AutonStartGroup());
        addSequential(new GotoPositionCommandGroup(ShooterPosition.BACK_LEG));
        addSequential(new ResetNumFrisbeesCommand(5));
        addSequential(new CheckFrisbeesAndFireCommand());
    }
}
