// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.*;
import frc.robot.subsystems.motors.*;

public class RobotContainer {
    CommandXboxController controller = new CommandXboxController(Constants.CONTROLLER_PORT);

    DigitalInput nintendo1Input = new DigitalInput(Constants.SWITCH_PORT_1);
    DigitalInput nintendo2Input = new DigitalInput(Constants.SWITCH_PORT_2);
    DigitalInput sensorInput = new DigitalInput(Constants.SENSOR_PORT);
    Trigger nintendo1 = new Trigger(nintendo1Input::get);
    Trigger nintendo2 = new Trigger(nintendo2Input::get);
    Trigger sensor = new Trigger(sensorInput::get);

    Motors motors = new Motors(Constants.TALONFX_ID, Constants.SPARKMAX_ID, Constants.TALONSRX_ID_1, Constants.TALONSRX_ID_2);

    public RobotContainer() {
      configureBindings();
    }

    private void configureBindings() {
        // ! hm maybe we shouldn't have two inputs controlling the same output

        // joysticks
        motors.setDefaultCommand(
            new ControlWithJoysticks(
                motors,
                () -> controller.getLeftX(),
                () -> controller.getLeftY(),
                () -> controller.getRightX(),
                () -> controller.getRightY()
            )
        );

        // button controls
        // controller.x().onTrue(new InstantCommand(() -> motors.startTalonFX(8)));
        // controller.y().onTrue(new InstantCommand(() -> motors.startSparkMax(8)));
        // controller.b().onTrue(new InstantCommand(() -> motors.startTalonSRX1(8))
        //             .andThen(new InstantCommand(() -> motors.startTalonSRX2(8))));
        // controller.a().onTrue(new InstantCommand(() -> motors.stopAll()));

        // DIO
        nintendo1.onTrue(new InstantCommand(() -> System.out.println("switch 1")));
        nintendo2.onTrue(new InstantCommand(() -> System.out.println("switch 2")));
        sensor.onTrue(new InstantCommand(() -> System.out.println("beam broken")));
    }
}