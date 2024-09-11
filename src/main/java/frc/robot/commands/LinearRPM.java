package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;

public class LinearRPM extends Command{
    FourMotors motors;
    int duration;
    int maxVoltage;
    int halfPeriodic;
    double timePassed = 0;
    double totalVolts = 0;

    public LinearRPM(FourMotors motors, int duration, int maxVoltage, int halfPeriodic){
        addRequirements(motors);
        this.motors=motors;
        this.duration = duration;
        this.maxVoltage = maxVoltage;
        this.halfPeriodic = halfPeriodic;
    }
    
    @Override
    public void execute() {
        double change = maxVoltage / (halfPeriodic * 1/0.02);
        if (((int) timePassed / halfPeriodic) % 2 == 0) {
            totalVolts += change;
        }
        totalVolts -= change;

        motors.run(totalVolts);
        motors.logVoltage();
        timePassed += 0.02;
    }
}