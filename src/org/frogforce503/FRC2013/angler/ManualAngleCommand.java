package org.frogforce503.FRC2013.angler;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frogforce503.FRC2013.OI;



/**
 *
 * @author Bryce Paputa
 */
public class ManualAngleCommand extends Command{
    

    public ManualAngleCommand() {
        requires(Angler.getInstance());
    }

    protected void initialize() {
    }

    protected void execute() {
        double input = OI.getAngleInput();
        if(Angler.controller.getAngle() <= 800 || input <= 0) {
            Angler.controller.set(input*input * (input>0?.4:-.33));
        } else {
            Angler.controller.set(0);
        }
        SmartDashboard.putNumber("angle", Angler.controller.getAngle());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
