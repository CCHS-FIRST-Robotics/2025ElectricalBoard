package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;
import frc.robot.Constants;

public class QuadraticProfile extends Command{
    FourMotors motors;
    int duration;
    int maxVoltage;
    int quarterWavelength;
    
    double t = 0; 
    double change = 0;
    double totalVolts = 0;

    public QuadraticProfile(FourMotors motors, int duration, int maxVoltage, int quarterWavelength){
        addRequirements(motors);
        this.motors = motors;
        this.duration = duration;
        this.maxVoltage = maxVoltage;
        this.quarterWavelength = quarterWavelength;
    }
    
    @Override
    public void execute() {
        double changeChange = maxVoltage / (quarterWavelength * (1 / Constants.PERIOD));
        if (((int) t / quarterWavelength) % 2 == 0) { // increasing
            change += changeChange;
            totalVolts += change;
        } else { // decreasing
            change -= changeChange;
            totalVolts -= change;
        }

        /**
         * to normalize, you want the totalVolts at quarterWavelength
         * which is the average change(maxVoltage/2) times the number of 20ms in 10 seconds(500)
         * 6 * 500 = 3000
         */
        int maxTotalVolts = (maxVoltage/2) * (int)(quarterWavelength / Constants.PERIOD);
        motors.setAllMotorVoltage((totalVolts / maxTotalVolts) * maxVoltage);

        t += 0.02; 
    }

    @Override
    public boolean isFinished(){
        return t > duration;
    }
}