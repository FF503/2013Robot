package org.frogforce503.FRC2013.util.calibrations;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 * @author Bryce Paputa
 */
public abstract class StringPotCalibration extends LinearCalibration{
    private final AnalogChannel pot;
    public StringPotCalibration(double lowerDistance, double upperDistance, String name, AnalogChannel pot){
        super(lowerDistance, upperDistance, name);
        this.pot = pot;
    }
    private double average = 0;
    private int avgCounts = 10;
    public double getVoltage() {
        double v = pot.getVoltage();
        double c = pot.getValue();
        SmartDashboard.putNumber("ad value", c);
        SmartDashboard.putNumber("raw voltage", v);
        average = average * ((avgCounts-1) / avgCounts) + v / avgCounts;
        SmartDashboard.putNumber("avg voltage", average);
        return average;
    }
    
    public double get(){
        return getDistanceFromVoltage(getVoltage());
    }
}
