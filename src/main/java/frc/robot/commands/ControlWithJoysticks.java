package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.Supplier;
import frc.robot.subsystems.motors.FourMotors;
import frc.robot.Constants;

public class ControlWithJoysticks extends Command{
    FourMotors motors;
    Supplier<Double> leftXSupplier;
    Supplier<Double> leftYSupplier;
    Supplier<Double> rightXSupplier;
    Supplier<Double> rightYSupplier;

    
    public ControlWithJoysticks(
        FourMotors motors,
        Supplier<Double> leftXSupplier,
        Supplier<Double> leftYSupplier,
        Supplier<Double> rightXSupplier,
        Supplier<Double> rightYSupplier
    ){
        addRequirements(motors);
        this.motors = motors;
        this.leftXSupplier = leftXSupplier;
        this.leftYSupplier = leftYSupplier;
        this.rightXSupplier = rightXSupplier;
        this.rightYSupplier = rightYSupplier;
    }
    
    @Override
    public void execute() {
        double leftX = leftXSupplier.get();
        double leftY = leftYSupplier.get();
        double rightX = rightXSupplier.get();
        double rightY = rightYSupplier.get();
        
        motors.setMotorVoltage(0, applyPreferences(leftX));
        motors.setMotorVoltage(1, applyPreferences(leftY));
        motors.setMotorVoltage(2, applyPreferences(rightX));
        motors.setMotorVoltage(3, applyPreferences(rightY));
    }

    public double applyPreferences(double input){
        if(Math.abs(input) < Constants.ANALOG_DEADZONE){
            return 0; 
        }
        return input * 8;


        if (Math.abs(input) < deadzone) {
            return 0;
    }
    return Math.pow(input, 2) * Math.signum(input);
    }
}