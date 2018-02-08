package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frogforce503.FRC2013.pneumatics.Compressor;



/**
 *
 * @author Bryce Paputa
 */
public class RetractFiringMechanismCommand extends Command {
    

    public RetractFiringMechanismCommand() {
        setTimeout(SmartDashboard.getNumber("shoot delay")/2);
        requires(Shooter.getInstance());
    }

    protected void initialize() {
        Compressor.end();
        Shooter.retractFiringMechanism();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        Compressor.start();
    }

    protected void interrupted() {
    }
}
