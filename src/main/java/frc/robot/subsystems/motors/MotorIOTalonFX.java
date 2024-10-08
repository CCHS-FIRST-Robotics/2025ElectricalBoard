package frc.robot.subsystems.motors;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.*;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import edu.wpi.first.units.*;

public class MotorIOTalonFX implements MotorIO {
    private TalonFX motor;
    private final TalonFXConfiguration motorConfig = new TalonFXConfiguration();
    private final Slot0Configs slot0Config = motorConfig.Slot0;
    private final MotionMagicVoltage motorMotionMagicVoltage = new MotionMagicVoltage(0);
    private final MotionMagicConfigs motionMagicConfig = motorConfig.MotionMagic;


    private StatusSignal<Double> voltageSignal;
    private StatusSignal<Double> currentSignal;
    private StatusSignal<Double> positionSignal;
    private StatusSignal<Double> velocitySignal;
    private StatusSignal<Double> temperatureSignal;
    
    public MotorIOTalonFX(int id){
        motor = new TalonFX(id);
        motor.getConfigurator().apply(motorConfig);

        voltageSignal = motor.getMotorVoltage();
        currentSignal = motor.getStatorCurrent();
        positionSignal = motor.getPosition();
        velocitySignal = motor.getVelocity();
        temperatureSignal = motor.getDeviceTemp();

        slot0Config.kP = 0.1;
        slot0Config.kD = 0;
        slot0Config.kI = 0;

        slot0Config.kS = 0;
        slot0Config.kV = 0;
        slot0Config.kA = 0; 

        motionMagicConfig.MotionMagicCruiseVelocity = 90; // max rps of the motor (almost)
        motionMagicConfig.MotionMagicAcceleration = .5;
        // motionMagicConfig.MotionMagicJerk = 0;
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