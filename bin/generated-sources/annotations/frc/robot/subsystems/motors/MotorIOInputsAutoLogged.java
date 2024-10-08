package frc.robot.subsystems.motors;

import java.lang.Cloneable;
import java.lang.Override;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class MotorIOInputsAutoLogged extends MotorIO.MotorIOInputs implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("MotorCurrent", motorCurrent);
    table.put("MotorVoltage", motorVoltage);
    table.put("MotorPosition", motorPosition);
    table.put("MotorVelocity", motorVelocity);
    table.put("MotorTemperature", motorTemperature);
  }

  @Override
  public void fromLog(LogTable table) {
    motorCurrent = table.get("MotorCurrent", motorCurrent);
    motorVoltage = table.get("MotorVoltage", motorVoltage);
    motorPosition = table.get("MotorPosition", motorPosition);
    motorVelocity = table.get("MotorVelocity", motorVelocity);
    motorTemperature = table.get("MotorTemperature", motorTemperature);
  }

  public MotorIOInputsAutoLogged clone() {
    MotorIOInputsAutoLogged copy = new MotorIOInputsAutoLogged();
    copy.motorCurrent = this.motorCurrent;
    copy.motorVoltage = this.motorVoltage;
    copy.motorPosition = this.motorPosition;
    copy.motorVelocity = this.motorVelocity;
    copy.motorTemperature = this.motorTemperature;
    return copy;
  }
}
