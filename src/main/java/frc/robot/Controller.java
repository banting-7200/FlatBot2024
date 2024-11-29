// Address //
package frc.robot;
//  Imports //
import edu.wpi.first.wpilibj.XboxController; 
import edu.wpi.first.wpilibj.Joystick;
// Abstract Classes //
// Main Class //
public class Controller
{
    XboxController xboxController;
    Joystick joystickController;
    
    Object controller;
    // Constructor //
    public Controller(XboxController controller) 
    {
        this.xboxController = controller;
    }

    public Controller(Joystick controller)
    {
        this.joystickController = controller;
    }
    // Base Methods //
    public double getHorizontal()
    {
        if (xboxController == null)
            return joystickController.getX(); 
        return xboxController.getRightX(); 
    }

    public double getVertical()
    {
        if (xboxController == null)
            return joystickController.getY();
        return xboxController.getLeftY(); 
    }

    public boolean isCreepDown()
    {
        if (xboxController == null)
            return joystickController.getTriggerPressed();
        return xboxController.getRightTriggerAxis() > .5;
    }

    public boolean isShootDown()
    {
        if (xboxController == null)
            return joystickController.getRawButtonPressed(10);
        return xboxController.getRightTriggerAxis() > .5;
    }

    public boolean turretArticulateUp()
    {
        if (xboxController == null)
            return joystickController.getRawButtonPressed(5);
        return xboxController.getBButton();
    }

    
    public boolean turretArticulateDown()
    {
        if (xboxController == null)
            return joystickController.getRawButtonPressed(3);
        return xboxController.getAButton();
    }
}