/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.commands.DriveTrainCommand;
import frc.robot.factories.MecanumDriveFactory;

public class DriveTrainSubsystem extends Subsystem {

    // Declare our motors
    private Spark r1;
    private Spark r2;
    private Spark l1;
    private Spark l2;
    private MecanumDrive mecDrive;

    /**
     * Constructor for DriveTrainSubsystem. Handles our drive train
     * 
     * @param r1 Dependency injection for future mocking in JUnit tests
     * @param r2 Dependency injection for future mocking in JUnit tests
     * @param l1 Dependency injection for future mocking in JUnit tests
     * @param l2 Dependency injection for future mocking in JUnit tests
     */
    public DriveTrainSubsystem(Spark r1, Spark r2, Spark l1, Spark l2, MecanumDriveFactory factory) {
        this.r1 = r1;
        this.r2 = r2;
        this.l1 = l1;
        this.l2 = l2;
        mecDrive = factory.createMecanumDrive(this.r1, this.r2, this.l1, this.l2);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveTrainCommand());
    }

    /**
     * Drive robot based on controller sticks
     * 
     * @param LSX Controller Left Stick - X value
     * @param LSY Controller Left Stick - Y value
     * @param RSX Controller Right Stick - X value
     */
    public void drive(double LSX, double LSY, double RSX) {
        mecDrive.driveCartesian(LSX, LSY, RSX);
    }

    /**
     * Polar Drive robot based on controller sticks
     * 
     * @param LSX Controller Left Stick - X value
     * @param LSY Controller Left Stick - Y value
     * @param RSX Controller Right Stick - X value
     */
    public void driveAuto(double LSX, double LSY, double RSX) {
        if (LSY > 0) {
            mecDrive.drivePolar(Math.sqrt((LSY * LSY) + (LSX * LSX)), Math.atan(LSX / LSY), RSX);
        } else if (LSY < 0) {
            mecDrive.drivePolar(Math.sqrt((LSY * LSY) + (LSX * LSX)), Math.atan(LSX / LSY) - 180 * Math.signum(LSX),
                    RSX);
        } else {
            mecDrive.drivePolar(Math.sqrt((LSY * LSY) + (LSX * LSX)), (Math.signum(LSX) * 90) - 90, RSX);
        }
    }
    // angle stuff needs to be fixed--FIRST's convention is that 0 degrees is j, 90
    // is i etc.

    /**
     * Stop the robot
     */
    public void stop() {
        mecDrive.stopMotor();
    }

    /**
     * 
     * @return Right front motor, used for testing
     * @deprecated Mocking does not affect the motor speed and may not be used at
     *             all
     */
    public double getR1Speed() {
        return this.r1.getSpeed();
    }

}
