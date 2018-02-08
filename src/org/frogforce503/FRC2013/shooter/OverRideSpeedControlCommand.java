package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class OverRideSpeedControlCommand extends Command {
    

    public OverRideSpeedControlCommand() {
        setTimeout(.1);
        setInterruptible(false);
    }
    double target;
    protected void initialize() {
        Shooter.setSpeed(1);
        target = Shooter.getTargetSpeed();
        Shooter.setTargetSpeed(503503503);
    }

    protected void execute() {
        
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        Shooter.setTargetSpeed(target);
    }

    protected void interrupted() {
        end();
    }
}
