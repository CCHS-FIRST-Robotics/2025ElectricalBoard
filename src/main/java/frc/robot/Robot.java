package frc.robot;

import org.littletonrobotics.junction.*;
import org.littletonrobotics.junction.networktables.NT4Publisher;

import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends LoggedRobot {
    RobotContainer robotContainer;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
        Logger.recordMetadata("ProjectName", "EncoderMoveToAngle");
        Logger.addDataReceiver(new NT4Publisher());
        Logger.start();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {}

    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {}

}
