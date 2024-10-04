package frc.robot.subsystems.motors;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.units.*;

public class Motor {
    private MotorIO io;
    private MotorIOInputsAutoLogged inputs = new MotorIOInputsAutoLogged();

    public Motor(MotorIO io) {
        this.io = io;
    }

    public void setVoltage(Measure<Voltage> volts){
        io.setVoltage(volts);
    }

    public void setPosition(Measure<Angle> radians){
        io.setPosition(radians);
    }

    public void updateInputs(){
        io.updateInputs(inputs);
        Logger.processInputs("motor", inputs);
    }
}