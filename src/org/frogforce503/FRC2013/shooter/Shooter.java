package org.frogforce503.FRC2013.shooter;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.GearTooth;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Timer;
import java.util.TimerTask;
import org.frogforce503.FRC2013.RobotMap;

/**
 *
 * @author Bryce Paputa
 */
public class Shooter extends Subsystem{
    public static class ShooterPosition{
        final private double angle, speed, threshold;
        private ShooterPosition(double angle, double speed, double threshold){
            this.angle = angle;
            this.speed = speed;
            this.threshold = threshold;
        }
        public double getAngle(){
            return angle;
        }
        public double getSpeed(){
            return speed;
        }
        public double getThreshold(){
            return threshold;
        }
        final public static ShooterPosition AIMING = new ShooterPosition(325, 0, 1);
        final public static ShooterPosition SLOW = new ShooterPosition(325, 1000, 100000);
        final public static ShooterPosition FRONT_LEG = new ShooterPosition(973-365, 3000, .01);//3500
        final public static ShooterPosition BACK_LEG = new ShooterPosition(550, 2500, .02);//3500
        final public static ShooterPosition BACK_BAR = new ShooterPosition(250, 3250, .01);//3000
        final public static ShooterPosition LOADING = new ShooterPosition(250, 3500, .02);//0
        final public static ShooterPosition FAR = new ShooterPosition(250, 3750, .02);//4500
    }
    public static class SmartPosition extends ShooterPosition{
        public SmartPosition(){
            super(0,0,0);
            SmartDashboard.putNumber("smartPositionAngle", 520);
            SmartDashboard.putNumber("smartPositionSpeed", 3000);
            SmartDashboard.putNumber("smartPositionThreshold", .001);
        }
        final public static SmartPosition OBJ = new SmartPosition();
        public double getAngle(){
            return SmartDashboard.getNumber("smartPositionAngle");
        }
        public double getSpeed(){
            return SmartDashboard.getNumber("smartPositionSpeed");
        }
        public double getThreshold(){
            return SmartDashboard.getNumber("smartPositionThreshold");
        }
    }
    private static final Talon shooter = new Talon(RobotMap.SHOOTER_MOTOR_CHANNEL);
    
    private static final Counter gt = new GearTooth(RobotMap.SHOOTER_GEAR_TOOTH_SENSOR_CHANNEL);
    
    private static final Relay gtRelay = new Relay(RobotMap.SHOOTER_GEAR_TOOTH_SENSOR_POWER_CHANNEL);
    
    private static final Solenoid firingMechanism = new Solenoid(RobotMap.SHOOTER_FIRING_MECHANISM_FORWARD_CHANNEL);
    
    static {
        LiveWindow.addActuator("Shooter", "Motor", shooter);        
        LiveWindow.addSensor("Shooter", "gt", gt);
        LiveWindow.addSensor("Shooter", "gtRelay", gtRelay);
        gt.start();
        
        gtRelay.set(Value.kForward);
        shooter.setSafetyEnabled(false);
    }
    
    private static final int NUM_SAMPLES = 10;
    private static double targetSpeed = 1000;
    private static double PERCENT_THRESHOLD = .02;
    
    private static boolean isOn = false;
    
    private static double avgSpeed = 0;
    private static boolean isUpToSpeed = false;
    private static double measuredRPM;
    private Shooter(){}
    
    private static Shooter instance = new Shooter();
    
    private static double getRawSpeed(){
        //               frequency       s/min   ratio    CPR
        measuredRPM = 1/gt.getPeriod() * 60 *   66d/22d / 32d;
        avgSpeed = avgSpeed / (NUM_SAMPLES) * (NUM_SAMPLES - 1) + measuredRPM / (NUM_SAMPLES);
        return measuredRPM;
    }
    
    public static double getSpeed(){
        return measuredRPM;
    }
    
    public static double getAvgSpeed(){
        return avgSpeed;
    }
    
    public static void setTargetSpeed(double speed){
        targetSpeed = speed;
    }
    
    public static double getTargetSpeed(){
        return targetSpeed;
    }
    
    public static void setSpeed(double speed){
        shooter.set(speed);
    }
    
    public static boolean onTarget(){        
        return Math.abs(avgSpeed-targetSpeed)/targetSpeed < PERCENT_THRESHOLD;
    }
    private static double maxError, minError;
    
    private static final TimerTask SpeedTask = new TimerTask(){
        double avgOutput = 0;
        double outputAvgSamples = 10;
        public void run() {
            double speed = getRawSpeed(), output = 0; 
            if(isOn()){
                if(speed < targetSpeed){
                    output = 1;
                }
                setSpeed(output);
                if(speed > targetSpeed && targetSpeed != 0){
                    isUpToSpeed = true;
                }
                if(isUpToSpeed){
                    if((speed - targetSpeed < minError) && (speed < targetSpeed)){
                        minError = speed - targetSpeed;
                        SmartDashboard.putNumber("minError", minError);
                    } 
                    if ((speed - targetSpeed> maxError) && (speed > targetSpeed)){
                        maxError = speed - targetSpeed;
                        SmartDashboard.putNumber("maxError", maxError);
                    }
                }
            }
            SmartDashboard.putNumber("used targetSpeed", targetSpeed);
            SmartDashboard.putNumber("Shooter Output", output);
            SmartDashboard.putNumber("gear tooth speed graph", speed);
            SmartDashboard.putNumber("gear tooth speed view", speed);
            avgOutput = avgOutput / (outputAvgSamples) * (outputAvgSamples - 1) + output / (outputAvgSamples);
            SmartDashboard.putNumber("avg output", avgOutput);
            double avgSpeed = getAvgSpeed();
            SmartDashboard.putNumber("avg speed graph", avgSpeed);
            SmartDashboard.putNumber("avg speed view", avgSpeed);
        }        
    };
    private static final long SPEED_TASK_PERIOD = 4;
    private static final Timer timer = new Timer();
    

    static{
        timer.schedule(SpeedTask, 0, SPEED_TASK_PERIOD);
    }

    public static Shooter getInstance() {
        return instance;
    }


    protected void initDefaultCommand() {
    }
    
    public static boolean isOn(){
        return isOn;
    }
    
    public static void setOn(boolean state) {
        isUpToSpeed = false;
        isOn = state;
        gtRelay.set(Value.kForward);
        SmartDashboard.putBoolean("shooter state", state);
        shooter.set(0);
        minError = maxError = 0;
    }
    
    
    public static void extendFiringMechanism(){
        firingMechanism.set(false);
    }
    
    public static double lastTime = 0;
    public static void retractFiringMechanism(){
        firingMechanism.set(true);
        System.out.println("T: " + (lastTime - edu.wpi.first.wpilibj.Timer.getFPGATimestamp()));
        lastTime = edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
    }
    
    private static int numFrisbees = -1;
    
    public static void resetNumFrisbees(int num){
        numFrisbees = num;
    }
    
    public static void decrementNumFrisbees(){
        numFrisbees--;
    }
    
    public static double getNumFrisbees(){
        return numFrisbees;
    }
    
    public static void setThreshold(double newThreshold){
        PERCENT_THRESHOLD = newThreshold;
    }
}