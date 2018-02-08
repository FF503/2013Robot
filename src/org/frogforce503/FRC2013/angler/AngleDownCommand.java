package org.frogforce503.FRC2013.angler;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class AngleDownCommand extends Command {
    private boolean done = false;
    private final double speed; 
    
    public AngleDownCommand(double speed) {
        requires(Angler.getInstance());
        this.speed = speed;
    }

    public AngleDownCommand(double speed, double time){
        requires(Angler.getInstance());
        setTimeout(time);
        this.speed = speed;
    }
    
    protected void initialize() {
        Angler.controller.disablePID();
    }

    protected void execute() {
        done = Angler.controller.goDown(speed);
    }

    protected boolean isFinished() {
        return done || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
