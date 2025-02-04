package frc.robot;

import org.littletonrobotics.junction.*;
import org.littletonrobotics.junction.networktables.NT4Publisher;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.vision.Camera;
import frc.robot.subsystems.vision.Tag;

public class Robot extends LoggedRobot {
    RobotContainer robotContainer;
    Camera camera;

    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
        camera = new Camera();
        Logger.recordMetadata("ProjectName", "EncoderMoveToAngle");
        Logger.addDataReceiver(new NT4Publisher());

        Logger.start();
    }

    @Override
    public void robotPeriodic() {

        camera.updateInputs();
        Tag t = camera.getTag(1);

        /*if (t != null) {
            System.out.println(t.getID());
        }*/

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
