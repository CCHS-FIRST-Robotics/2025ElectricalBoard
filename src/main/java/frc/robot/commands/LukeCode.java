package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motors.FourMotors;

public class LukeCode extends Command{

    //States
    public enum State{IDLE, SPEEDUP, MAX, SLOWDOWN};
    public State state = State.IDLE;

    public int timer; //In 20 ms increments because I'm stupid
    public double motorSpeed = 0;
    
    public FourMotors motors; //This is what gets the motors
    @Override
    public void execute(){
        //System.out.println("Hello, World!");
        switch (state) {
            case IDLE: {
                timer--;
                if (timer == 0) {
                    state = State.SPEEDUP;
                }
                break;
            }
            case SPEEDUP: {
                motorSpeed += 0.2 / 12;
                if (motorSpeed == 12) {
                    state = State.MAX;
                    timer = 50;
                }
                break;
            }
            case MAX: {
                timer--;
                if (timer == 0) {
                    state = State.SLOWDOWN;
                }
                break;
            }
            case SLOWDOWN: {
                motorSpeed -= 0.2 / 12;
                if (motorSpeed == 12) {
                    state = State.IDLE;
                    timer = 50;
                }
                break;
            }
        
            default:
                break;
        }
    }
    public LukeCode(){
    }
}
