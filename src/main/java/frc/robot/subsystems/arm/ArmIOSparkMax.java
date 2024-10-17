package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkLowLevel.PeriodicFrame;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.*;

public class ArmIOSparkMax implements ArmIO {
    private final CANSparkMax motor;
    private final AbsoluteEncoder encoder;
    private final SparkPIDController PIDF;

    PIDController PID_temp = new PIDController(10, 0, 0);

    Measure<Angle> angle = Radians.of(0);

    public ArmIOSparkMax(int id){
        motor = new CANSparkMax(id, MotorType.kBrushed);
        encoder = motor.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);
        PIDF = motor.getPIDController();

        PIDF.setP(10, 0);
        PIDF.setD(0, 0);
        PIDF.setI(0, 0);
        PIDF.setFF(0, 0);
        PIDF.setPositionPIDWrappingEnabled(true);
        PIDF.setPositionPIDWrappingMinInput(0);
        PIDF.setPositionPIDWrappingMaxInput(1);
        PIDF.setFeedbackDevice(encoder);

        motor.setCANTimeout(500);
        motor.setPeriodicFramePeriod(
            PeriodicFrame.kStatus5, // returns encoder position frame
            20
        );
        motor.setSmartCurrentLimit(30);
        motor.enableVoltageCompensation(12);
        motor.setIdleMode(IdleMode.kBrake);
        encoder.setAverageDepth(2);
        encoder.setPositionConversionFactor(1);
        motor.setCANTimeout(0);

        System.out.println(motor.burnFlash() == REVLibError.kOk);
    }

    @Override
    public void setVoltage(Measure<Voltage> volts){
        motor.setVoltage(volts.in(Volts));
    }

    @Override
    public void setPosition(Measure<Angle> position){
        motor.setVoltage(PID_temp.calculate(encoder.getPosition(), 0));
        
        // if(position.in(Radians) == 0){
        //     PIDF.setReference(
        //         position.in(Rotations),
        //         CANSparkMax.ControlType.kPosition,
        //         0
        //     );
        // }else{
        //     iteratePosition();
        // }
    }

    public void iteratePosition(){
        PIDF.setReference(
            encoder.getPosition() + 0.25,
            CANSparkMax.ControlType.kPosition,
            0
        );
    }

    @Override
    public void updateInputs(ArmIOInputs inputs) {
        inputs.motorCurrent = motor.getOutputCurrent();
        inputs.motorVoltage = motor.getBusVoltage();
        inputs.motorPosition = encoder.getPosition();
        inputs.motorVelocity = encoder.getVelocity();
        inputs.motorTemperature = motor.getMotorTemperature();
    }
}