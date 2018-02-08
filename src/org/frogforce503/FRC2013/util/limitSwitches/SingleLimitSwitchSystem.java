package org.frogforce503.FRC2013.util.limitSwitches;


/**
 * Class that represents a system with one limit switch, assumed to be the lower one
 * @author Bryce Paputa
 */
public class SingleLimitSwitchSystem implements LimitSwitchSystem{
        //The switch
    public final NCLimitSwitch lowerLimitSwitch;
    
    /**
     * Creates a new SingleLimitSwitchSystem with given switch
     * @param lower The lower limit switch
     */
    public SingleLimitSwitchSystem(NCLimitSwitch lower){
        lowerLimitSwitch = lower;
    }
    
    /**
     * Gets the state of the system
     * @return the state of the system
     */
    public LimitSwitchState getLimitSwitchState(){
        boolean lower = lowerLimitSwitch.get();
        if(!lower){
            return LimitSwitchState.UNTRIGGERED;
        } else if(lower){
            return LimitSwitchState.LOWER;
        }
        return LimitSwitchState.ERROR;
    }
    
    
    /**
     * Says whether or not the mechanism can go up, since the system has no
     * upper limit, it always can
     * @return true
     */
    public boolean canGoUp(){
        return true;
    }
    
    /**
     * Says whether or not the mechanism can go down
     * @return whether or not the mechanism can go down
     */
    public boolean canGoDown(){
        LimitSwitchState state = getLimitSwitchState();
        return state == LimitSwitchState.UNTRIGGERED;
    }
}
