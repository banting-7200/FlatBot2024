/*
 * @author: Nicholas Ranin
 * @editor: Edward
 * @description: Tank Drive Code
 */

// Package //
package frc.robot.Movement;
// Imports //
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.Constants.DriveSettings.*;
// Base Class //
public class TankDrive {
    // Properties //
    private DifferentialDrive differentialDrive;
    private XboxController controller;
    // Motor Pins //
    private final int leftFrontID = 1;
    private final int leftBackID = 2;

    private final int rightFrontID = 3;
    private final int rightBackID = 4;
    // Motor Objects //
    private PWMSparkMax leftMotor, leftFollower, rightMotor, rightFollower;
    // Initialize //
    /**
     * Call once to set up Tank Drive
     * 
     * Name: initialize
     * Frequency: Once
     */
    public void initialize()
    {
        // Initialize Controller // 
        controller = new XboxController(0); // Setup the controller port in driver station
        // Initialize Motors //
        // PWM Version:
        leftMotor = new PWMSparkMax(leftFrontID);
        leftFollower = new PWMSparkMax(leftBackID);
        rightMotor = new PWMSparkMax(rightFrontID);
        rightFollower = new PWMSparkMax(rightBackID);
        // Pair motors with their siblings //
        leftMotor.addFollower(leftFollower);
        rightMotor.addFollower(rightFollower);
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
        // Calculations //
        speedMultiplier = controller.getRightBumperPressed() ? speedMultiplier * -1 : speedMultiplier; // Flip drive
        creepMultiplier = controller.getRightTriggerAxis() > .5 ? creepSpeed : baseSpeed; // Creep drive
        // Controller //
        double leftControllerY = controller.getLeftY() * creepMultiplier * speedMultiplier; // Y controller values
        double rightControllerX = controller.getRightX() * creepMultiplier; // X controller values
        // Motor Speed //
        double leftMotorSpeed = (leftControllerY + rightControllerX); // Left Motor //
        double rightMotorSpeed = -(leftControllerY - rightControllerX); // Right Motor //
        // Tank Drive //
        differentialDrive.tankDrive(leftMotorSpeed, rightMotorSpeed); // Send code to the motors with the Tank Drive method
    }
}