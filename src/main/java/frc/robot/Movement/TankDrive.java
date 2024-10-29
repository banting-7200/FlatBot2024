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

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.Constants.DriveSettings.*;
// Base Class //
public final class TankDrive {
    // Properties //
    private static DifferentialDrive differentialDrive;
    private static XboxController controller;
    // Motor Pins //
    private static final int leftFrontID = 1;
    private static final int leftBackID = 2;

    private static final int rightFrontID = 3;
    private static final int rightBackID = 4;
    // Motor Objects //
    private static CANSparkMax leftMotor, leftFollower, rightMotor, rightFollower;
    // Initialize //
    /**
     * Call once to set up Tank Drive
     * 
     * Name: initialize
     * Frequency: Once
     */
    public static void initialize()
    {
        // Initialize Controller // 
        controller = new XboxController(0);
        // Initialize Motors //
        /*
         * PWM Version:
         * PWMSparkMax leftMotor = new PWMSparkMax(0);
         * PWMSparkMax leftFollowerMotor = new PWMSparkMax(1);
         * PWMSparkMax leftFollowerMotor = new PWMSparkMax(2);
         * PWMSparkMax leftFollowerMotor = new PWMSparkMax(3);
         * leftMotor.addFollower(leftFollowerMotor);
         * rightMotor.addFollower(rightFollowerMotor);
         */
        leftMotor = new CANSparkMax(leftFrontID, MotorType.kBrushed);
        leftFollower = new CANSparkMax(leftBackID, MotorType.kBrushed);
        rightMotor = new CANSparkMax(rightFrontID, MotorType.kBrushed);
        rightFollower = new CANSparkMax(rightBackID, MotorType.kBrushed);
        // Pair motors with their siblings //
        leftFollower.follow(leftMotor);
        rightFollower.follow(rightMotor);
        // Tank Drive //
        differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
    }
    // Update //
    /**
     * Call often to update speed to controller input
     * 
     * Name: updateDrive
     * Frequency: Every-Frame
     */ 
    public static void updateDrive()
    {
        // Calculations //
        speedMultiplier = controller.getRightBumperPressed() ? speedMultiplier * -1 : speedMultiplier;
        creepMultiplier = controller.getRightTriggerAxis() > .5 ? creepSpeed : baseSpeed;
        // Controller //
        double leftControllerY = controller.getLeftY() * creepMultiplier * speedMultiplier;
        double rightControllerX = controller.getRightX() * creepMultiplier;
        // Motor Speed //
        double leftMotorSpeed = (leftControllerY + rightControllerX); // Left Motor //
        double rightMotorSpeed = -(leftControllerY - rightControllerX); // Right Motor //
        // Tank Drive //
        differentialDrive.tankDrive(leftMotorSpeed, rightMotorSpeed);
    }
}