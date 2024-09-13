package frc.robot.commands;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;

public class UpDownQuadratic extends Command{
    int time = 0;
    int seconds = 10; //increase for 10 seconds
    boolean increasing = true;

    private TalonFX motors;
    public UpDownQuadratic(TalonFX motors){

        this.motors = motors;
    }

    @Override
    public void execute(){ //0.02 seconds period, time increases by 50 per second
        if(time == 500){
            increasing = false;
        }
        if(time == 0){
            increasing = true;
        }
        if(increasing){
            time++;
        }
        else{
            time--;
        }
        if(time >= 0 && time < 250){
            motors.set(6d*time*time/62500d/12d);f
        }
        if(time >= 250 && time <= 500){
            motors.set(1d-6d*(time-500d)*(time-500d)/62500d/12d);

        }
         
    }
}
