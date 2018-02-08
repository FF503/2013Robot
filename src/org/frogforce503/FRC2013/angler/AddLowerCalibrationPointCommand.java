package org.frogforce503.FRC2013.angler;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class AddLowerCalibrationPointCommand extends Command {
    

     public AddLowerCalibrationPointCommand() {
        requires(Angler.getInstance());
    }

    protected void initialize() {
        Angler.controller.setLowerCalibrationPoint();
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
