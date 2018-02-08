package org.frogforce503.FRC2013.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Command that turns the robot.
 * @see Drivetrain 
 * @see DriveStraightCommand
 * @author Bryce Paputa
 */
public class TurnCommand extends Command implements PIDSource, PIDOutput{
    
    /**
     * Constructor that takes a distance to turn.
     * If you pass distance = 2, then both sides of the Drivetrain should move 1 rotation in opposite directions.
     * CW is positive. TODO: Confirm direction and tune.
     * @param distance how much to turn.
     */
    public TurnCommand(double distance) {
        requires(Drivetrain.getInstance());
        this.distance = distance;
        setTimeout(0);
    }
    
    /**
     * Returns the distance the drivetrain has rotated. Used by the PID controller.
     * @return distance rotated
     * @see PIDController
     * @see PIDSource
     */
    public double pidGet() {
        //SmartDashboard.putNumber("rotation", Drivetrain.getRotation());
        //SmartDashboard.putNumber("distance", Drivetrain.getLeftDistance()+Drivetrain.getRightDistance());
        return Drivetrain.getRotation();
    }        
    
    /**
     * Fields that keep track of the last outputted values. 
     * @see #debug
     */
    private double lastRot = 0, lastCompensationSpeed;
    
    /**
     * Outputs the value from the PID controller, along with a value to keep the robot centered, to the Drivetrain.
     * @param rot rotation speed from PID controller
     * @see PIDController
     * @see PIDOutput
     */
    public void pidWrite(double rot){
        //lastCompensationSpeed = - centeringKp * (Drivetrain.getDistance());
        Drivetrain.arcade(0, lastRot = -rot);
    }
    
    /**
     * Parameters that control the PID controller.
     */
    private final double P = .3, I = 0.015, D = 0, centeringKp = 0;
    private double distance;
    
    /**
     * PID controller.
     */
    private final PIDController pid = new PIDController(P, I, D, this, this);
    
    /**
     * Called once when the command is scheduled to run.
     * Resets and starts the encoders and PID controller.
     */
    protected void initialize() {
        _init(distance);
    }

    protected void _init(double pDistance){
        setTimeout(2);
        LiveWindow.addActuator("Drivetrain", "Turn PID", pid);
        Drivetrain.resetEncoders();
        Drivetrain.startEncoders();
        pid.reset();
        pid.setSetpoint(pDistance);
        pid.enable();
    }
    /**
     * Called repeatedly when this Command is scheduled to run.
     * Doesn't do anything, the PIDTask loop takes care of the periodic operations.
     */ 
    protected void execute() {
    }

    /**
     * Returns true once the PID controller is on target. 
     * The command is stopped when this becomes true.
     * @see PIDController#onTarget()
     */ 
    protected boolean isFinished() {
        return Math.abs(pid.getError())<.01 || isTimedOut();
    }

    /**
     * Called once when the command finishes. 
     * Stops the PID Controller.
     */ 
    protected void end() {
        pid.disable();
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run. Calls {@link #end()}.
     */ 
    protected void interrupted() {
        System.err.println("DO NOT INTERRUPT DRIVETRAIN TURN PID!");
        end();
    }
}
