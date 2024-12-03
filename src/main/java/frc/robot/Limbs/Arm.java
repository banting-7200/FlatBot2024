// Address //
package frc.robot.Limbs;
// Imports //
import static frc.robot.Constants.DriveSettings.isJoystick;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Controller;
// Data //
// Main Class //
public final class Arm 
{
    public static double inputAxis = 0;
    //initializes motors
    public static PWMSparkMax mainBallLauncher = new PWMSparkMax(5);
    public static PWMSparkMax followerBallLauncher = new PWMSparkMax(6);
    public static PWMSparkMax articulateMotor = new PWMSparkMax(4);
    // Input //
    public static Controller controller;
    // Initialize Method //
    public static void initialize()
    {
        mainBallLauncher.addFollower(followerBallLauncher);
        mainBallLauncher.setInverted(true);
        followerBallLauncher.setInverted(true);
        // Init Controller //
        controller = Controller.getController();
    }
    // Base Methods //
    public static void updateArticulate()
    {
        
        // Controls //
        if (controller.getAxis("Articulate") == 1){ // UP
            inputAxis = -0.1;
        } else if(controller.getAxis("Articulate") == -1){ // DOWN
            inputAxis = 0.35;
        } else{
            inputAxis = 0;
        }
               
        // Update Motors //
        articulateMotor.set(inputAxis);
    }
    // Update Method //
    public static void update()
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