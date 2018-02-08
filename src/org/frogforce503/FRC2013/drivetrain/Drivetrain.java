package org.frogforce503.FRC2013.drivetrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.frogforce503.FRC2013.OI;
import org.frogforce503.FRC2013.RobotMap;
import org.frogforce503.FRC2013.util.CoupledTalons;

/**
 * Drivetrain subsystem. Defines methods to control the robot's movement and 
 * to measure it's speed and position.
 * @see DriveStraightCommand 
 * @see TurnCommand 
 * @see TeleopDriveCommand
 * @author Bryce Paputa
 */
public final class Drivetrain extends Subsystem{
    
    /**
     * Motor objects. Each object represents two motors that should always have the exact same output.
     */
    private static final CoupledTalons leftMotors, rightMotors;
    
    /**
     * Robot drive object that handles the low level output calculation.
     */
    private static final RobotDrive drive;
    
    /**
     * Encoders.
     */
    private static final Encoder leftEncoder, rightEncoder;
    
    /**
     * Static instance of Drivetrain. This should be the only Drivetrain object ever.
     */
    private static final Drivetrain instance = new Drivetrain();

    private static final double POS_MAX_DELTA = .03;
    
    private static final double NEG_MAX_DELTA = .005;
    
    /**
     * Returns the single instance of Drivetrain.
     * @return instance of Drivetrain
     */
    public static Drivetrain getInstance() {
        return instance;
    }
    
    /*
     * Static block that initializes all of the drivetrain components.
     */
    static {
        leftMotors = new CoupledTalons(RobotMap.DRIVETRAIN_LEFT_FRONT_MOTOR_CHANNEL, 
                                    RobotMap.DRIVETRAIN_LEFT_BACK_MOTOR_CHANNEL, true, POS_MAX_DELTA, NEG_MAX_DELTA);
        rightMotors = new CoupledTalons(RobotMap.DRIVETRAIN_RIGHT_FRONT_MOTOR_CHANNEL, 
                                    RobotMap.DRIVETRAIN_RIGHT_BACK_MOTOR_CHANNEL, true, POS_MAX_DELTA, NEG_MAX_DELTA);
        drive = new RobotDrive(rightMotors, leftMotors);
        drive.setSafetyEnabled(false);
        leftEncoder = new Encoder(RobotMap.DRIVETRAIN_LEFT_ENCODER_CHANNEL_A,
                                    RobotMap.DRIVETRAIN_LEFT_ENCODER_CHANNEL_B, true,
                                    Encoder.EncodingType.k4X);
        rightEncoder = new Encoder(RobotMap.DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A,
                                    RobotMap.DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B, false,
                                    Encoder.EncodingType.k4X);
        leftEncoder.setDistancePerPulse(1d/360d);
        leftEncoder.setMaxPeriod(1);
        rightEncoder.setDistancePerPulse(1d/250d);
        rightEncoder.setMaxPeriod(1);
        startEncoders();
        LiveWindow.addActuator("Drivetrain", "Left Motors", leftMotors);
        LiveWindow.addActuator("Drivetrain", "Right Motors", rightMotors);
        LiveWindow.addSensor("Drivetrain", "Left Encoder", leftEncoder);
        LiveWindow.addSensor("Drivetrain", "Right Encoder", rightEncoder);
    }
    
    /**
     * Private constructor to create the static instance.
     */
    private Drivetrain(){
        super("Drivetrain");
    }
    
    /**
     * Drive the robot in tank mode, squaring the inputs.
     * @param leftSpeed  left motor speed
     * @param rightSpeed right motor speed
     */
    public static void tank(double leftSpeed, double rightSpeed){
        drive.tankDrive(leftSpeed, rightSpeed, true);
    }
    
    /**
     * Drive the robot in arcade mode, squaring the inputs.
     * @param speed forward speed
     * @param rot   rotational speed, positive CW(?)
     */
    public static void arcade(double speed, double rot){
        drive.arcadeDrive(speed, rot, false);
    }
    
    /**
     * Drive the robot in "Drive" mode. 
     * Takes a speed value and a straightening value.
     * @param speed forward speed
     * @param curve straightening value, positive CW
     */
    public static void drive(double speed, double curve){
        drive.drive(speed, curve);
    }
    
    /**
     * Reset the encoders. 
     * Sets the accumulated distance to zero.
     */
    public static void resetEncoders(){
        leftEncoder.reset();
        rightEncoder.reset();
    }
    
    /**
     * Starts the encoders.
     * Starts the distance accumulation, they will both read 0.0 
     * if you forget to call this.
     */
    public static void startEncoders(){
        leftEncoder.start();
        rightEncoder.start();
    }
    
    /**
     * Gets the distance the left encoder has rotated.
     * @return the distance rotated, in rotations, positive when the robot is moving forward
     */
    public static double getLeftDistance(){
        return leftEncoder.getDistance();
    }
    
    /**
     * Gets the distance the right encoder has rotated.
     * @return the distance rotated, in rotations, positive when the robot is moving forward
     */
    public static double getRightDistance(){
        return rightEncoder.getDistance();
    }
    
    /**
     * Gets the distance the robot has traveled.
     * @return 2x the distance traveled
     */
    public static double getDistance(){
        return getLeftDistance() + getRightDistance();
    }
    
    /**
     * Gets the rotation of the robot
     * @return the distance rotated, positive CW
     */
    public static double getRotation(){
        return getLeftDistance() - getRightDistance();
    }
    
    /**
     * Gets the last power outputted to the left motors.
     * @return the last power outputted
     */
    public static double getLeftThrottle(){
        return OI.getLeftDriveInput();
    }
    
    /**
     * Gets the last power outputted to the right motors.
     * @return the last power outputted
     */
    public static double getRightThrottle(){
        return OI.getRightDriveInput();
    }
    
    public static double getRotationThrottle(){
        return getLeftThrottle() - getRightThrottle();
    }
    
    public static double getThrottle(){
        return getRightThrottle() + getLeftThrottle();
    }
    
    private final static int NUM_SAMPLES = 10;
    private static double leftAvgSpeed = 0, rightAvgSpeed = 0;
    
    public static double getLeftAvgSpeed(){
        return leftAvgSpeed = leftAvgSpeed / (NUM_SAMPLES) * (NUM_SAMPLES-1) + leftEncoder.getRate() / NUM_SAMPLES;
    }
    
    public static double getRightAvgSpeed(){
        return rightAvgSpeed = rightAvgSpeed / (NUM_SAMPLES) * (NUM_SAMPLES-1) + rightEncoder.getRate() / NUM_SAMPLES;
    }
    
    public static double getAvgSpeed(){
        return getLeftAvgSpeed() + getRightAvgSpeed();
    }
    
    public static double getRotationAvgSpeed(){
        return getLeftAvgSpeed() - getRightAvgSpeed();
    }
    /**
     * Sets the default command ({@link TeleopDriveCommand}).
     */
    protected void initDefaultCommand() {
        setDefaultCommand(new TeleopDriveCommand());
    }
}
