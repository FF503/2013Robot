package org.frogforce503.FRC2013.util.calibrations;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * Calibration to use the raw AD counts from an analog sensor
 * @author Bryce Paputa
 */
public class AbsoluteAnalogSensorCalibration implements Calibration{
    private AnalogChannel input;
    private double calibration = -1;
    private boolean isCalibrated = false;
    
    /**
     * Creates a new AbsolueAnalogSensorCalibration object, using the given 
     * channel and assuming cRIO slot 1
     * @param channel
     */
    public AbsoluteAnalogSensorCalibration(int channel){
        input = new AnalogChannel(channel);
    }
    
    /**
     * Zeros the sensor to the current value
     */
    public void setLowerPoint() {
        calibration = input.getValue();
        SmartDashboard.putNumber("angle zero", calibration);
        isCalibrated = true;
    }

    /**
     * Sets the upper point of the calibration, doesn't actually do anything
     * because this calibration only uses one point
     */
    public void setUpperPoint() {
    }

    /**
     * Gets a reading, taking the calibration into account 
     * (on our robot the sensor was wired so that a high AD count was a lower angle,
     *      this function is multiplied by -1 to fix that)
     * @return 
     */
    public double get() {
        return calibration - input.getValue();
    }
    
    /**
     * Says whether or not the object has been calibrated
     * @return true if setLowerPoint has been called, false otherwise
     */
    public boolean isCalibrated() {
        return isCalibrated;
    }

    /**
     * Pseudonym for get to implement PIDSource
     * @return The value of get()
     */
    public double pidGet() {
        return get();
    }
}
