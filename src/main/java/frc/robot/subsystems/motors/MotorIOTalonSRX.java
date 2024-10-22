package frc.robot.subsystems.motors;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.units.*;

public class MotorIOTalonSRX implements MotorIO {
    private final TalonSRX motor;

    private final double kP = 1;
    private final double kI = 0;
    private final double kD = 0;
    private final double kF = 0;
    
    public MotorIOTalonSRX(int id){
        motor = new TalonSRX(id);
        motor.configFactoryDefault();
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

        motor.config_kP(0, kP, 0);
	    motor.config_kI(0, kI, 0);
	    motor.config_kD(0, kD, 0);
        motor.config_kF(0, kF, 0);

        motor.setInverted(false);
        motor.setSensorPhase(true);
        motor.setSelectedSensorPosition(motor.getSensorCollection().getPulseWidthPosition(), 0, 0);
    }
    
    @Override
    public void setVoltage(Measure<Voltage> volts) {
        motor.set(TalonSRXControlMode.PercentOutput, volts.in(Volts) / 12);
    }

    @Override
    public void setPosition(Measure<Angle> position){
        motor.set(TalonSRXControlMode.Position, position.in(Rotations));
    }

    @Override
    public void updateInputs(MotorIOInputs inputs) {
        inputs.motorCurrent = motor.getStatorCurrent();
        inputs.motorVoltage = motor.getMotorOutputVoltage();
        inputs.motorPosition = motor.getSelectedSensorPosition();
        inputs.motorVelocity = motor.getSelectedSensorVelocity();
        inputs.motorTemperature = motor.getTemperature();
    }
}