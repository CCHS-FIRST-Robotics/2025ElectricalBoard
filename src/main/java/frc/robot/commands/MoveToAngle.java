package frc.robot.commands;
import com.ctre.phoenix6.hardware.TalonFX;
import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.Command;
public class MoveToAngle extends Command{
    private TalonFX motor;
    Supplier<Double> x;
    Supplier<Double> y;
    public MoveToAngle(TalonFX motor, Supplier<Double> x, Supplier<Double> y){
        this.x = x;
        this.y = y;
        this.motor = motor;
    }
    public void turnToAngle(double angle){
        System.out.println(angle);
        motor.setPosition(angle/(2*Math.PI));
    }
    @Override
    public void execute(){
        double leftX = x.get();
        double leftY = y.get();
        // System.out.println(leftX + ", " + leftY);
        if(leftX >= 0){
            turnToAngle(Math.atan(leftY/leftX));
        }
        else{
            turnToAngle(Math.PI + Math.atan(leftY/leftX));
        }
    }
}
