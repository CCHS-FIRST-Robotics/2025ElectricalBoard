package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.*;

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
        Logger.processInputs("Arm", inputs);
    }

    public void setVoltage(Measure<Voltage> volts){
        io.setVoltage(volts);
    }

    public void setPosition(Measure<Angle> position) {
        io.setPosition(position);
    }

    public void toggleMotor(){
        setVoltage(motorOn ? Volts.of(0) : Volts.of(8));
        motorOn = !motorOn;
    }
}