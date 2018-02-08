/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.frogforce503.FRC2013;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frogforce503.FRC2013.angler.Angler;
import org.frogforce503.FRC2013.autonomous.BackLegAutonGroup;
import org.frogforce503.FRC2013.autonomous.FrontLegAutonGroup;
import org.frogforce503.FRC2013.autonomous.SmartAutonGroup;
import org.frogforce503.FRC2013.drivetrain.Drivetrain;
import org.frogforce503.FRC2013.pneumatics.Shifter;
import org.frogforce503.FRC2013.pneumatics.StartCompressorCommand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class FF503Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
        //Object to choose autonomous program with smart dashboard
    private final static SendableChooser autonChooser = new SendableChooser();
    
    public void robotInit() {
            //Minimum number of seconds between each shot
        SmartDashboard.putNumber("shoot delay", .75);     
            //Maximum drivetrain speed ramp
        SmartDashboard.putNumber("ramp", 0.0075);
            //Auton programs
        autonChooser.addDefault("Back Leg Of Pyramid", new BackLegAutonGroup());
        autonChooser.addObject("Front Leg Of Pyramid", new FrontLegAutonGroup());
            //Gets it's numbers from the smart dashboard instead of hard numbers
        autonChooser.addObject("Smart Auton", null);
        SmartDashboard.putData("autonChooser", autonChooser);
    }

    public void autonomousInit() {
            //The smart auton program has to be created at the begining of auton
            //That way, it gets the correct numbers from the smart dashboard
            //null is used as a placeholder for it
	if((CommandGroup) autonChooser.getSelected() == null){
		(new SmartAutonGroup()).start();
	} else {
                    //All of the other autons are stored in the object
                    //We just have to get the selected one and start it
                CommandGroup autonGroup = (CommandGroup) autonChooser.getSelected();
                autonGroup.start();
	}
            //Shift into high gear so that it is correct for teleop
        Shifter.shift(Shifter.Position.UP);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
            //Run the scheduler
        Scheduler.getInstance().run();
            //Graph the angle on the smart dashboard
        SmartDashboard.putNumber("angle", Angler.controller.getDistance());
    }

    public void disabledInit(){
            //Reset the encoders when disabled
        Drivetrain.resetEncoders();
    }
    
    public void teleopInit() {
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
            //Run commands
        Scheduler.getInstance().run();
            //Graph angle, speeds and throttle on the smart dashboard
        SmartDashboard.putNumber("angle", Angler.controller.getDistance());
            SmartDashboard.putNumber("avgSpeed", Drivetrain.getAvgSpeed());
            SmartDashboard.putNumber("leftSpeed", Drivetrain.getLeftAvgSpeed());
            SmartDashboard.putNumber("rightSpeed", Drivetrain.getRightAvgSpeed());
            SmartDashboard.putNumber("rot Throttle", Drivetrain.getRotationThrottle());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
            //Run test mode
        LiveWindow.run();
        Scheduler.getInstance().run();
    }
}
