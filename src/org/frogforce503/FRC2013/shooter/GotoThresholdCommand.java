package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class GotoThresholdCommand extends Command {
    private final double threshold;

    public GotoThresholdCommand(double threshold) {
        this.threshold = threshold;
    }

    protected void initialize() {
        Shooter.setThreshold(threshold);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
