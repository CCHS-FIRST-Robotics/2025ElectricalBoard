package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;

public class LukeCode extends Command{
    //States
    public enum State{IDLE, SPEEDUP, MAX, SLOWDOWN};
    public State state = State.IDLE;

    public int timer = 50; //In 20 ms increments because I'm stupid
    public double motorVoltage = 0;
    
    public FourMotors motors; //This is what gets the motors

    public LukeCode(FourMotors motors){
        this.motors = motors;
        addRequirements(motors);
    }

    @Override
    public void execute(){
        switch (state) {
            case IDLE: {
                if (timer <= 0) {
                    state = State.SPEEDUP;
                    timer = 500;
                    //System.out.println(state);
                }
                timer--;
                break;
            }
            case SPEEDUP: {
                motorVoltage += 0.024;
                if (motorVoltage >= 12) {
                    state = State.MAX;
                    timer = 500;
                    //System.out.println(state);
                }
                break;
            }
            case MAX: {
                if (timer <= 0) {
                    state = State.SLOWDOWN;
                    timer = 500;
                    //System.out.println(state);
                }
                timer--;
                break;
            }
            case SLOWDOWN: {
                motorVoltage -= 0.024;
                if (motorVoltage <= 0) {
                    state = State.IDLE;
                    timer = 500;
                    //System.out.println(state);
                }
                break;
            }
        
            default:
                break;
        }
        motors.setAllMotorVoltage(motorVoltage);
    }
}