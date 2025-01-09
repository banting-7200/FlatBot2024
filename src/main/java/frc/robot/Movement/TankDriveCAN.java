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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Input.Controller;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.Constants.DriveSettings.*;
// Base Class //
public class TankDriveCAN extends SubsystemBase
{
    // Devices //
    private final Controller controller = Controller.getController();
    // Properties //
    private DifferentialDrive differentialDrive;
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
    public TankDriveCAN()
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
    @Override
    public void periodic()
    {
        // Calculations //
        speedMultiplier = controller.isActionDown("Flip") ? speedMultiplier * -1 : speedMultiplier; // Flip drive 
        creepMultiplier = controller.isActionDown("Creep") ? creepSpeed : baseSpeed; // Creep drive
        // Overrides //
        creepMultiplier = Robot.isCreepOverriden() ? creepSpeed : creepMultiplier;
        // Controller //
        double leftControllerY = controller.getAxis("Vertical") * creepMultiplier * speedMultiplier; // Y controller values
        double rightControllerX = controller.getAxis("Horizontal") * creepMultiplier;  // X controller values 
        // Motor Speed //
        double leftMotorSpeed = (leftControllerY + rightControllerX); // Left Motor //
        double rightMotorSpeed = -(leftControllerY - rightControllerX); // Right Motor //
        // Tank Drive //
        differentialDrive.tankDrive(leftMotorSpeed, rightMotorSpeed); // Send code to the motors with the Tank Drive method
    }
}