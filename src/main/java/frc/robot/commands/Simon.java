package frc.robot.commands;

import static edu.wpi.first.units.Units.Radians;
// import static edu.wpi.first.units.Units.Volts;

// import static edu.wpi.first.units.Units.*; //REALLY IMPORTANT


import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.motors.GroupOfMotors;

public class Simon extends Command{
    
    //Mechanical stuff (ew)
    public GroupOfMotors motors;
    public CommandXboxController controller;

    public double x;
    public double y;

    public double magnitude;

    public double direction;

    //Simon stuff (yay)
    public final double[] directions = {-Math.PI, -Math.PI * 0.5, 0, Math.PI * 0.5}; //Radians, baby!
    //                               north east           south    west
    public ArrayList<Double> sequence = new ArrayList<Double>();
    public double selectedDirection = 0;

    public enum GameState{SEQUENCE, THINK, ACTIVATED, LOSE};
    public GameState state = GameState.SEQUENCE;
    public double input;

    public int sequenceIndex = 0; //How far in the sequence we are
    public int timer = 50;

    boolean done = false;

    //Other stuff idk
    public Simon(GroupOfMotors motors, CommandXboxController controller){
        this.motors = motors;
        this.controller = controller;
        addRequirements(motors);
    }

    @Override
    public void initialize(){
        sequence.add(directions[(int) Math.round(Math.random() * 3)]);
    }

    @Override
    public void execute() {
        switch (state) {
            case THINK: { //When the player's joystick is resting. Detecting if they move it out of deadzone
                
                x = controller.getLeftX();
                y = controller.getLeftY();
                magnitude = Math.sqrt(((x * x) + (y * y))); //Finding Magnitude

                if (Math.abs(magnitude) >= 0.5){
                    //DO THE SIMON Stuff
                    input = Math.round( (Math.atan2(y, x) * 2) / Math.PI ) * Math.PI * 0.5;

                    state = GameState.ACTIVATED;
                }

                break;
            }
            case ACTIVATED:{
                if (input == sequence.get(sequenceIndex)) { //Checking if correct
                    if (sequence.size() != sequenceIndex + 1) { //Making sure not at end of sequence
                        sequenceIndex++;
                        motors.setMotorPosition(0, Radians.of(input));
                    }
                    else { //If end of sequence
                        motors.setMotorPosition(0, Radians.of(0));
                        sequenceIndex = 0;
                        timer = 100;
                        sequence.add(directions[(int) Math.round(Math.random() * 3)]);
                        state = GameState.SEQUENCE;
                    }
                }
                else {
                    state = GameState.LOSE;
                }
                break;
            }
            case SEQUENCE: {
                if (timer <= 0) {
                    motors.setMotorPosition(0, Radians.of(sequence.get(sequenceIndex)));
                    if (sequence.size() == sequenceIndex + 1){
                        sequenceIndex = 0;
                        state = GameState.THINK;
                    }
                    else {
                        sequenceIndex++;
                        timer = 100;
                    }
                }
                else {
                    timer--;
                }
                break;
            }
        
            case LOSE: {
                done = true;
            }

            default:
                break;
        }
    }

    public boolean isFinished(){
        return done;
    }
    
}
