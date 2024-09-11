package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;
import frc.robot.Constants;

public class QuadraticRPM extends Command{
    FourMotors motors;
    int duration;
    int maxVoltage;
    int halfPeriodic;
    double timePassed = 0; 
    double totalVolts = 0;

    public QuadraticRPM(FourMotors motors, int duration, int maxVoltage, int halfPeriodic){
        addRequirements(motors);
        this.motors = motors;
        this.duration = duration;
        this.maxVoltage = maxVoltage;
        this.halfPeriodic = halfPeriodic;
    }
    
    @Override
    public void execute() {
       
    }
}

