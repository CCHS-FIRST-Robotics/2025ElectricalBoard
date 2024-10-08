package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.GroupOfMotors;
import frc.robot.Constants;

public class LinearProfile extends Command{
    GroupOfMotors motors;
    int currentIterations = 0;
    int maxVoltage;
    int maxIterations;
    double duration;
    double currentVoltage = 0;
    double changeRate = 0;

    public LinearProfile(GroupOfMotors motors, double duration, int maxVoltage) {
        this.motors = motors;
        this.maxVoltage = maxVoltage;
        this.change = changeRate;
        this.maxIterations = duration / 50;
        this.duration = maxIterations;
    }
    
    @Override
    public void execute() {

        // calculate change
        if (currentVoltage == maxVoltage) {
            // Reached max voltage (level out)
            change = 0;
        } else if (currentIterations <= maxIterations / 3) {
            // Rising voltage ()
            change = maxVoltage / (maxIterations / 3);
        } else if (currentIterations >= ((maxIterations / 3) * 2)) {
            // Lowering voltage
            change = -(maxVoltage / (maxIterations / 3));
        }
        // Make sure the voltage will not exceed max or go below 0
        if (currentVoltage + change <= maxVoltage && currentVoltage + change >= 0) {
            currentVoltage += change;
            totalVolts = currentVoltage;
            motors.setAllMotorVoltage(totalVolts);
        } 
        currentIterations += 1; 
    }

    @Override
    public boolean isFinished(){
        return currentIterations >= maxIterations;
    }
}