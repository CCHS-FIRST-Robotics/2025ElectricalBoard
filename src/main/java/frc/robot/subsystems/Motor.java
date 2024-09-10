package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.*;

public class Motor {
    private int motorID;
    private TalonSRX motor;
    public Motor(int motorID){
        this.motorID = motorID;
        motor = new TalonSRX(motorID);
    }
    public void setVoltage(double volts){
        motor.set(TalonSRXControlMode.PercentOutput,volts / 12);
    }
}


