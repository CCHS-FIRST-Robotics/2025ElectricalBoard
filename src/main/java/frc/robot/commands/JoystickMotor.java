package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.motors.FourMotors;

public class JoystickMotor extends Command{

        public FourMotors motors;
        public CommandXboxController controller;
        public double speed;

        public JoystickMotor(FourMotors motors, CommandXboxController controller){
            this.motors = motors;
            this.controller = controller;
            addRequirements(motors);
    }

    @Override
    public void execute() {
        speed = Math.sqrt(((controller.getLeftX() * controller.getLeftX()) + (controller.getLeftY() * controller.getLeftY()))) * 12; //Finding Magnitude

        motors.setAllMotorVoltage(speed);
    }

}
