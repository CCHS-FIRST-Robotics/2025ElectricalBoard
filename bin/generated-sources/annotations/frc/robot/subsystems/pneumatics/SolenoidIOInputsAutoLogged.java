package frc.robot.subsystems.pneumatics;

import java.lang.Cloneable;
import java.lang.Override;
import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class SolenoidIOInputsAutoLogged extends SolenoidIO.SolenoidIOInputs implements LoggableInputs, Cloneable {
  @Override
  public void toLog(LogTable table) {
    table.put("On", on);
  }

  @Override
  public void fromLog(LogTable table) {
    on = table.get("On", on);
  }

  public SolenoidIOInputsAutoLogged clone() {
    SolenoidIOInputsAutoLogged copy = new SolenoidIOInputsAutoLogged();
    copy.on = this.on;
    return copy;
  }
}
