package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;

public class QuadraticRPM extends Command{
    FourMotors motors;
    int duration;
    double maxVoltage;
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
       double change = maxVoltage / (halfPeriodic * (1 / 0.02));

        if (((int) timePassed/ halfPeriodic) % 2 == 0) {
        totalVolts += change;
        } else {
            totalVolts -= change;
      }

    double maxTotalVoltage = (maxVoltage / 2) * (halfPeriodic / 0.02);
    double scaleVoltage = totalVolts/maxTotalVoltage;

    motors.run(scaleVoltage * maxVoltage);

    timePassed += 0.02;

    }
 }


