package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;

public class UpDownLinear extends Command{
    private TalonSRX motors;
    public UpDownLinear(TalonSRX motors){
        this.motors = motors;
    }

    @Override
    public void execute(){
        
    }
    
}
