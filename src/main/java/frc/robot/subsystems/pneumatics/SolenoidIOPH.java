package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj.*;

public class SolenoidIOPH implements SolenoidIO{
    Solenoid solenoid;

    public SolenoidIOPH(int id){
        solenoid = new Solenoid(PneumaticsModuleType.REVPH, id);
    }

    @Override
    public void set(boolean on){
        solenoid.set(on);
    }

    @Override
    public void updateInputs(SolenoidIOInputs inputs){
        inputs.on = solenoid.get();
    }
}