package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
    private final CommandXboxController transmitter = new CommandXboxController(Constants.Controller_PORT);
    private final TalonFX Motor= new TalonFX(0);
    private final PIDController pidController = new PIDController(0.5, 0.0, 0.1);

    public RobotContainer() {
        pidController.setSetpoint(90); //90 deg (pi/2 rad)
        pidController.setTolerance(5, 10); //position tolerance, velocity tolerance
        configureBindings();
    }

    private void configureBindings() {
        Trigger Button = transmitter.b();
        Button.onTrue(new InstantCommand(() -> moveToAngle(90)));
  
    }

    /*public void moveToAngle(double angle) {
      pidController.setSetpoint(angle);
      double output = pidController.calculate(Motor.getPosition().getValue(), 90);
      Motor.set(output);

      if (pidController.atSetpoint()) {
        Motor.set(0);
      }
  }
  */

  public void moveToAngle(double angle) {
    pidController.setSetpoint(angle);
    double currentPosition = Motor.getPosition().getValue();
    double output = pidController.calculate(currentPosition);
  
    output = Math.max(Math.min(output, 1.0), -1.0);

    Motor.set(output);

    if (pidController.atSetpoint()) {
        Motor.set(0);
    }
}



}

