package org.frogforce503.FRC2013.pneumatics;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 * @author Bryce Paputa
 */
public class StartCompressorCommand extends Command {
    

    public StartCompressorCommand() {
        requires(Compressor.getInstance());
    }

    protected void initialize() {
        Compressor.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
