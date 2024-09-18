package frc.robot.commands;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
public class MoveToAngle extends Command{
    private TalonFX motor;
    public MoveToAngle(TalonFX motor){
        this.motor = motor;
    }
    public void turnToAngle(double angle){
        motor.setPosition(angle/(2*Math.PI));
    }
}
