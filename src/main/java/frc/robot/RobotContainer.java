package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.units.*;
import static edu.wpi.first.units.Units.*;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;

import frc.robot.commands.MoveToAngle;

public class RobotContainer {
    private final CommandXboxController transmitter = new CommandXboxController(Constants.Controller_PORT);
    private final TalonFX motor;
    private final PIDController pidController;
    Measure<Angle> targetAngle1 = Radians.of(Math.PI / 2); //pi/2 rad = 90 deg
    Measure<Angle> targetAngle2 = Radians.of(Math.PI); //pi rad = 180 deg
    Measure<Angle> targetAngle3 = Radians.of(Math.PI / 4); // 45 deg


    public RobotContainer() {
        Logger.recordMetadata("ProjectName", "EncoderMoveToAngle");
        Logger.addDataReceiver(new NT4Publisher());
        Logger.start();

        pidController = new PIDController(1, 0, 0.1);
        motor = new TalonFX(0);

        configureBindings();
    }
    
    private void configureBindings() {
        Trigger buttonB = transmitter.b();
        buttonB.onTrue(new MoveToAngle(motor, pidController, targetAngle1));

        Trigger buttonX = transmitter.x();
        buttonX.onTrue(new MoveToAngle(motor, pidController, targetAngle2));

        Trigger buttonY = transmitter.y();
        buttonY.onTrue(new MoveToAngle(motor, pidController, targetAngle3));

      
    }
}