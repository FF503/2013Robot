package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frogforce503.FRC2013.angler.GotoAngleCommand;
import org.frogforce503.FRC2013.shooter.Shooter.ShooterPosition;


/**
 *
 * @author Bryce Paputa
 */
public class GotoPositionCommandGroup extends CommandGroup {
    

    public GotoPositionCommandGroup(ShooterPosition pos) {
        addParallel(new GotoSpeedCommand(pos.getSpeed()));
        addParallel(new GotoThresholdCommand(pos.getThreshold()));
        addParallel(new GotoAngleCommand(pos.getAngle()));
        addParallel(new SetShooterStateCommand(pos.getSpeed()>0?true:false));
    }
}
