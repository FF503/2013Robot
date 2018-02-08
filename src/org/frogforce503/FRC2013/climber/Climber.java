package org.frogforce503.FRC2013.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.frogforce503.FRC2013.RobotMap;


/**
 *
 * @author Bryce Paputa
 */
public class Climber extends Subsystem {
    private static final DoubleSolenoid cylinder = new DoubleSolenoid(RobotMap.CLIMBER_CLIMB_UP_CHANNEL, RobotMap.CLIMBER_CLIMB_DOWN_CHANNEL);
    private static boolean state = false;
    private static Climber instance = new Climber();
    private Climber(){};
    
    public static Climber getInstance(){
        return instance;
    }
    
    public static void toggle(){
        cylinder.set((state = !state)? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }
    
    protected void initDefaultCommand() {
        
    }
}
