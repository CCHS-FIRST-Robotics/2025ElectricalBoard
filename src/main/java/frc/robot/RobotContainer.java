package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
    private final CommandXboxController transmitter = new CommandXboxController(Constants.Controller_PORT);
    private final TalonFX motor= new TalonFX(0);
    private final PIDController pidController = new PIDController(0.5, 0.0, 0.1);

    private final double targetAngle = 90;

    public RobotContainer() {
        pidController.setSetpoint(targetAngle); //90 deg (pi/2 rad)
        pidController.setTolerance(5, 10); //position tolerance, velocity tolerance
        configureBindings();
    }

    private void configureBindings() {
        Trigger Button = transmitter.b();
        Button.onTrue(new InstantCommand(() -> moveToAngle(targetAngle)));
      
    }

    public void moveToAngle(double angle) {
      pidController.setSetpoint(angle);
      double currentPosition = motor.getPosition().getValue();
      double output = pidController.calculate(currentPosition);

      //constrain the output to -1.0 to 1.0 as the controller joystick ranges from -1 to 1 and 1 = max throttle signal -1 = reverse direction max throttle?
      MathUtil.clamp(pidController.calculate(output, targetAngle), -1, 1);

      motor.set(output);

      if (pidController.atSetpoint()) {
        motor.set(0);
      }
  }

}

