package frc.robot.subsystems.motors;

public class Motor {
    private MotorIO io;
    private MotorIOInputsAutoLogged inputs = new MotorIOInputsAutoLogged();

    public Motor(MotorIO io) {
        this.io = io;
    }

    public void setVoltage(double volts){
        io.setVoltage(volts);
    }

    public void getVoltage() {
        io.getVoltage();
    }

    public void updateInputs(){
        io.updateInputs(inputs);
    }


}