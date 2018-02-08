package org.frogforce503.FRC2013.util.calibrations;

import edu.wpi.first.wpilibj.PIDSource;



/**
 * Class to take sensor data and give back a usable number
 * @author Bryce Paputa
 */
public interface Calibration extends PIDSource{
    /**
     * Adds a lower calibration point
     */
    public void setLowerPoint();
    
    /**
     * Adds a upper calibration point
     */
    public void setUpperPoint();
    /**
     * Gets a usable number from the sensor
     * @return A usable reading from the sensor
     */
    public double get();
    /**
     * Says whether or not this object is calibrated
     * @return true if calibrated, false otherwise
     */
    public boolean isCalibrated();
}
