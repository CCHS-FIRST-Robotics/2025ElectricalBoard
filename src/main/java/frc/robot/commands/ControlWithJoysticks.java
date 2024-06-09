package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.Supplier;

import frc.robot.Constants;
import frc.robot.subsystems.motors.Motors;

public class ControlWithJoysticks extends Command{
    Motors motors;
    Supplier<Double> leftXSupplier;
    Supplier<Double> leftYSupplier;
    Supplier<Double> rightXSupplier;
    Supplier<Double> rightYSupplier;

    
    public ControlWithJoysticks(
        Motors motors,
        Supplier<Double> leftXSupplier,
        Supplier<Double> leftYSupplier,
        Supplier<Double> rightXSupplier,
        Supplier<Double> rightYSupplier
    ){
        this.motors = motors;
        this.leftXSupplier = leftXSupplier;
        this.leftYSupplier = leftYSupplier;
        this.rightXSupplier = leftXSupplier;
        this.rightYSupplier = leftYSupplier;
    }
    
    @Override
    public void execute() {
        double leftX = leftXSupplier.get();
        double leftY = leftYSupplier.get();
        double rightX = leftXSupplier.get();
        double rightY = leftYSupplier.get();

        motors.startTalonFX(applyPreferences(leftX));
        motors.startSparkMax(applyPreferences(leftY));
        motors.startTalonSRX1(applyPreferences(rightX));
        motors.startTalonSRX2(applyPreferences(rightY));
    }

    public double applyPreferences(double input){
        if(Math.abs(input) < Constants.ANALOG_DEADZONE){
            return 0; 
        }
        return input * 12;
    }
}