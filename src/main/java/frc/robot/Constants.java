// Address //
package frc.robot;
// Imports //
import java.util.Hashtable;

import java.util.Dictionary;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
// Credits //
// @author: Nicholas Ranin
// @editor: N/A
// @description: Carries constant values, valyes cannot be altered when running.

/* Static Imports:
 * Variables can be used directly without accessing
 * from the class every time.
 * 
 * Ex: With Regular Import: Speed *= Constants.DriveSettings.creepMultiplier;
 * Ex: With Static Import: Speed *= creepMultiplier;
 * 
 * Pros: See how we didn't have to write all those words?
 * Cons: Not Being Able to see the address of the variable (File Origin).
 * 
 * Intellisense definition: Autofill, Variable Information
 */

/* Final Keyword
 * Variable stays as the value it is set to
 * and cannot be changed when the code is
 * running, use to let others know this
 * value should not be changed.
 * 
 * Ex: public static final float creepMultiplier = 5;
 * 
 * Public Explanation: Variable can be accessed
 * from outside the class.
 * 
 * Static Explanation: Class Object not needed,
 * can be accessed directly from the base class.
 * Ex: float Multiplier = Constants.DriveSettings.creepMultiplier;
 */

// Base Class //
public final class Constants 
{
    /*
     * Import With ClassName: import frc.robot.Constants.DriveSettings;
     * Import Without ClassName: import static frc.robot.Constants.DriveSettings.*;
     * 
     * Explanation: Won't have to type the class name every time, you can just use the variables.
     * 
     * E.G: With Regular Import: Constants.DriveSettings.creepMultiplier
     * E.G: With Static Import: creepMultiplier
     */
    public final class DriveSettings // SubClass of Constants //
    {
        public static final boolean isJoystick = false; //determines robot's controller type

        public static final double baseSpeed = 0.7;
        public static final double creepSpeed = 0.5;
        
        public static double speedMultiplier = 1;
        public static double creepMultiplier = 1;
    }

    public final class MotorSettings
    {
        public static final Class<?> MotorClass = PWMSparkMax.class;
        public static final boolean isMotorTypePWM = true; //determines which motor controller should be used
    }
    

    public final class ShooterSettings
    {
        public static final int firstArmMotorPin = 4;
        public static final double loaderMotorSpeedMultiplier = 1d;
        public static final double shooterMotorSpeedMultiplier = 1d;
    }

    // Controller Type //
    public static final Class<?> ControllerClass = XboxController.class;
    // Inputs //
    public static final Dictionary<Class<?>, Dictionary<String, String>> InputMaps = 
    new Hashtable<Class<?>, Dictionary<String, String>>() {{
        put(XboxController.class, 
            new Hashtable<String, String>() {{
                // Movement //
                put("Horizontal", "getRightX|double");
                put("Vertical", "getLeftY|double");
                put("Flip", "getLeftBumper|boolean");
                put("Creep", "getRightBumper|boolean");
                // Actions //
                put("Shoot", "getRightTriggerAxis|boolean");
                // Turret //
                put("Articulate", "getPOV|yPOV");
            }}
        );

        put(Joystick.class,
            new Hashtable<String, String>() {{
                // Movement //
                put("Horizontal", "getX|double");
                put("Vertical", "getY|double");
                put("Flip", "getRawButton|boolean|2");
                put("Creep", "getRawButton|boolean|3");
                // Actions //
                put("Shoot", "getTrigger|boolean");
                // Turret //
                put("Articulate", "getPOV|yPOV");
            }}
        );

        put(PS4Controller.class,
            new Hashtable<String, String>() {{
                // Movement //
                put("Horizontal", "getRightX|double");
                put("Vertical", "getLeftY|double");
                put("Flip", "getL1Button|boolean");
                put("Creep", "getR1Button|boolean");
                // Actions //
                put("Shoot", "getR2Button|boolean");
                // Turret //
                put("Articulate", "getPOV|yPOV");
            }}
        );
    }};
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
