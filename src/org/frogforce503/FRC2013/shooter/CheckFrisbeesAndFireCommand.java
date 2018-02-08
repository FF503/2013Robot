package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class CheckFrisbeesAndFireCommand extends Command {
    
    protected void initialize() {
        if(Shooter.getNumFrisbees()>0){
            (new FireCommandGroup()).start();
        } else {
            (new SetShooterStateCommand(false)).start();
        }
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
