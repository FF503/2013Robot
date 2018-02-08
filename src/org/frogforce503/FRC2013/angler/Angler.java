package org.frogforce503.FRC2013.angler;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.frogforce503.FRC2013.RobotMap;
import org.frogforce503.FRC2013.util.calibrations.AbsoluteAnalogSensorCalibration;
import org.frogforce503.FRC2013.util.controllers.StringPotPIDController;
import org.frogforce503.FRC2013.util.limitSwitches.NCLimitSwitch;
import org.frogforce503.FRC2013.util.limitSwitches.SingleLimitSwitchSystem;


/**
 *
 * @author Bryce Paputa
 */
public class Angler extends Subsystem {
    private final static Talon motor = new Talon(RobotMap.SHOOTER_ANGLER_CHANNEL);
    private final static NCLimitSwitch lowerLimitSwitch = new NCLimitSwitch(RobotMap.SHOOTER_ANGLER_LOWER_LIMIT_SWITCH_CHANNEL);
    private final static SingleLimitSwitchSystem limitSwitchSystem = new SingleLimitSwitchSystem(lowerLimitSwitch);
    private final static AbsoluteAnalogSensorCalibration calibration = new AbsoluteAnalogSensorCalibration(RobotMap.SHOOTER_ANGLER_POT_CHANNEL);
    private final static double p = 0.015, i = 0.0005, d = 0; /*AK 131115 lowered P amd I from 25 and 7*/
    
    private Angler(){}
    public final static StringPotPIDController controller = new  StringPotPIDController(motor, limitSwitchSystem, calibration, p, i, d);
    
    private final static Angler instance = new Angler();
    
    public static Angler getInstance(){
        return instance;
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new ManualAngleCommand());
    }

    
}
