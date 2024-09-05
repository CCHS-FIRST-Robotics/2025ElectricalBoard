package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.*;

public class UpDownQuadratic extends Command{
    FourMotors motors;
    int time = 0;
    int seconds = 10;
    double percent = 1d; // should only range from 0 --> 1
    boolean increasing = true;
    boolean top = true;

    public UpDownQuadratic(FourMotors motors){
        addRequirements(motors);
        this.motors = motors;
    }

    @Override
    public void execute() {
        if(!top) motors.startMotor(0, time * time * 12d/(50d));
        else motors.startMotor(0, 12 - time * time * 12d/(50d));

        time++;

        // if(time == 0) 
        // if(increasing && time == 50) increasing = false;
        // else if(!increasing && time == 0) increasing = true;
    }

}
// 9471 is jude password