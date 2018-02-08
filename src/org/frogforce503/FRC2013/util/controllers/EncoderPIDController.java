package org.frogforce503.FRC2013.util.controllers;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frogforce503.FRC2013.util.calibrations.Calibration;
import org.frogforce503.FRC2013.util.limitSwitches.SingleLimitSwitchSystem;



/**
 *
 * @author Bryce Paputa
 */
public class EncoderPIDController extends SimpleController implements PIDOutput{
    private final Encoder encoder;
    private final Calibration calibration;
    private final PIDController pid;
    private final double P, I, D;
    
    public EncoderPIDController(SpeedController motor, SingleLimitSwitchSystem limitSwitchSystem, Encoder encoder, 
                         Calibration calibration, double p, double i, double d){
        super(motor, limitSwitchSystem);
        this.encoder = encoder;
        this.calibration = calibration;
        pid = new PIDController(P = p, I = i, D = d, calibration, this);
        pid.setOutputRange(-.333, .333);
        pid.setAbsoluteTolerance(.25);
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
    
    public boolean isPIDOnTarget(){
        return pid.onTarget();
    }
    
    public void setLowerCalibrationPoint(){
        calibration.setLowerPoint();
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
    
    public void saveCalibration(){
        //calibration.saveToFile();
    }
    
    public void readCalibrationFromFile(){
      //  calibration.readFromFile();
    }

    public void pidWrite(double output) {
        set(output);
        SmartDashboard.putNumber("pidError", pid.getError());
    }
}
