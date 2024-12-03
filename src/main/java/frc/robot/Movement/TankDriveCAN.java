/*
 * @author: Nicholas Ranin
 * @editor: Edward
 * @description: Tank Drive Code
 */

// Package //
package frc.robot.Movement;
// Imports //
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.Constants.DriveSettings.*;
// Base Class //
public class TankDriveCAN {
    // Properties //
    private DifferentialDrive differentialDrive;
    private final Joystick joystickController = new Joystick(0);
    private final XboxController xBoxController = new XboxController(0); // private controller controller;
    // Motor Pins //
    private final int leftFrontID = 1;
    private final int leftBackID = 2;

    private final int rightFrontID = 3;
    private final int rightBackID = 4;
    // Motor Objects //
    private CANSparkMax leftMotor, leftFollower, rightMotor, rightFollower;
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
        leftMotor = new CANSparkMax(leftFrontID, MotorType.kBrushed); // CIM motors are brushed, neo motors are brushless.
        leftFollower = new CANSparkMax(leftBackID, MotorType.kBrushed);
        rightMotor = new CANSparkMax(rightFrontID, MotorType.kBrushed);
        rightFollower = new CANSparkMax(rightBackID, MotorType.kBrushed);
        // Pair motors with their siblings //
        leftFollower.follow(leftMotor);
        rightFollower.follow(rightMotor);
        // Flash Motors //
        leftMotor.burnFlash();
        leftFollower.burnFlash();
        rightMotor.burnFlash();
        rightFollower.burnFlash();
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