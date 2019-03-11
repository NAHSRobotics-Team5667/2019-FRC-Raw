/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * A Multi-Camera Subsystem controller
 */
public class MultiCameraSubsystem extends Subsystem {

    UsbCamera camera1;
    UsbCamera camera2;
    VideoSink server;

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
     */
    public MultiCameraSubsystem(int _cameraCount, int _port, int xres, int yres, int fps) {

        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera2 = CameraServer.getInstance().startAutomaticCapture(1);

        camera1.setResolution(xres, yres);
        camera2.setResolution(xres, yres);
    }

    public MultiCameraSubsystem(int _cameraCount, int _port) {
        this(_cameraCount, _port, RobotMap.DefaultCamera_XR, RobotMap.DefaultCamera_XR, RobotMap.DefaultCamera_FPS);
    }

}
