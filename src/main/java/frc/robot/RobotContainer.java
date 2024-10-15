package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.units.*;
import static edu.wpi.first.units.Units.*;

public class RobotContainer {
    private final CommandXboxController transmitter = new CommandXboxController(Constants.Controller_PORT);
    private final TalonFX motor = new TalonFX(0);
    private final PIDController pidController = new PIDController(20, 0.0, 0.1);
    Measure<Angle> targetAngle = Radians.of(Math.PI / 2); //pi/2 rad = 90 deg

    public RobotContainer() {
        pidController.setTolerance(5, 10);
        configureBindings();
    }

    private void configureBindings() {
        Trigger buttonB = transmitter.b();
        buttonB.onTrue(new InstantCommand(() -> moveToAngle(targetAngle.in(Rotations))));
    }

    public void moveToAngle(double angle) {
        pidController.setSetpoint(angle);
        double currentPosition = motor.getPosition().getValue();
        //double output = pidController.calculate(currentPosition);

        //output = MathUtil.clamp(output, -1, 1);
        while (!pidController.atSetpoint()) {
             motor.setVoltage(pidController.calculate(currentPosition/(2 * Math.PI), angle));
        }

        }
       
    public void periodic() {
        if (pidController.atSetpoint()) {
            motor.set(0);
        }
    }
}
