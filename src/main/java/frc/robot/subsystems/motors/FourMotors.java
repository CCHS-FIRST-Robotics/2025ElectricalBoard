package frc.robot.subsystems.motors;

import edu.wpi.first.wpilibj2.command.*;

import java.sql.Driver;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;

public class FourMotors extends SubsystemBase{
    private Motor[] motors = new Motor[4];

    public FourMotors(Motor motor0, Motor motor1, Motor motor2, Motor motor3){
        motors[0] = motor0;
        motors[1] = motor1;
        motors[2] = motor2;
        motors[3] = motor3;
    }

    @Override
    public void periodic() {
        for(Motor motor : motors){
            motor.updateInputs();
        }
    }

    public void startMotor(int index, double volts) {
        motors[index].setVoltage(volts);
    }

    public void run(double volts){
        for(Motor motor : motors){
            motor.setVoltage(volts);
        }
    }

    public void stopMotors() {
        for (Motor motor : motors) {
            motor.setVoltage(0);
        }
    }

    public void logVoltage() {
        for (Motor motor : motors) {
            motor.getVoltage();
        }
    }
}