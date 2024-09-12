package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;

public class UpDownLinear extends Command{
    int time = 0;
    int seconds = 10; //increase for 10 seconds
    boolean increasing = true;

    private TalonSRX motors;
    public UpDownLinear(TalonSRX motors){

        this.motors = motors;
    }

    @Override
    public void execute(){ //0.02 seconds period, 50 cycles per second 
        motors.set(TalonSRXControlMode.PercentOutput, time*12d/(50d*seconds));//after one second, called 50 times, want voltage to be 12/10
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
    }
    
}
