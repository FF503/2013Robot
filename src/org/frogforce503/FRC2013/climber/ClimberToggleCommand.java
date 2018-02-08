package org.frogforce503.FRC2013.climber;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class ClimberToggleCommand extends Command {
    

    public ClimberToggleCommand() {
        requires(Climber.getInstance());
    }

    protected void initialize() {
        Climber.toggle();
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
