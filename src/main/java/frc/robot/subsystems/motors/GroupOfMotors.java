package frc.robot.subsystems.motors;

import edu.wpi.first.wpilibj2.command.*;
import java.util.ArrayList;

public class GroupOfMotors extends SubsystemBase{
    private ArrayList<Motor> motors;
    private boolean allMotorsOn = false;

    public GroupOfMotors(){
        motors = new ArrayList<Motor>();
    }

    public void addMotor(Motor motor){
        motors.add(motor);
    }

    @Override
    public void periodic() {
        for(Motor motor : motors){
            motor.updateInputs();
        }
    }

    public void setMotorVoltage(int index, double volts){
        motors.get(index).setVoltage(volts);
    }

    public void setMotorPosition(int index, double radians){
        motors.get(index).setPosition(radians);
    }

    public void setAllMotorVoltage(double volts){
        for(Motor motor : motors){
            motor.setVoltage(volts);
        }
    }

    public void toggleMotors(){
        setAllMotorVoltage(allMotorsOn ? 0 : 8);
        allMotorsOn = !allMotorsOn;
    }
}