// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.MoveToAngle;
import frc.robot.commands.UpDownLinear;
//import frc.robot.commands.UpDownQuadratic;

public class RobotContainer {
    CommandXboxController controller = new CommandXboxController(Constants.CONTROLLER_PORT);
    Trigger nintendo1 = new Trigger(new DigitalInput(Constants.SWITCH_PORT_1)::get);
    TalonFX test = new TalonFX(0);
    MoveToAngle temp;
    public RobotContainer() {
      configureBindings();
      
    }
    
    private void configureBindings() {
        System.out.println("why");
        // MoveToAngle temp = new MoveToAngle(test, () -> controller.getLeftX(), () -> controller.getLeftY());
     //   controller.x().onTrue( new MoveToAngle(test, () -> controller.getLeftX(), () -> controller.getLeftY()));
    //     nintendo1.onTrue(new InstantCommand(() -> System.out.println("switch 1")));
        controller.x().toggleOnTrue(new UpDownLinear(test));
      //  controller.y().onTrue(new UpDownQuadratic(test));
      
    }
}