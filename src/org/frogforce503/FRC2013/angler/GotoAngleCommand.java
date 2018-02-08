package org.frogforce503.FRC2013.angler;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class GotoAngleCommand extends Command {
    private final double angle;

    public GotoAngleCommand(double angle) {
        requires(Angler.getInstance());
        this.angle = angle;
    }

    protected void initialize() {
        Angler.controller.setPIDSetpoint(angle);
        Angler.controller.enablePID();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Angler.controller.isPIDOnTarget();
    }

    protected void end() {
        Angler.controller.disablePID();
    }

    protected void interrupted() {
        Angler.controller.disablePID();
    }
}
