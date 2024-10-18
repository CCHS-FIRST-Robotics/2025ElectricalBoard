package frc.robot.commands;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.Supplier;
import frc.robot.subsystems.arm.*;

public class PositionWithJoysticks extends Command{
    Arm arm;
    Supplier<Double> leftXSupplier;

    public PositionWithJoysticks(
        Arm arm,
        Supplier<Double> leftXSupplier
    ){
        addRequirements(arm);
        this.arm = arm;
        this.leftXSupplier = leftXSupplier;
    }
    
    @Override
    public void execute() {
        double leftX = leftXSupplier.get();
        
        arm.setPosition(Rotations.of(leftX));
    }
}