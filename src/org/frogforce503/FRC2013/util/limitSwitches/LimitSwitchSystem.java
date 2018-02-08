package org.frogforce503.FRC2013.util.limitSwitches;


/**
 * Interface for reading limit switches and making sure you don't go past them.
 * @author Bryce Paputa
 */
public interface LimitSwitchSystem {
    
    /**
     * Tells whether or not the mechanism can go up.
     * @return true if the mechanism can go up more
     */
    public boolean canGoUp();
    
    /**
     * Tells whether of not the mechanism can go down.
     * @return true if the mechanism can go down more
     */
    public boolean canGoDown();
    
}
