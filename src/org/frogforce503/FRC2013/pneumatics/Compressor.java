package org.frogforce503.FRC2013.pneumatics;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.frogforce503.FRC2013.RobotMap;

/**
 *
 * @author Bryce Paputa
 */
public class Compressor extends Subsystem {
    private final static edu.wpi.first.wpilibj.Compressor compressor = new edu.wpi.first.wpilibj.Compressor(
                                                RobotMap.PNEUMATICS_PRESURE_SWITCH_CHANNEL,
                                                RobotMap.PNEUMATICS_COMPRESSOR_CHANNEL);
    private Compressor(){}
    private final static Compressor instance = new Compressor();
    public static Compressor getInstance(){
        return instance;
    }
    
    public static void start(){
        compressor.start();
    }
    
    public static void end(){
        compressor.stop();
    }

    public static boolean getMaybeRunning(){
        return !compressor.getPressureSwitchValue();
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new StartCompressorCommand());
    }
}
