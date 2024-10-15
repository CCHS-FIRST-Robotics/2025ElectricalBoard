// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * to do: 
 * 
 * big items for tomorrow: 
 * test all advantagescope logging
 * 
 * test talonfx: 
 *  - zeroing and regular iteration
 *  - iteration adding on the current position
 *    - with and without refreshing statussignal
 *  - without PID and just with motion profiling (may not even work)
 *  - with different values for motionmagic
 * 
 * test arm: 
 *  - setting voltage / toggling
 *  - zeroing and regular iteration
 *  - iteration adding on the current position
 * 
 * 
 * tune PIDs
 * arm motion profiling
 * test pneumatics
 */

public final class Main {
    private Main() {}

    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }
}