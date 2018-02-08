package org.frogforce503.FRC2013.angler;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class AngleUpCommand extends Command {
    private boolean done = false;
    private final double speed;
    public AngleUpCommand(double speed) {
        requires(Angler.getInstance());
        this.speed = speed;
    }

    public AngleUpCommand(double speed, double time){
        requires(Angler.getInstance());
        setTimeout(time);
        this.speed = speed;
    }
    
    protected void initialize() {
        Angler.controller.disablePID();
    }

    protected void execute(){
        done = Angler.controller.goUp(speed);
    }

    protected boolean isFinished() {
        return done || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
