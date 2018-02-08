package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class GotoSpeedCommand extends Command{
    private final double speed;

    public GotoSpeedCommand(double speed) {
        this.speed = speed;
    }

    protected void initialize() {
        Shooter.setTargetSpeed(speed);
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
