package frc.robot.subsystems.motors;

import edu.wpi.first.units.*;

public class Motor {
    private final MotorIO io;
    int index;

    public Motor(MotorIO io, int index) {
        this.io = io;
        this.index = index;
    }

    public void setVoltage(Measure<Voltage> volts){
        io.setVoltage(volts);
    }

    public void setPosition(Measure<Angle> position){
        io.setPosition(position);
    }
}