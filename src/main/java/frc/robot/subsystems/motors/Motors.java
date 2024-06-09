package frc.robot.subsystems.motors;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motors extends SubsystemBase{
    MotorIOTalonFX talonFX;
    MotorIOSparkMax sparkMax;
    MotorIOTalonSRX talonSRX1;
    MotorIOTalonSRX talonSRX2;

    public Motors(int talonFXId, int sparkMaxId, int talonSRXId1, int talonSRXId2){
        talonFX = new MotorIOTalonFX(talonFXId); // kraken
        sparkMax = new MotorIOSparkMax(sparkMaxId); // neo
        talonSRX1 = new MotorIOTalonSRX(talonSRXId1); // cim 1
        talonSRX2 = new MotorIOTalonSRX(talonSRXId2); // cim 2
        
    }

    public void startTalonFX(double volts){
        talonFX.setVoltage(volts);
    }

    public void startSparkMax(double volts){
        sparkMax.setVoltage(volts);
    }

    public void startTalonSRX1(double volts){
        talonSRX1.setVoltage(volts);
    }

    public void startTalonSRX2(double volts){
        talonSRX2.setVoltage(volts);
    }

    public void stopAll(){
        talonFX.setVoltage(0);
        sparkMax.setVoltage(0);
        talonSRX1.setVoltage(0);
        talonSRX2.setVoltage(0);
    }
}