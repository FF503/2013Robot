package org.frogforce503.FRC2013.util.controllers;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frogforce503.FRC2013.util.limitSwitches.LimitSwitchSystem;



/**
 *
 * @author Bryce Paputa
 */
public class SimpleController {
    private final SpeedController motor;
    public final LimitSwitchSystem limitSwitchSystem;
    
    public SimpleController(SpeedController motor, LimitSwitchSystem limitSwitchSystem){
        this.motor = motor;
        this.limitSwitchSystem = limitSwitchSystem;
    }
    
    public boolean goUp(double speed){
        if(limitSwitchSystem.canGoUp()){
            
            SmartDashboard.putNumber("simple controller speed", Math.abs(speed));
            motor.set(Math.abs(speed));
            return false;
        } else {
            goNoWhere();
            return true;
        }
    }
    
    public boolean goDown(double speed){
        if(limitSwitchSystem.canGoDown()){
            SmartDashboard.putNumber("simple controller speed", -Math.abs(speed));
            motor.set(-Math.abs(speed));
            return false;
        } else {
            goNoWhere();
            return true;
        }
    }
    
    public void goNoWhere(){
        motor.set(0);
    }
    
    public void set(double speed){
        if(speed >=0){
            goUp(speed);
        } else {
            goDown(speed);
        }
    }
    
    public void manaulSet(double speed){
        motor.set(speed);
    }
}
