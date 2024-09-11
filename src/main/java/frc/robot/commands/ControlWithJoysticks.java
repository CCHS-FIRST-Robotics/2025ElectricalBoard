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
        
        motors.startMotor(0, applyPreferences(leftX));
        motors.startMotor(1, applyPreferences(leftY));
        motors.startMotor(2, applyPreferences(rightX));
        motors.startMotor(3, applyPreferences(rightY));
    }

    public double applyPreferences(double input){
        if(Math.abs(input) < Constants.ANALOG_DEADZONE){
            return 0; 
        }

        //square the input? But isn't the input between -1 and 1? Wouldn't the input just be decreasing then?
        //does signum just make sure its - or + for reverse direction if joystick is range
        // -1<=x<=0 || 0<=x<=1?
        return Math.pow(input, 2) * Math.signum(input) * 8;
    }
}