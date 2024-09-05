package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.*;

public class UpDown extends Command{
    FourMotors motors;
     public UpDown(
        FourMotors motors,
        Supplier<Double> leftXSupplier,
        Supplier<Double> leftYSupplier,
        Supplier<Double> rightXSupplier,
        Supplier<Double> rightYSupplier
    ){
        addRequirements(motors);
        this.motors = motors;
    }

    

    @Override
    public void execute() {

        motors.startMotor(0, 12);

    }

}
