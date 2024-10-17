package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.units.*;
import static edu.wpi.first.units.Units.*;
import frc.robot.commands.MoveToAngle;

public class RobotContainer {
    private final CommandXboxController transmitter = new CommandXboxController(Constants.Controller_PORT);
    private final TalonFX motor;
    private final PIDController pidController;
    Measure<Angle> targetAngle = Radians.of(Math.PI / 2); //pi/2 rad = 90 deg

    public RobotContainer() {
        pidController = new PIDController(15, 0, 0);
        pidController.setTolerance(5, 10); //hmm
        motor = new TalonFX(0);

        configureBindings();
    }
    
    private void configureBindings() {
        Trigger buttonB = transmitter.b();
        buttonB.onTrue(new MoveToAngle(motor, pidController, targetAngle));
    }
}