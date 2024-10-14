package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.*; // ! TEMP

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.*;

public class Arm extends SubsystemBase{
    private final ArmIO io;
    private final ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();
    boolean motorOn = false; 

    public Arm(ArmIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("arm", inputs);
    }

    // ! TEMP
    public void toggleMotor(){
        io.setVoltage(motorOn ? Volts.of(0) : Volts.of(8));
        motorOn = !motorOn;
    }

    public void setPosition(Measure<Angle> position) {
        io.setPosition(position);
    }
}