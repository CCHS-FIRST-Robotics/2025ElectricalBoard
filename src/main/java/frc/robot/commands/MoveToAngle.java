package frc.robot.commands;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;
import edu.wpi.first.wpilibj2.command.Command;
import static edu.wpi.first.units.Units.*;

public class MoveToAngle extends Command{
    Measure<Angle> angle;
    public final PIDController pidController;
    public final TalonFX motor;

    public MoveToAngle(Measure<Angle> angle, PIDController pidController, TalonFX motor) {
        this.angle = angle;
        this.pidController = pidController;
        this.motor = motor;
    }

    @Override
    public void execute() {
        pidController.setSetpoint(angle.in(Rotations));
        double currentPosition = motor.getPosition().getValue();
        motor.setVoltage(pidController.calculate(currentPosition/(2 * Math.PI), angle.in(Rotations)));
    }

    @Override
    public boolean isFinished() {
        System.out.println("Thing Finished");
        return pidController.atSetpoint();
    }
}
