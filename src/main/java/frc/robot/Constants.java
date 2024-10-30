// Address //
package frc.robot;
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
    public class DriveSettings // SubClass of Constants //
    {
        public static final double baseSpeed = 0.7;
        public static final double creepSpeed = 0.5;
        
        public static double speedMultiplier = 1;
        public static double creepMultiplier = 1;
    }
}