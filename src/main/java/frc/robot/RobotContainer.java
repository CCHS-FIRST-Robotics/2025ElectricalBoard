// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.*;
import frc.robot.subsystems.motors.*;
import frc.robot.subsystems.pneumatics.*;

public class RobotContainer {
    CommandXboxController controller = new CommandXboxController(Constants.CONTROLLER_PORT);

    Trigger nintendo1 = new Trigger(new DigitalInput(Constants.SWITCH_PORT_1)::get);
    Trigger nintendo2 = new Trigger(new DigitalInput(Constants.SWITCH_PORT_2)::get);
    Trigger irSensor = new Trigger(new DigitalInput(Constants.IR_SENSOR_PORT)::get);

    Motors motors = new Motors(Constants.TALONFX_ID, Constants.SPARKMAX_ID, Constants.TALONSRX_ID_1, Constants.TALONSRX_ID_2);
    Pneumatics pneumatics = new Pneumatics(Constants.PISTON_ID_1, Constants.PISTON_ID_2);

    public RobotContainer() {
      configureBindings();
    }

    private void configureBindings() {
        //-----Motors-----//

        // joystick controls
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

        //-----Pneumatics-----//
        controller.b().onTrue(new InstantCommand(() -> pneumatics.togglePiston1()));
        controller.a().onTrue(new InstantCommand(() -> pneumatics.togglePiston2()));

        //-----DIO-----//
        nintendo1.onTrue(new InstantCommand(() -> System.out.println("switch 1")));
        nintendo2.onTrue(new InstantCommand(() -> System.out.println("switch 2")));
        irSensor.onTrue(new InstantCommand(() -> System.out.println("beam broken")));
    }
}