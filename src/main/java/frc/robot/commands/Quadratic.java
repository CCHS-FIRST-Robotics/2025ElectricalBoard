package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.*;

public class Quadratic extends Command {
    double time = 0.0;
    FourMotors motor;
    double temptime = 0.0;

    public Quadratic(FourMotors motors) {
        addRequirements(motors);
        this.motor = motor;
    }

    @Override
    public void execute() {
        if(time < 250) {
            motor.startMotor(0, 0.24 * Math.pow(0.02 * time, 2));
        }

        if(250 <= time && time <= 750) {
            motor.startMotor(0, -0.24 * (Math.pow(time * 0.02 - 10, 2) - 50));
        }

        if(time > 750) {
            temptime = 1000 - time;
            motor.startMotor(0, 0.24 * Math.pow(0.02 * temptime, 2));
            if(time == 1000) {
                time = 0;
            }
        }
        time++;
    }
}