package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.units.*;

public class ArmIOSparkMax implements ArmIO {
    private CANSparkMax motor;
    private RelativeEncoder encoder;
    private final SparkPIDController PIDF;

    public ArmIOSparkMax(int id){
        motor = new CANSparkMax(id, MotorType.kBrushed);
        encoder = motor.getEncoder(); 
        PIDF = motor.getPIDController();

        PIDF.setP(8, 0);
        PIDF.setD(0, 0);
        PIDF.setI(0, 0);
        PIDF.setFF(0, 0);
    }

    @Override
    public void setPosition(Measure<Angle> position){
        PIDF.setReference(
            position.in(Rotations),
            CANSparkMax.ControlType.kPosition,
            0
        );
    }

    @Override
    public void updateInputs(ArmIOInputs inputs) {
        inputs.motorCurrent = motor.getOutputCurrent();
        inputs.motorVoltage = motor.getBusVoltage();
        inputs.motorPosition = encoder.getPosition();
        inputs.motorVelocity = encoder.getVelocity();
        inputs.motorTemperature = motor.getMotorTemperature();
    }
}