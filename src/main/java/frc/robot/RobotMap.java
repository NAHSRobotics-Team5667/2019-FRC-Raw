/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // Map ports for our robot

    // Controller
    public static int controllerPort = 0; // Controller port

    // Camera defaults
    public static int DefaultCameraCount = 2; // The total amount of cameras
    public static int DefaultCameraPort = 0; // The default camera port for camera 1
    public static int DefaultCamera_XR = 120; // The X resolution for the cameras
    public static int DefaultCamera_YR = 60; // The Y resolution for the cameras
    public static int DefaultCamera_FPS = 30; // The default FPS for the cameras

    // Encoders
    public static int EncoderPortA = 2;
    public static int EncoderPortB = 3;

    // Motors
    // - Drive Train
    public static int r1Port = 0; // Right motor port
    public static int r2Port = 1; // Right 2 motor port
    public static int l1Port = 2; // Left motor port
    public static int l2Port = 3; // Left 2 motor port

    // - Linear slide
    public static int SlidePort = 9; // Linear Slide port

    // Pistons
    public static int ClawSolenoidPort = 0;
    public static int BallPiston = 1;

    // Slide calibrated values
    // LEVEL 1: 0
    // LEVEL 2: 205057
    // LEVEL 3: 336398.0
    public static int[] Levels = { 0, 569500, 1046000 };

    // Sticks
    public static int sRightX_Port = 4; // Right stick x
    public static int sRightY_Port = 5; // Right stick y
    public static int sLeftX_Port = 0; // Left stick x
    public static int sLeftY_Port = 1; // Left stick y

    // Triggers
    public static int TriggerRight_Port = 3; // Right trigger
    public static int TriggerLeft_Port = 2; // Left trigger

    // Bumpers
    public static int BumperRight_Port = 6; // Right bumper
    public static int BumperLeft_Port = 5; // Left bumper

    // Buttons
    public static int button_A_Port = 1; // A Button
    public static int button_B_Port = 2; // B Button
    public static int button_X_Port = 3; // X Button
    public static int button_Y_Port = 4; // Y Button

    // Special buttons
    public static int button_menu_Port = 8; // Menu Button
    public static int button_Start_Port = 7; // Start button

}
