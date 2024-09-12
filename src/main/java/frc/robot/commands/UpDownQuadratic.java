package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;

public class UpDownQuadratic extends Command{
    int time = 0;
    int seconds = 10; //increase for 10 seconds
    boolean increasing = true;

    private TalonSRX motors;
    public UpDownQuadratic(TalonSRX motors){

        this.motors = motors;
    }

    @Override
    public void execute(){ //0.02 seconds period

    }
    
}
