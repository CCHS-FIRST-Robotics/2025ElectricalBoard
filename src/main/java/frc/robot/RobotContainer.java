// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.UpDownLinear;
import frc.robot.subsystems.Motor;

public class RobotContainer {
    CommandXboxController controller = new CommandXboxController(Constants.CONTROLLER_PORT);

    TalonSRX test = new TalonSRX(0);
    public RobotContainer() {
      configureBindings();
    }

    private void configureBindings() {
        controller.x().onTrue(new UpDownLinear(test));
    }
}