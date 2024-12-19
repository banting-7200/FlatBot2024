// Address //
package frc.robot.Limbs;
// Imports //
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Input.Controller;
import edu.wpi.first.wpilibj.Joystick;
// Data //
// Main Class //
public final class Arm extends SubsystemBase
{
    public double inputAxis = 0;
    //initializes motors
    public PWMSparkMax mainBallLauncher = new PWMSparkMax(5);
    public PWMSparkMax followerBallLauncher = new PWMSparkMax(6);
    public PWMSparkMax articulateMotor = new PWMSparkMax(4);
    // Input //
    public Controller controller;
    // Initialize Method //
    public Arm()
    {
        mainBallLauncher.addFollower(followerBallLauncher);
        mainBallLauncher.setInverted(true);
        followerBallLauncher.setInverted(true);
        // Init Controller //
        controller = Controller.getController();
    }
    // Base Methods //
    public void updateArticulate()
    {
        // Controls //
        if (controller.getAxis("Articulate") > 0) // Up
            inputAxis = 0.35;
        else if(controller.getAxis("Articulate") < 0) // DOWN
            inputAxis = -0.1;
        else
            inputAxis = 0; // 
               
        // Update Motors //
        articulateMotor.set(inputAxis);
    }
    // Update Method //
    @Override
    public void periodic()
    {
        updateArticulate();
        // Conditions //
        if (!controller.isActionDown("Shoot"))
        {
            mainBallLauncher.set(0);
            return;
        }
        // Settings //
        System.out.println("SHOOTING!");
        mainBallLauncher.set(1);
    }
}