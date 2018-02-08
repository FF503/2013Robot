package org.frogforce503.FRC2013.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frogforce503.FRC2013.OI;
import org.frogforce503.FRC2013.pneumatics.Shifter;
import org.frogforce503.FRC2013.pneumatics.Shifter.Position;


/**
 * Command that gets the joystick inputs and tank drives with them.
 * @see Drivetrain
 * @author Bryce Paputa
 */
public class TeleopDriveCommand extends Command {
    
    /**
     * Constructor that creates a new command.
     */
    public TeleopDriveCommand() {
        requires(Drivetrain.getInstance());
    }
    
    /**
     * Called once when the command is scheduled to run.
     * Just calls the super method.
     */
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     * Drives the drivetrain with the inputs from the operator interface.
     * @see Drivetrain
     * @see OI
     */
    boolean lastOpOverRide = false;
    Position lastShiftState = Shifter.Position.UNDEF;
            
            
    protected void execute() {
        if(!OI.getOperatorOverRide()){
            Drivetrain.tank(OI.getLeftDriveInput(), OI.getRightDriveInput());
            SmartDashboard.putNumber("leftinput", OI.getLeftDriveInput());
            SmartDashboard.putNumber("rightinput", OI.getRightDriveInput());        
            if(lastOpOverRide == true){
                Shifter.shift(lastShiftState);
            }
            lastOpOverRide = false;
        } else {
            if(lastOpOverRide == false){
                lastShiftState = Shifter.getPosition();
                Shifter.shift(Position.DOWN);
            }
            Drivetrain.arcade(0, OI.getOperatorRotInput()*.667);
            lastOpOverRide = true;
        }
    }

    /**
     * Returns true when the command is done, which is never as it is the default command of the drivetrain.
     * @return whether or not the command is done, which is false
     * @see Drivetrain
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called when the command finishes.
     * Just calls the super method.
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run. Calls {@link #end()}.
     */
    protected void interrupted() {
        end();
    }
}
