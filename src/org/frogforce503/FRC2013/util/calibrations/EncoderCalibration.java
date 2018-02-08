package org.frogforce503.FRC2013.util.calibrations;

import edu.wpi.first.wpilibj.Encoder;




/**
 * Calibration to use an Encoder as a sensor
 *  Note: Don't use for drivetrain, this is for a singular encoder, the drivetrain
 *      must use both of them.
 * @author Bryce Paputa
 */
public class EncoderCalibration implements Calibration{
    private boolean isCalibrated = false;
    private final Encoder encoder;
    public EncoderCalibration(Encoder encoder){
        this.encoder = encoder;
    }
    
    public void setLowerPoint() {
        encoder.reset();
        encoder.start();
        isCalibrated = true;
    }
    
    public void setUpperPoint() {}
    
    public boolean isCalibrated() {
        return isCalibrated;
    }
    
    public double pidGet() {
        return encoder.getDistance();
    }

    public double get() {
        return encoder.getDistance();
    }
}
