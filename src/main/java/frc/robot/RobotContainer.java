// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import frc.robot.subsystems.motors.*;
import frc.robot.subsystems.pneumatics.*;

public class RobotContainer {
    CommandXboxController controller = new CommandXboxController(Constants.CONTROLLER_PORT);
    Trigger button = new Trigger(new DigitalInput(0)::get);
    //DigitalInput button = new DigitalInput(0);
    
    FourMotors motors = new FourMotors(
        new Motor(new MotorIOTalonFX(Constants.TALONFX_ID)), // kraken
        // new Motor(new MotorIOSparkMax(Constants.SPARKMAX_ID)) // neo
        new Motor(new MotorIOTalonSRX(Constants.TALONSRX_ID_1)) // cim1
        //new Motor(new MotorIOTalonSRX(Constants.TALONSRX_ID_2)) // cim2 //! 
        
    );
    
    Pneumatics pneumatics = new Pneumatics(Constants.PISTON_ID_1, Constants.PISTON_ID_2);

    public RobotContainer() {
      configureBindings();
    }

    private void configureBindings() {
        //-----Motors-----//

        // joystick controls
        // motors.setDefaultCommand(
        //     new ControlWithJoysticks(
        //         motors,
        //         () -> controller.getLeftX(),
        //         () -> controller.getLeftY(),
        //         () -> controller.getRightX(),
        //         () -> controller.getRightY()
        //     )
        // );

        // button controls
        controller.x().onTrue(new LukeCode(motors));
        button.onTrue(new InstantCommand(()->motors.toggleMotors()));
        controller.b().onTrue(new JoystickMotor(motors, controller));
        
        
        // controller.a().onTrue(new InstantCommand(() -> motors.toggleMotors()));

        //-----Pneumatics-----//
        // controller.b().onTrue(new InstantCommand(() -> pneumatics.togglePiston1()));
        // controller.a().onTrue(new InstantCommand(() -> pneumatics.togglePiston2()));
    }
}