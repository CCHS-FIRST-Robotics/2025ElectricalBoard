package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Constants;

public class Intake {
    TalonFX intakeMotor = new TalonFX(Constants.intakeMotor);

    public void runIntake() {
        //intakeMotor.set()
    }
}
