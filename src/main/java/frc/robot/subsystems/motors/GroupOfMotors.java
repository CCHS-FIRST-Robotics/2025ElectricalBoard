package frc.robot.subsystems.motors;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.wpilibj2.command.*;
import java.util.ArrayList;
import edu.wpi.first.units.*;

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

    public void setMotorVoltage(int index, Measure<Voltage> volts){
        motors.get(index).setVoltage(volts);
    }

    public void setMotorPosition(int index, Measure<Angle> radians){
        motors.get(index).setPosition(radians);
    }

    public void setAllMotorVoltage(Measure<Voltage> volts){
        for(Motor motor : motors){
            motor.setVoltage(volts);
        }
    }

    public void toggleMotors(){
        setAllMotorVoltage(allMotorsOn ? Volts.of(0) : Volts.of(8));
        allMotorsOn = !allMotorsOn;
    }
}