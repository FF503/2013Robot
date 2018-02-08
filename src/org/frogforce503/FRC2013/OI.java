package org.frogforce503.FRC2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frogforce503.FRC2013.angler.Angler;
import org.frogforce503.FRC2013.angler.AutoCalibrateAnglerCommandGroup;
import org.frogforce503.FRC2013.climber.ClimberToggleCommand;
import org.frogforce503.FRC2013.drivetrain.Drivetrain;
import org.frogforce503.FRC2013.pneumatics.Compressor;
import org.frogforce503.FRC2013.pneumatics.ManualShiftCommand;
import org.frogforce503.FRC2013.pneumatics.Shifter;
import org.frogforce503.FRC2013.shooter.ExtendFiringMechanismCommand;
import org.frogforce503.FRC2013.shooter.GotoPositionCommandGroup;
import org.frogforce503.FRC2013.shooter.LaunchCommandGroup;
import org.frogforce503.FRC2013.shooter.SetShooterStateCommand;
import org.frogforce503.FRC2013.shooter.Shooter;
import org.frogforce503.FRC2013.shooter.Shooter.ShooterPosition;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * @author Bryce Paputa
 */
public class OI {
    
    /**
     * Objects representing physical joysticks.
     */
        //Joysticks
    public static final Joystick leftStick, rightStick, operatorStick;
        //Driver Buttons
    public final static JoystickButton upShiftButton, downShiftButton, powerButton;
    public final static JoystickButton climbButton;
        //Operator Buttons
    public static final JoystickButton operatorOverRideButton;
    public static final JoystickButton farButton, backOfPyramidAngleButton, frontOfPyramidAngleButton, slowAutonAimButton, loadButton;
    public final static JoystickButton shooterOffButton, shooterFireButton, autoCalibrateAnglerButton;
    public final static JoystickButton shooterManualRetractButton;
    public static final int DRIVER_PORT = 1;
    public static final int LEFTX = 1;
    public static final int LEFTY = 2;
    public static final int RIGHTX = 3;
    public static final int RIGHTY = 4;
    public static final int DPADX = 5;
    /*
     * Static block that initializes the joysticks and buttons and 
     * puts the commands to the SmartDashboard.
     */
    static {
        SmartDashboard.putData(Drivetrain.getInstance());
        SmartDashboard.putData(Shooter.getInstance());
        SmartDashboard.putData(Shifter.getInstance());
        SmartDashboard.putData(Compressor.getInstance());
        SmartDashboard.putData(Angler.getInstance());
        
            //Joysticks
        leftStick = new Joystick(1);
        rightStick = new Joystick(2);
        operatorStick = new Joystick(3);
        
            //Driver buttons
        upShiftButton = new JoystickButton(rightStick, 3);
        downShiftButton = new JoystickButton(rightStick, 2);
        upShiftButton.whenPressed(new ManualShiftCommand(Shifter.Position.UP));
        downShiftButton.whenPressed(new ManualShiftCommand(Shifter.Position.DOWN));
        climbButton = new JoystickButton(rightStick, 4);
        climbButton.whenPressed(new ClimberToggleCommand());
        powerButton = new JoystickButton(leftStick, 5);
        
            //Operator buttons
        shooterOffButton = new JoystickButton(operatorStick, 7);
        shooterOffButton.whenPressed(new SetShooterStateCommand(false));
        shooterFireButton = new JoystickButton(operatorStick, 6);
        shooterFireButton.whileHeld(new LaunchCommandGroup());
        autoCalibrateAnglerButton = new JoystickButton(operatorStick, 5);
        autoCalibrateAnglerButton.whenPressed(new AutoCalibrateAnglerCommandGroup());
        backOfPyramidAngleButton = new JoystickButton(operatorStick, 1);
        backOfPyramidAngleButton.whileHeld(new GotoPositionCommandGroup(ShooterPosition.BACK_LEG));
        frontOfPyramidAngleButton = new JoystickButton(operatorStick, 4);
        frontOfPyramidAngleButton.whileHeld(new GotoPositionCommandGroup(ShooterPosition.FRONT_LEG));
        slowAutonAimButton = new JoystickButton(operatorStick, 2);
        slowAutonAimButton.whileHeld(new GotoPositionCommandGroup(ShooterPosition.SLOW));
        loadButton = new JoystickButton(operatorStick, 3);//3500
        loadButton.whileHeld(new GotoPositionCommandGroup(ShooterPosition.LOADING));
        farButton = new JoystickButton(operatorStick, 10);//3750
        farButton.whileHeld(new GotoPositionCommandGroup(ShooterPosition.FAR));
        operatorOverRideButton = new JoystickButton(operatorStick, 8);
        shooterManualRetractButton = new JoystickButton(operatorStick, 9);
        shooterManualRetractButton.whenPressed(new ExtendFiringMechanismCommand());
        
    }
    /**
     * Gets the left drive input.
     * @return left drive input
     */
    public static double getLeftDriveInput(){
        return -leftStick.getY();
    }
    
    /**
     * Gets the right drive input.
     * @return right drive input
     */
    public static double getRightDriveInput(){
        return -rightStick.getY();
    }
    
    
    public static double getOperatorRotInput(){
        return -operatorStick.getRawAxis(RIGHTX)*.75;
    }
    
    public static boolean getOperatorOverRide(){
        return operatorOverRideButton.get();
    }
    
    public static double getAngleInput(){
        return operatorStick.getY();
    }
}

