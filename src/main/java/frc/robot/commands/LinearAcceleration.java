package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;
import frc.robot.Constants;

public class LinearAcceleration extends Command{
    FourMotors motors;
    int duration;
    int maxVoltage;
    int quarterWavelength;
    
    double t = 0; 
    double totalVolts = 0;

    public LinearAcceleration(FourMotors motors, int duration, int maxVoltage, int quarterWavelength){
        addRequirements(motors);
        this.motors = motors;
        this.duration = duration;
        this.maxVoltage = maxVoltage;
        this.quarterWavelength = quarterWavelength;
    }
    
    @Override
    public void execute() {
        double change = maxVoltage / (quarterWavelength * (1 / Constants.PERIOD));
        if (((int) t / quarterWavelength) % 2 == 0) { // increasing
            totalVolts += change;
        } else { // decreasing
            totalVolts -= change;
        }

        motors.setAllMotorVoltage(totalVolts);
        t += Constants.PERIOD; 

        // double linear = ((double) (((int) t / 10) % 2 == 0 ? t % 10 : 10 - t % 10) / 10d) * maxVoltage;
    }

    @Override
    public boolean isFinished(){
        return t > duration;
    }
}