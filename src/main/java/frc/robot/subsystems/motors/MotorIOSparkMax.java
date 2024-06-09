package frc.robot.subsystems.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class MotorIOSparkMax implements MotorIO {
    CANSparkMax motor;
    RelativeEncoder encoder;

    public MotorIOSparkMax(int id){
        motor = new CANSparkMax(id, MotorType.kBrushless);
        encoder = motor.getEncoder(); 
    }
    
    @Override
    public void setVoltage(double volts) {
        motor.setVoltage(volts);
    }

    @Override
    public void updateInputs(MotorIOInputs inputs) {
        inputs.motorCurrent = motor.getOutputCurrent();
        inputs.motorVoltage = motor.getBusVoltage();
        inputs.motorPosition = encoder.getPosition();
        inputs.motorVelocity = encoder.getVelocity();
        inputs.motorTemperature = motor.getMotorTemperature();
    }
}