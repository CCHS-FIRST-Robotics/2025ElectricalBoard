package frc.robot.commands;
import org.littletonrobotics.junction.Logger;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;
import edu.wpi.first.wpilibj2.command.Command;
import static edu.wpi.first.units.Units.*;

public class MoveToAngle extends Command{
    public final TalonFX motor;
    public final PIDController pidController;
    Measure<Angle> angle;
    
    public MoveToAngle(TalonFX motor, PIDController pidController, Measure<Angle> angle) {
        this.motor = motor;
        this.pidController = pidController;
        this.angle = angle;
    }

    @Override
    public void execute() {
        // pidController.setSetpoint(angle.in(Rotations));
        double currentPosition = motor.getPosition().getValue();
        double pidvalue = pidController.calculate(currentPosition, angle.in(Rotations));
        motor.setVoltage(pidvalue);
        Logger.recordOutput("Motor position (rotations)", currentPosition);
        Logger.recordOutput("PID Val", pidvalue);
    }

    // @Override
    // public boolean isFinished() {
    //     System.out.println(Math.abs(motor.getPosition().getValue() - angle.in(Rotations))<0.1);
    //     return Math.abs(motor.getPosition().getValue() - angle.in(Rotations))<0.1;
    // }
}