package frc.robot.subsystems.motors;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;

public class MotorIOTalonFX implements MotorIO {
    private TalonFX motor;
    private final PIDController pidController;

    public MotorIOTalonFX(int id){
        motor = new TalonFX(id);

        pidController = new PIDController(1.0, 0.0, 0.1); //tune later on
        pidController.setSetpoint(90); //move to 90 deg
        pidController.setTolerance(3); //3 deg off is fine?
    }

    @Override
    public void setVoltage(double volts) {
        double percentOutput = volts / 12.0;
        motor.set(TalonFXControlMode.PercentOutput, percentOutput);
    }

    @Override
    public void getVoltage() {
        motor.getMotorOutputVoltage();
    }

    @Override
    public void updateInputs(MotorIOInputs inputs) {
        inputs.motorCurrent = motor.getStatorCurrent();
        inputs.motorVoltage = motor.getMotorOutputVoltage();
        inputs.motorPosition = motor.getSelectedSensorPosition();
        inputs.motorVelocity = motor.getSelectedSensorVelocity();
        inputs.motorTemperature = motor.getTemperature();
    }

    public void moveToAngle(double angle) {
        pidController.setSetpoint(angle);
        double currentPos = motor.getSelectedSensorPosition();
        double output = pidController.calculate(currentPos);

        motor.set(TalonFXControlMode.PercentOutput, output);

        if (pidController.atSetpoint()) {
            motor.set(TalonFXControlMode.PercentOutput, 0);
        }

     
    }
}
