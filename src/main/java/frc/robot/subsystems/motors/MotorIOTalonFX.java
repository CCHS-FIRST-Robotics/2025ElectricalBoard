package frc.robot.subsystems.motors;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;

public class MotorIOTalonFX implements MotorIO {
    private TalonFX motor;
    private final PIDController pidController;
    private StatusSignal<Double> voltageSignal;
    private StatusSignal<Double> currentSignal;
    private StatusSignal<Double> positionSignal;
    private StatusSignal<Double> velocitySignal;
    private StatusSignal<Double> temperatureSignal;

    public MotorIOTalonFX(int id){
        motor = new TalonFX(id);

        //talon FX has built in encoder right? So I dont needa define an encoder?
        pidController = new PIDController(1.0, 0.0, 0.1); //tune later on
        pidController.setSetpoint(90); //move to 90 deg
        pidController.setTolerance(3); //3 deg off is fine?

        voltageSignal = motor.getMotorVoltage();
        currentSignal = motor.getStatorCurrent();
        positionSignal = motor.getPosition();
        velocitySignal = motor.getVelocity();
        temperatureSignal = motor.getDeviceTemp();
    }

    @Override
    public void setVoltage(double volts) {
        motor.set(volts);
    }

    @Override
    public void getVoltage() {
        motor.getMotorVoltage();
    }

    @Override
    public void updateInputs(MotorIOInputs inputs) {
        BaseStatusSignal.refreshAll(voltageSignal, currentSignal, positionSignal, velocitySignal, temperatureSignal);

        inputs.motorVoltage = voltageSignal.getValue();
        inputs.motorCurrent = currentSignal.getValue();
        inputs.motorPosition = positionSignal.getValue();
        inputs.motorVelocity = velocitySignal.getValue();
        inputs.motorTemperature = temperatureSignal.getValue();
    }

    public void moveToAngle(double angle) {
        pidController.setSetpoint(angle);
        double currentPos = positionSignal.getValue();
        double output = pidController.calculate(currentPos);

        motor.set(output);

        if (pidController.atSetpoint()) {
            motor.set(0);
        }
     
    }
}
