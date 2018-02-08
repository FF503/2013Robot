package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 * @author Bryce Paputa
 */
public class ExtendFiringMechanismCommand extends Command {
    

    public ExtendFiringMechanismCommand() {
        setTimeout(SmartDashboard.getNumber("shoot delay")/2);
        requires(Shooter.getInstance());
    }

    protected void initialize() {
        Shooter.extendFiringMechanism();
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
