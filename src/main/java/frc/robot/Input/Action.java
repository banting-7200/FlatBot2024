package frc.robot.Input;

import java.lang.reflect.*;
import java.util.Dictionary;
import java.util.Hashtable;

import edu.wpi.first.wpilibj.XboxController;

/*
 * Meant only for controller class!
 */
public class Action
{
    // Data //
    public final Dictionary<Object, Class<?>> parameterList = new Hashtable<Object,Class<?>>() {{
        
    }};

    Object controller;
    String actionName;
    Method action;
    // Constructors //
    public Action(String actionName)
    {
        this.actionName = actionName;
        this.controller = Controller.getController().getActualController();
    }
}
