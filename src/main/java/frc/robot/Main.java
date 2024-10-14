// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * to do: 
 * 
 * test talonfx voltage signal
 * test talonfx getting position and then iterating vs not getting position
 * // ! test the differences between with and without pid
 * 
 * arm position control
 * arm motion profiling
 * test pneumatics
 */

public final class Main {
    private Main() {}

    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }
}