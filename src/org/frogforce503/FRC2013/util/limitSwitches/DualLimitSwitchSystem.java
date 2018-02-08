package org.frogforce503.FRC2013.util.limitSwitches;


/**
 * Class to represent a system of two limit switches
 * @author Bryce Paputa
 */
public class DualLimitSwitchSystem implements LimitSwitchSystem{
        //Limit switch objects
    public NCLimitSwitch lowerLimitSwitch, upperLimitSwitch;
    
    /**
     * Creates new DualLimitSwitchSystem with given limit switches
     * @param lower Lower limit switch
     * @param upper Upper limit switch
     */
    public DualLimitSwitchSystem(NCLimitSwitch lower, NCLimitSwitch upper){
        lowerLimitSwitch = lower;
        upperLimitSwitch = upper;
    }
    
    /**
     * Get the current state of the system
     * @return The state of the system
     */
    public LimitSwitchState getLimitSwitchState(){
        boolean lower = lowerLimitSwitch.get(), upper = upperLimitSwitch.get();
        if(!lower && !upper){
            return LimitSwitchState.UNTRIGGERED;
        } else if(lower && !upper){
            return LimitSwitchState.LOWER;
        } else if (upper && !lower){
            return LimitSwitchState.UPPER;
        }
        return LimitSwitchState.ERROR;
    }
    
    /**
     * Says whether or not the mechanism can go up further
     * @return whether or not the mechanism can go up further
     */
    public boolean canGoUp(){
        LimitSwitchState state = getLimitSwitchState();
        return state == LimitSwitchState.LOWER || state == LimitSwitchState.UNTRIGGERED;
    }
    
    /**
     * Says whether or not the mechanism can go down further
     * @return whether or not the mechanism can go down further
     */
    public boolean canGoDown(){
        LimitSwitchState state = getLimitSwitchState();
        return state == LimitSwitchState.UPPER || state == LimitSwitchState.UNTRIGGERED;
    }
    
}
