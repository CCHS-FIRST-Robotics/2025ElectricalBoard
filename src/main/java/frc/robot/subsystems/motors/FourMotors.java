package frc.robot.subsystems.motors;

import edu.wpi.first.wpilibj2.command.*;

public class FourMotors extends SubsystemBase{
    private Motor[] motors = new Motor[2];
    private boolean allMotorsOn = false;
    final double MAXVOLTAGE = 12;


    public FourMotors(Motor motor0, Motor motor1){
        motors[0] = motor0;
        motors[1] = motor1;
        //motors[2] = motor2;
        //motors[3] = motor3;
    }

    @Override
    public void periodic() {
        for(Motor motor : motors){
            motor.updateInputs();
        }
    }

    public void setMotorVoltage(int index, double volts){
        motors[index].setVoltage(Math.min(MAXVOLTAGE, volts));
    }

    public void setAllMotorVoltage(double volts){
        for(Motor motor : motors){
            motor.setVoltage(Math.min(MAXVOLTAGE, volts));
        }
    }

    public void toggleMotors(){
        setAllMotorVoltage(allMotorsOn ? 0 : 8);
    }
}