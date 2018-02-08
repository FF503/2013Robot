package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class DecrementNumFrisbeesCommand extends Command {
    

    public DecrementNumFrisbeesCommand() {
    }

    protected void initialize() {
        Shooter.decrementNumFrisbees();
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
