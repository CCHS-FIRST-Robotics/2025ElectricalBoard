package frc.robot.commands;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
public class MoveToAngle extends Command{
    double angle;
    private TalonFX motor;
    public MoveToAngle(double angle, TalonFX motor){
        this.angle = angle;
        this.motor = motor;
    }
    public void turnToAngle(){
        motor.setPosition(angle/(2*Math.PI));
    }
}
