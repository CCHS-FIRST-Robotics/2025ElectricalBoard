package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.units.*;

public class ArmIOTalonSRX implements ArmIO {
    private final TalonSRX motor;
    
    public ArmIOTalonSRX(int id){
        motor = new TalonSRX(id);

        motor.configFactoryDefault();
        motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        motor.setInverted(false);
        motor.setSensorPhase(true);

		motor.config_kP(0, 1, 0);
		motor.config_kI(0, 0, 0);
		motor.config_kD(0, 0, 0);
        motor.config_kF(0, 0, 0);

        motor.setSelectedSensorPosition(motor.getSensorCollection().getPulseWidthPosition(), 0, 0);
    }
    
    @Override
    public void setVoltage(Measure<Voltage> volts) {
        motor.set(TalonSRXControlMode.PercentOutput, volts.in(Volts) / 12);
    }

    @Override
    public void setPosition(Measure<Angle> position){
        if(position.in(Radians) == 0){
            motor.set(TalonSRXControlMode.Position, position.in(Rotations));
        }else{
            iteratePosition();
        }
    }

    public void iteratePosition(){
        motor.set(TalonSRXControlMode.Position, motor.getSelectedSensorPosition() + 0.25 * 4096);
        System.out.println(motor.getSelectedSensorPosition() + 0.25 * 4096);
    }

    @Override
    public void updateInputs(ArmIOInputs inputs) {
        inputs.motorCurrent = motor.getStatorCurrent();
        inputs.motorVoltage = motor.getMotorOutputVoltage();
        inputs.motorPosition = motor.getSelectedSensorPosition();
        inputs.motorVelocity = motor.getSelectedSensorVelocity();
        inputs.motorTemperature = motor.getTemperature();
    }
}