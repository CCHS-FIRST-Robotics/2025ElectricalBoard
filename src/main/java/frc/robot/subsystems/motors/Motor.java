package frc.robot.subsystems.motors;

import org.littletonrobotics.junction.Logger;

public class Motor {
    private MotorIO io;
    private MotorIOInputsAutoLogged inputs = new MotorIOInputsAutoLogged();

    public Motor(MotorIO io) {
        this.io = io;
    }

    public void setVoltage(double volts){
        io.setVoltage(volts);
    }

    public void setPosition(double radians){
        io.setPosition(radians);
    }

    public void updateInputs(){
        io.updateInputs(inputs);
        Logger.processInputs("motor", inputs);
    }
}