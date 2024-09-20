package frc.robot.subsystems.motors;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.*;

public class MotorTalonSRX extends SubsystemBase{
    private int motorID;
    private TalonSRX motor;
    public MotorTalonSRX(int ID){
        motorID = ID;
        motor = new TalonSRX(motorID);
    }
    public void setVoltage(double volts){
        motor.set(TalonSRXControlMode.PercentOutput,volts / 12);
    }
}


