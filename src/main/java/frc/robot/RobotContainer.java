// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.*;
import frc.robot.subsystems.motors.*;
import frc.robot.subsystems.arm.*;
// import frc.robot.subsystems.pneumatics.*;

public class RobotContainer {
    private final CommandXboxController controller = new CommandXboxController(Constants.CONTROLLER_PORT);

    private final GroupOfMotors motors;
    private final Arm arm;
    // private final Pneumatics pneumatics;

    private final Trigger nintendo1 = new Trigger(new DigitalInput(Constants.SWITCH_PORT_1)::get);
    private final Trigger nintendo2 = new Trigger(new DigitalInput(Constants.SWITCH_PORT_2)::get);
    private final Trigger irSensor = new Trigger(new DigitalInput(Constants.IR_SENSOR_PORT)::get);

    public RobotContainer() {
        motors = new GroupOfMotors();
        arm = new Arm(new ArmIOSparkMax(Constants.SPARKMAX_ID));
        // pneumatics = new Pneumatics(Constants.PISTON_ID_1, Constants.PISTON_ID_2);

        motors.addMotor(new Motor(new MotorIOTalonFX(Constants.TALONFX_ID), 0)); // kraken
        // motors.addMotor(new Motor(new MotorIOSparkMax(Constants.SPARKMAX_ID), 1)); // neo
        // motors.addMotor(new Motor(new MotorIOTalonSRX(Constants.TALONSRX_ID_1), 2)); // cim1
        // motors.addMotor(new Motor(new MotorIOTalonSRX(Constants.TALONSRX_ID_2), 3)); // cim2

        configureBindings();
    }

    private void configureBindings() {
        //-----Motors-----//
        // motors.setDefaultCommand(
        //     new ControlWithJoysticks(
        //         motors,
        //         () -> controller.getLeftX(),
        //         () -> controller.getLeftY(),
        //         () -> controller.getRightX(),
        //         () -> controller.getRightY()
        //     )
        // );

        controller.x().onTrue(new InstantCommand(() -> motors.setMotorPosition(0, Radians.of(Math.random() * 2 * Math.PI))));
        controller.y().onTrue(new InstantCommand(() -> arm.setPosition(Radians.of(Math.random() * 2 * Math.PI))));
        controller.b().onTrue(new ExponentialProfile(motors, 20, 12, 10));
        controller.a().onTrue(new InstantCommand(() -> motors.toggleMotors()));

        //-----Pneumatics-----//
        // controller.b().onTrue(new InstantCommand(() -> pneumatics.togglePiston1()));
        // controller.a().onTrue(new InstantCommand(() -> pneumatics.togglePiston2()));

        //-----DIO-----//
        nintendo1.onTrue(new InstantCommand(() -> System.out.println("switch 1")));
        nintendo2.onTrue(new InstantCommand(() -> System.out.println("switch 2")));
        irSensor.onTrue(new InstantCommand(() -> System.out.println("beam broken")));
    }
}