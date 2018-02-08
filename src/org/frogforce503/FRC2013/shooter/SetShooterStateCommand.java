package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class SetShooterStateCommand extends Command {
    
    private final boolean state;
    public SetShooterStateCommand(boolean state) {
        this.state = state;
    }

    protected void initialize() {
        Shooter.setOn(state);
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
