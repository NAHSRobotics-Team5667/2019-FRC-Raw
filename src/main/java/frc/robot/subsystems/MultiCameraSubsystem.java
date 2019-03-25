/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * A Multi-Camera Subsystem controller
 */
public class MultiCameraSubsystem extends Subsystem {

    UsbCamera camera1;
    UsbCamera camera2;

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * A Camera subsystem capable of controlling 2 cameras
     * 
     * @param _cameraCount - The amount of cameras desired (1-2)
     * @param _port        - The desired starting port for camera 1
     * @param xres         - The X Resolution for the cameras
     * @param yres         - The Y Resolution for the cameras
     * @param fps          - The FPS for the cameras
     */
    public MultiCameraSubsystem(int _cameraCount, int _port, int xres, int yres, int fps) {

        if (_cameraCount == 1) {
            camera1 = CameraServer.getInstance().startAutomaticCapture(_port);
            camera1.setResolution(xres, yres);

        } else if (_cameraCount == 2) {
            camera1 = CameraServer.getInstance().startAutomaticCapture(_port);
            camera2 = CameraServer.getInstance().startAutomaticCapture(_port == 0 ? 1 : 0);
            camera1.setResolution(xres, yres);
            camera2.setResolution(xres, yres);
        }

    }

    /**
     * A Camera subsystem capable of controlling 2 cameras
     * 
     * @param _cameraCount - The amount of cameras desired (1-2)
     * @param _port        - The desired starting port for camera 1
     */
    public MultiCameraSubsystem(int _cameraCount, int _port) {
        this(_cameraCount, _port, RobotMap.DefaultCamera_XR, RobotMap.DefaultCamera_XR, RobotMap.DefaultCamera_FPS);
    }

}
