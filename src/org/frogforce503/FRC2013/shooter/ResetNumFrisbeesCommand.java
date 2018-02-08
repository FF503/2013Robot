package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class ResetNumFrisbeesCommand extends Command {
    
    private final int num;
    public ResetNumFrisbeesCommand(int num) {
        this.num = num;
    }

    protected void initialize() {
        Shooter.resetNumFrisbees(num);
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
