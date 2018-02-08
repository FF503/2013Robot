package org.frogforce503.FRC2013.util.limitSwitches;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.parsing.IInputOutput;



/**
 * Code for normally closed limit switches
 * @author Bryce Paputa
 */
public class NCLimitSwitch extends DigitalSource implements IInputOutput{
    protected final DigitalInput input;
    
    /**
     * Creates a new NCLimitSwitch with given channel. Defaults to module #1
     * @param channel The DSC channel the switch is plugged into
     */
    public NCLimitSwitch(int channel){
        this(1, channel);
    }
    
    /**
     * Creates a new NCLimitSwitch with given channel and module number
     * @param moduleNumber The cRIO slot the DSC is plugged into
     * @param channel The DSC channel the switch is plugged into
     */
    public NCLimitSwitch(int moduleNumber, int channel){
        input = new DigitalInput(moduleNumber, channel);
    }
    
    /**
     * Releases the DIO channel so something else can use it
     */
    public void free(){
        input.free();
    }
    
    /**
     * Says whether or not the switch is pressed
     * Should be inverted. TODO: INVERT AND TEST
     * @return The state of the switch
     */
    public boolean get(){
        return input.get();
    }
    
    public int getChannel() {
        return input.getChannel();
    }

    public int getChannelForRouting() {
        return input.getChannelForRouting();
    }

    public int getModuleForRouting() {
        return input.getModuleForRouting();
    }

    public boolean getAnalogTriggerForRouting() {
        return input.getAnalogTriggerForRouting();
    }
    
    public void requestInterrupts(/*tInterruptHandler*/Object handler, Object param){
        input.requestInterrupts(handler, param);
    }
    
    public void requestInterrupts(){
        input.requestInterrupts();
    }

    public void setUpSourceEdge(boolean rising, boolean falling){
        input.setUpSourceEdge(rising, falling);
    }
}
