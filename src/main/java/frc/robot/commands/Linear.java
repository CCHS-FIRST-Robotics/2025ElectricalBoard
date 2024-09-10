package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.*;

public class Linear extends Command {
    FourMotors motor;
    boolean increasing = true;
    double percent = 1d;
    double time = 0;
    double seconds = 10.0;

    public Linear(FourMotors motor) {
        addRequirements(motor);
        this.motor = motor;
    }

    @Override
    public void execute() {
        motor.startMotor(0, 12d * percent * time / (50d * seconds));

        if(increasing == true) {
            time++;
        }
        else {
            time--;
        }
        
        if(time == 500) {
            increasing = false;
        }
        if(time == 0) {
            increasing = true;
        }
    }
}