package frc.robot;

import java.util.Hashtable;
import static frc.robot.Constants.MotorSettings.MotorClass;

import java.lang.reflect.*;

import java.util.Dictionary;

public class Motor 
{
    // Constant Data //
    public final int pinID;
    // Private Data //
    private static Dictionary<Integer, Motor> motorDict = new Hashtable<Integer, Motor>();
    private Object actualMotor;
    private Motor motorObject;
    // Constructor //
    public Motor(int pinID)
    {
        // Data //
        this.pinID = pinID;
        // Initialize Actual Motor //
        try
        {
            Constructor<?> BaseConstructor = MotorClass.getDeclaredConstructor();
            actualMotor = BaseConstructor.newInstance(pinID);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    // Base Methods //

    public static Motor getMotor(int pinID)
    {
        // Conditions //
        if (motorDict.get(pinID) != null)
            return motorDict.get(pinID);
        // Settings //
        return motorDict.put(pinID, new Motor(pinID));
    }
}
