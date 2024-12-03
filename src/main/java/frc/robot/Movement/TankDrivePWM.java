/*
 * @author: Nicholas Ranin
 * @editor: Edward
 * @description: Tank Drive Code
 */

// Package //
package frc.robot.Movement;
// Imports //
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.Controller;

import static frc.robot.Constants.DriveSettings.*;
// Base Class //
public class TankDrivePWM {
    // Properties //
    private DifferentialDrive differentialDrive;
    private final Controller controller = Controller.getController();
    // Motor Objects //
    private PWMSparkMax leftMotor, leftFollowerMotor, rightMotor, rightFollowerMotor;
    // Initialize //
    /**
     * Call once to set up Tank Drive
     * 
     * Name: initialize
     * Frequency: Once
     */
    public void initialize()
    {
        // Initialize Motors //
        leftMotor = new PWMSparkMax(0);
        leftFollowerMotor = new PWMSparkMax(1);
        rightMotor = new PWMSparkMax(2);
        rightFollowerMotor = new PWMSparkMax(3);
        leftMotor.addFollower(leftFollowerMotor);
        rightMotor.addFollower(rightFollowerMotor);
        // Tank Drive //
        differentialDrive = new DifferentialDrive(leftMotor, rightMotor); // Create a Differential drive object
    }
    // Update //
    /**
     * Call often to update speed to controller input
     * 
     * Name: updateDrive
     * Frequency: Every-Frame
     */ 
    public void updateDrive()
    {
        if (isJoystick)
        {
            /// Joystick Version /// 
            // Calculations //
            speedMultiplier = joystickController.getRawButtonPressed(2) ? speedMultiplier * -1 : speedMultiplier; // Flip drive 
            creepMultiplier = joystickController.getTriggerPressed() ? creepSpeed : baseSpeed; // Creep drive
            // Controller //
            double leftControllerY = joystickController.getY() * creepMultiplier * speedMultiplier; // Y controller values
            double rightControllerX = joystickController.getX() * creepMultiplier;  // X controller values 
            // Motor Speed //
            double leftMotorSpeed = (leftControllerY - rightControllerX); // Left Motor // -1 | 1
            double rightMotorSpeed = (leftControllerY + rightControllerX); // Right Motor //
            // Tank Drive //
            differentialDrive.tankDrive(leftMotorSpeed, rightMotorSpeed); // Send code to the motors with the Tank Drive method
        }
        else
        {
            /// Xbox Version ///
            // Calculations //
            speedMultiplier = xBoxController.getRightBumperPressed() ? speedMultiplier * -1 : speedMultiplier; // Flip drive 
            creepMultiplier = xBoxController.getRightTriggerAxis() > .5 ? creepSpeed : baseSpeed; // Creep drive
            // Controller //
            double leftControllerY = xBoxController.getLeftY() * creepMultiplier * speedMultiplier; // Y controller values
            double rightControllerX = xBoxController.getRightX() * creepMultiplier; // X controller values
            // Motor Speed //
            double leftMotorSpeed = (leftControllerY + rightControllerX); // Left Motor //
            double rightMotorSpeed = -(leftControllerY - rightControllerX); // Right Motor //
            // Tank Drive //
            differentialDrive.tankDrive(leftMotorSpeed, rightMotorSpeed); // Send code to the motors with the Tank Drive method
        }
    }
}