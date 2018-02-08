package org.frogforce503.FRC2013.util.limitSwitches;


/**
 * Enum to represent the state of limit switches 
 * @author Bryce Paputa
 */
public final class LimitSwitchState {
        //Both are "pressed", probably a wiring issue
    private static final int ERROR_VAL = -1;
        //Neither are pressed
    private static final int UNTRIGGERED_VAL = 0;
        //The lower switch is pressed
    private static final int LOWER_VAL = 1;
        //The upper switch is pressed
    private static final int UPPER_VAL = 2;
    
        //int to store the state in
    private final int value;

    private LimitSwitchState(int val) {
        value = val;
    }
    
    public static final LimitSwitchState ERROR = new LimitSwitchState(ERROR_VAL);
    public static final LimitSwitchState UNTRIGGERED = new LimitSwitchState(UNTRIGGERED_VAL);
    public static final LimitSwitchState LOWER = new LimitSwitchState(LOWER_VAL);
    public static final LimitSwitchState UPPER = new LimitSwitchState(UPPER_VAL);    
}
