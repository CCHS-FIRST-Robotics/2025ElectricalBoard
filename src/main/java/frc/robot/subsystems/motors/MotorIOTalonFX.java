package frc.robot.subsystems.motors;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.*;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.configs.Slot0Configs;
import edu.wpi.first.units.*;

public class MotorIOTalonFX implements MotorIO {
    private TalonFX motor;
    private final TalonFXConfiguration motorConfig = new TalonFXConfiguration();
    private final MotionMagicVoltage motorMotionMagicVoltage = new MotionMagicVoltage(0);
    private final Slot0Configs PID = motorConfig.Slot0;

    private StatusSignal<Double> voltageSignal;
    private StatusSignal<Double> currentSignal;
    private StatusSignal<Double> positionSignal;
    private StatusSignal<Double> velocitySignal;
    private StatusSignal<Double> temperatureSignal;
    
    public MotorIOTalonFX(int id){
        motor = new TalonFX(id);

        voltageSignal = motor.getMotorVoltage();
        currentSignal = motor.getStatorCurrent();
        positionSignal = motor.getPosition();
        velocitySignal = motor.getVelocity();
        temperatureSignal = motor.getDeviceTemp();

        PID.kP = 0.1;
        PID.kD = 0;
        PID.kI = 0;

        PID.kS = 0;
        PID.kV = 0;
        PID.kA = 0;
    }

    @Override
    public void setVoltage(Measure<Voltage> volts) {
        motor.setVoltage(volts.in(Volts));
    }

    @Override
    public void setPosition(Measure<Angle> radians){
        motor.setControl(motorMotionMagicVoltage.withPosition(radians.in(Rotations)).withSlot(0));
    }

    @Override
    public void updateInputs(MotorIOInputs inputs) {
        BaseStatusSignal.refreshAll(voltageSignal, currentSignal, positionSignal, velocitySignal, temperatureSignal);

        inputs.motorCurrent = currentSignal.getValue();
        inputs.motorVoltage = voltageSignal.getValue();
        inputs.motorPosition = positionSignal.getValue();
        inputs.motorVelocity = velocitySignal.getValue();
        inputs.motorTemperature = temperatureSignal.getValue();
    }
}