package org.frogforce503.FRC2013.angler;

import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 *
 * @author Bryce Paputa
 */
public class AutoCalibrateAnglerCommandGroup extends CommandGroup {

    public AutoCalibrateAnglerCommandGroup() {
        addSequential(new AngleDownCommand(.9));
        addSequential(new AngleUpCommand(.5, .25));
        addSequential(new AngleDownCommand(.2));
        addSequential(new AddLowerCalibrationPointCommand());
    }
}
