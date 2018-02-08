package org.frogforce503.FRC2013.util;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.parsing.IDeviceController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import org.frogforce503.FRC2013.OI;

/**
 * Class that allows you to control two talons at once. Also provides ramping.
 * @author Bryce Paputa
 */
public class CoupledTalons implements SpeedController, IDeviceController, PIDOutput, LiveWindowSendable{
    private final Talon talon1, talon2;
    private final int inversionFactor;
    private double slowDownMaxDelta, speedUpMaxDelta;
    
    /**
     * Constructor that takes just the ports and whether or not to invert the outputs. 
     * Assumes that the motors are controlled by the first DIO module.
     * @param port1    port of the first motor
     * @param port2    port of the second motor
     * @param inverted whether or not to invert the outputs.
     * @param maxDelta maximum acceleration 
     */
    public CoupledTalons(int port1, int port2, boolean inverted, double slowDownMaxDelta, double speedUpMaxDelta){
        this(1, port1, 1, port2, inverted, slowDownMaxDelta, speedUpMaxDelta);
    }
    
    /**
     * Constructor that takes the ports, the slots, and whether or not to invert the outputs. 
     * @param slot1    slot of the first motor
     * @param port1    port of the first motor
     * @param slot2    slot of the second motor
     * @param port2    port of the second motor
     * @param inverted whether or not to invert the outputs.
     * @param maxDelta maximum acceleration 
     */
    public CoupledTalons(int slot1, int port1, int slot2, int port2, boolean inverted, double slowDownMaxDelta, double speedUpMaxDelta){
            //Just take the parameters and pass them to both talons
        talon1 = new Talon(slot1, port1);
        talon2 = new Talon(slot2, port2);
            //store inverted as a number that will be multiplied with the input to get the output
        inversionFactor = inverted? -1: 1;
        
        this.slowDownMaxDelta = slowDownMaxDelta;
        this.speedUpMaxDelta = speedUpMaxDelta;
    }
    
    /**
     * Sets the speed.
     * @deprecated don't use, just here for completeness' sake.
     * @param speed     speed
     * @param syncGroup deprecated parameter
     */
    public void set(double speed, byte syncGroup) {  
            //output the speed, inverted if necessary
        _set(speed * inversionFactor);    
    }
    
    /**
     * Sets the speed.
     * @param speed the speed
     */
    public void set(double speed){
            //output the speed, inverted if necessary
        _set(speed * inversionFactor);
    }
    
    /**
     * Gets the last speed sent to the controllers.
     * @return the last outputted speed.
     */
    public double get() {
            //should be equal to talon2.get()
        return talon1.get();
    }
    
    /**
     * Same as {@link #set(double)}, just here to implement PIDOutput.
     * @param output output from the PID
     */
    public void pidWrite(double output) {
        _set(output * inversionFactor);
    }
    
    /**
     * Disables the controllers.
     */
    public void disable(){
        talon1.disable();
        talon2.disable();
    }
    
    private static double lastSpeed = 0;
    
    private void _set(double speed){
        if(!OI.powerButton.get()){
            
            double delta = speed - lastSpeed;
            double maxDelta;
            if(delta/*Math.abs(speed)-Math.abs(lastSpeed)*/> 0){
                maxDelta = speedUpMaxDelta;
            } else {
                maxDelta = slowDownMaxDelta;
            }
            
            if(delta > maxDelta){
                delta = maxDelta;
            } else if(delta < - maxDelta){
                delta = -maxDelta;
            }
            talon1.set(lastSpeed + delta);
            talon2.set(lastSpeed + delta);
            lastSpeed = speed;      
            SmartDashboard.putNumber("drive Speed", maxDelta);
        } else {
            
            talon1.set(-speed);
            talon2.set(-speed);
        }
        
    }

    public void updateTable() {
        talon1.updateTable();
        talon2.updateTable();
    }

    public void startLiveWindowMode() {
        talon1.startLiveWindowMode();
        talon2.startLiveWindowMode();
    }

    public void stopLiveWindowMode() {
        talon1.stopLiveWindowMode();
        talon2.stopLiveWindowMode();
    }

    public void initTable(ITable subtable) {
        talon1.initTable(subtable);
        talon2.initTable(subtable);
    }

    public ITable getTable() {
        return talon1.getTable();
    }

    public String getSmartDashboardType() {
        return talon1.getSmartDashboardType();
    }
}
