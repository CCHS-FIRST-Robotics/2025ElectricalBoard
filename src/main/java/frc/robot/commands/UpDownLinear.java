package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.*;

public class UpDownLinear extends Command{
    FourMotors motors;
    int time = 0;
    int seconds = 10;
    double percent = 1d;
    boolean increasing = true;

    public UpDownLinear(FourMotors motors){
        addRequirements(motors);
        this.motors = motors;
    }

    @Override
    public void execute() {
        motors.startMotor(0, time * percent * 12d/(50d * seconds));
        
        if(increasing) time++;
        else time--;

        if(time == 50 * seconds) increasing = !increasing;
        else if(time == 0) increasing = !increasing;
    }

}
