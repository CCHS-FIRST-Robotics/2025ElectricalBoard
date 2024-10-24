package frc.robot.subsystems.motors;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.*;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import edu.wpi.first.units.*;

public class MotorIOTalonFX implements MotorIO {
    private final TalonFX motor;
    private final TalonFXConfiguration motorConfig = new TalonFXConfiguration();
    private final Slot0Configs PIDF = motorConfig.Slot0;
    private final MotionMagicVoltage motorMotionMagicVoltage = new MotionMagicVoltage(0);
    private final MotionMagicConfigs motionMagicConfig = motorConfig.MotionMagic;

    private final double kP = 20;
    private final double kI = 0;
    private final double kD = 0;
    private final double kS = 0;
    private final double kV = 0;
    private final double kA = 0;

    private StatusSignal<Double> voltageSignal;
    private StatusSignal<Double> currentSignal;
    private StatusSignal<Double> positionSignal;
    private StatusSignal<Double> velocitySignal;
    private StatusSignal<Double> temperatureSignal;
    
    public MotorIOTalonFX(int id){
        motor = new TalonFX(id);

        PIDF.kP = kP;
        PIDF.kI = kI;
        PIDF.kD = kD;
        PIDF.kS = kS;
        PIDF.kV = kV;
        PIDF.kA = kA;

        motionMagicConfig.MotionMagicCruiseVelocity = 100; // motor max rps
        motionMagicConfig.MotionMagicAcceleration = 1;
        motionMagicConfig.MotionMagicJerk = 1;

        motor.getConfigurator().apply(motorConfig);

        voltageSignal = motor.getMotorVoltage();
        currentSignal = motor.getStatorCurrent();
        positionSignal = motor.getPosition();
        velocitySignal = motor.getVelocity();
        temperatureSignal = motor.getDeviceTemp();
    }

    @Override
    public void setVoltage(Measure<Voltage> volts) {
        motor.setVoltage(volts.in(Volts));
    }

    @Override
    public void setPosition(Measure<Angle> position){
        motor.setControl(motorMotionMagicVoltage.withPosition(position.in(Rotations)).withSlot(0));
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