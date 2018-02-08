package org.frogforce503.FRC2013.pneumatics;

import edu.wpi.first.wpilibj.command.Command;
import org.frogforce503.FRC2013.pneumatics.Shifter.Position;


/**
 *
 * @author Bryce Paputa
 */
public class ManualShiftCommand extends Command {
    private final Position position;

    public ManualShiftCommand(Position pos) {
        position = pos;
        requires(Shifter.getInstance());
        setTimeout(.5d);
        setInterruptible(false);
    }

    protected void initialize() {
        Shifter.manualShift(position);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
