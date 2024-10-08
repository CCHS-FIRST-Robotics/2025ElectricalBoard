package frc.robot;


import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class RobotContainer {
    private final CommandXboxController transmitter = new CommandXboxController(Constants.Controller_PORT);
    private final TalonFX Motor= new TalonFX(0);
    private final PIDController pidController = new PIDController(1.0, 0.0, 0.1);

    public RobotContainer() {
        pidController.setSetpoint(90); //90 deg (pi/2 rad)
        pidController.setTolerance(3); //3 deg off is fine?
        configureBindings();
    }

    private void configureBindings() {
        Trigger Button = transmitter.b();
        Button.onTrue(new InstantCommand(() -> moveToAngle(90)));
  
    }

     public void moveToAngle(double angle) {
      pidController.setSetpoint(angle);
      double output = pidController.calculate(Motor.getPosition().getValue());
      Motor.set(output);

      if (pidController.atSetpoint()) {
        Motor.set(0);
      }
  }


}

