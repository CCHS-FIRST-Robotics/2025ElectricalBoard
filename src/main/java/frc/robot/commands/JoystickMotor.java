package frc.robot.commands;

import static edu.wpi.first.units.Units.*; //REALLY IMPORTANT


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.motors.GroupOfMotors;

public class JoystickMotor extends Command{

    public GroupOfMotors motors;
    public CommandXboxController controller;
    public double x;
    public double y;

    public double speed;
    public double angle;

    public JoystickMotor(GroupOfMotors motors, CommandXboxController controller){
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
            angle = Math.atan(y / x);

        }

        // motors.setAllMotorVoltage(Volts.of(speed));
        motors.setMotorPosition(0, Radians.of(angle));
    }

}
