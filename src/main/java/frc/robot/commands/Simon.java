package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.motors.FourMotors;

public class Simon extends Command{
 
    public FourMotors motors;
    public CommandXboxController controller;
    public double x;
    public double y;

    public double magnitude;

    public Simon(FourMotors motors, CommandXboxController controller){
        this.motors = motors;
        this.controller = controller;
        addRequirements(motors);
    }

    @Override
    public void execute() {
        x = controller.getLeftX();
        y = controller.getLeftY();
        magnitude = Math.sqrt(((x * x) + (y * y))); //Finding Magnitude

        if (Math.abs(magnitude) < 0.06){
            magnitude = 0;
        }
        else{
            magnitude *= 12;
            magnitude *= Math.signum(y);
        }

        motors.setAllMotorVoltage(magnitude);
    }
    
}
