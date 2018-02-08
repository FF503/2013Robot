package org.frogforce503.FRC2013.pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frogforce503.FRC2013.RobotMap;
import org.frogforce503.FRC2013.drivetrain.Drivetrain;


/**
 *
 * @author Bryce Paputa
 */
public class Shifter extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private static final Solenoid shifter = new Solenoid(RobotMap.DRIVETRAIN_SHIFTER_CHANNEL);
    
    private static final Shifter instance = new Shifter();
    
    public static Shifter getInstance(){
        return instance;
    }
    
    private Shifter(){};
    
    public static class Position{
        public final static int UNDEF_val = -1;
        public final static int UP_val = 0;
        public final static int DOWN_val = 1;
        public final int value;
        private Position(int val){
            value = val;
        }
        public final static Position UNDEF = new Position(UNDEF_val);
        public final static Position UP = new Position(UP_val);
        public final static Position DOWN = new Position(DOWN_val);
    }
    
    public static void shift(Position pos){
        if(lastPosition == pos){
            return;
        } else {
            lastPosition = pos;
        }
        if(pos == Position.UP){
            shifter.set(false);
            SmartDashboard.putString("shifter position", "up");
        } else if(pos == Position.DOWN){
            shifter.set(true);
            SmartDashboard.putString("shifter position", "down");
        }
        updateShiftTimestamp();
    }
    
    private static double lastShiftTimestamp = 0;
    private static boolean manualOverRide = false;
    private static final double UP_SHIFT_THRESHOLD = 4,
                                DOWN_SHIFT_THRESHOLD = 2;
    private static Position lastPosition = Position.UNDEF;
    
    
    private static void updateShiftTimestamp(){
        lastShiftTimestamp = Timer.getFPGATimestamp();
    }
    
    private static boolean canAutoShift(){
        return //(Timer.getFPGATimestamp() - lastShiftTimestamp) > .5d &&
               Math.abs(Drivetrain.getRotationThrottle()) < .75d && 
               !manualOverRide;
    }
    
    private static void autoShift(Position pos){
        if(canAutoShift()){
            shift(pos);
        }
    }
    
    public static void runAutoShift(){
        if(!manualOverRide){
            if(Math.abs(Drivetrain.getAvgSpeed()) > UP_SHIFT_THRESHOLD){
                autoShift(Position.UP);
            } else if(Math.abs(Drivetrain.getAvgSpeed()) < DOWN_SHIFT_THRESHOLD){
                autoShift(Position.DOWN);
            }
        }
    }
    
    public static void manualShift(Position pos){
        manualOverRide = true;
        shift(pos);
    }
    
    public static void setManualOverRide(boolean overRide){
        manualOverRide = overRide;
    }
    
    public static Position getPosition(){
        return lastPosition;
    }
    
    public void initDefaultCommand() {
    }
}
