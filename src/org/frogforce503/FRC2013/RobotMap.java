package org.frogforce503.FRC2013;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public interface RobotMap {
    public static final int 
                                //PWM
                            DRIVETRAIN_LEFT_FRONT_MOTOR_CHANNEL = 1, 
                            DRIVETRAIN_LEFT_BACK_MOTOR_CHANNEL = 2, 
                            DRIVETRAIN_RIGHT_FRONT_MOTOR_CHANNEL = 3, 
                            DRIVETRAIN_RIGHT_BACK_MOTOR_CHANNEL = 4,
                            SHOOTER_MOTOR_CHANNEL = 5,
                            SHOOTER_ANGLER_CHANNEL = 6,
                                //GPIO
                            DRIVETRAIN_LEFT_ENCODER_CHANNEL_A = 1,
                            DRIVETRAIN_LEFT_ENCODER_CHANNEL_B = 2,
                            DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A = 3,
                            DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B = 4,
                            PNEUMATICS_PRESURE_SWITCH_CHANNEL = 5,
                            SHOOTER_GEAR_TOOTH_SENSOR_CHANNEL = 7,
                            SHOOTER_ANGLER_LOWER_LIMIT_SWITCH_CHANNEL = 8,
                                //RELAYS
                            SHOOTER_GEAR_TOOTH_SENSOR_POWER_CHANNEL = 1,
                            PNEUMATICS_COMPRESSOR_CHANNEL = 2,
                            
                                //SOLENOIDS
                                //1 and 3 are broken
                            SHOOTER_FIRING_MECHANISM_FORWARD_CHANNEL = 8,
                            DRIVETRAIN_SHIFTER_CHANNEL = 2,
                            CLIMBER_CLIMB_DOWN_CHANNEL = 4,
                            CLIMBER_CLIMB_UP_CHANNEL = 5,
                                //ANALOG INPUT
                            SHOOTER_ANGLER_POT_CHANNEL = 1;    
}
 