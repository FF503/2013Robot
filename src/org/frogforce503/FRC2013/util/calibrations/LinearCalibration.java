package org.frogforce503.FRC2013.util.calibrations;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;




/**
 *
 * @author Bryce Paputa
 */
public abstract class LinearCalibration implements PIDSource{
    private boolean isCalibrated = false;
    private double m;
    private double b;
    private double vLow = 0;
    private double vHigh = 0;
    public final double LOWER_DISTANCE;
    public final double UPPER_DISTANCE;
    public final String name;
    public LinearCalibration(double lowerDistance, double upperDistance, String name){
        LOWER_DISTANCE = lowerDistance;
        UPPER_DISTANCE = upperDistance;
        this.name = name;
    }
    
    public void setLowerPoint() {
        vLow = getVoltage();
        SmartDashboard.putNumber("vLow", vLow);
        recalibrate();
    }
    
    public void setUpperPoint() {
        vHigh = getVoltage();
        SmartDashboard.putNumber("vHigh", vHigh);
        recalibrate();
    }

    public void recalibrate() {
        if(vLow - vHigh == 0) {
            vLow += 1.0E-4;
        }
        m = (LOWER_DISTANCE - UPPER_DISTANCE) / (vLow - vHigh);
        b = UPPER_DISTANCE - m * vHigh;
        isCalibrated = true;
        SmartDashboard.putNumber("m", m);
        SmartDashboard.putNumber("b", b);
    }

    public boolean isCalibrated() {
        return isCalibrated;
    }

    public double getDistanceFromVoltage(double v) {
        
        return m * v + b;
    }

    public abstract double getUsableVariableFromDistance(double d);
    
    public abstract double getVoltage();

    public double pidGet() {
        return getUsableVariableFromDistance(getDistanceFromVoltage(getVoltage()));
    }
}
