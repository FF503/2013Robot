package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class WaitForShooterSpeedCommand extends Command {
    

    public WaitForShooterSpeedCommand() {
        requires(Shooter.getInstance());
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Shooter.onTarget();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
