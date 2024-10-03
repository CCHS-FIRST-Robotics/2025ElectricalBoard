package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.motors.FourMotors;

public class JoystickMotor extends Command{

    public FourMotors motors;
    public CommandXboxController controller;
    public double x;
    public double y;

    public double speed;

    public JoystickMotor(FourMotors motors, CommandXboxController controller){
        this.motors = motors;
        this.controller = controller;
        addRequirements(motors);
    }

    @Override
    public void execute() {
        x = controller.getLeftX();
        y = controller.getLeftY();
        speed = Math.sqrt(((x * x) + (y * y))); //Finding Magnitude

        if (Math.abs(speed) < 0.06){
            speed = 0;
        }
        else{
            speed *= 12;
            speed *= Math.signum(y);
        }

        motors.setAllMotorVoltage(speed);
    }

}
