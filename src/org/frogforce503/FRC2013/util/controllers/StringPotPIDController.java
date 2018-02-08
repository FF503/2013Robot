package org.frogforce503.FRC2013.util.controllers;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frogforce503.FRC2013.util.calibrations.Calibration;
import org.frogforce503.FRC2013.util.limitSwitches.LimitSwitchSystem;



/**
 *
 * @author Bryce Paputa
 */
public class StringPotPIDController extends SimpleController implements PIDOutput{
    public final Calibration calibration;
    private final PIDController pid;
    private final double P, I, D;
    
    public StringPotPIDController(SpeedController motor, LimitSwitchSystem limitSwitchSystem, 
                         Calibration calibration, double p, double i, double d){
        super(motor, limitSwitchSystem);
        this.calibration = calibration;
        pid = new PIDController(P = p, I = i, D = d, calibration, this);
        pid.setOutputRange(-.3, .3);
        pid.setAbsoluteTolerance(1);
        SmartDashboard.putData("angle pid", pid);
    }
    
    public void disablePID(){
        pid.disable();
    }
    
    public void enablePID(){
        pid.enable();
    }
    
    public void setPIDSetpoint(double setpoint){
        pid.setSetpoint(setpoint);
    }
    
    boolean last0 = false, last1 = false;
    public boolean isPIDOnTarget(){
        boolean result = last0 && last1 && pid.onTarget();
        last0 = last1;
        last1 = pid.onTarget();
        return result;
    }
    
    public void setLowerCalibrationPoint(){
        calibration.setLowerPoint();
    }
    
    public void setUpperCalibrationPoint(){
        calibration.setUpperPoint();
    }

    public boolean isCalibrated() {
        return calibration.isCalibrated();
    }
    
    public double getDistance(){
        return calibration.get();
    }
    
    public double getSetpoint(){
        return pid.getSetpoint();
    }
    
    public double getAngle(){
        return getDistance();
    }
    
    public void pidWrite(double output) {
        set(output);
        SmartDashboard.putNumber("pidError", pid.getError());
    }
}
