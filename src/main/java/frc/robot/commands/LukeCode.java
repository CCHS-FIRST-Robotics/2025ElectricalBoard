package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;

public class LukeCode extends Command{

    public FourMotors motors;
    @Override
    public void execute(){
        System.out.println("Hello, World!");
    }
    public LukeCode(){
        //motors.setMotorVoltage(0, 6);
    }
}
