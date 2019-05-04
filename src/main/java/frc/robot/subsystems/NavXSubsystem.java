/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class NavXSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private AHRS NavX;

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
	}

	public NavXSubsystem() {
		NavX = new AHRS(SerialPort.Port.kMXP);
	}

	public void resetNavX() {
		NavX.reset();
	}

	public double getGyro() {
		return NavX.getGyroFullScaleRangeDPS();
	}

	public double getAngle() {
		return NavX.getAngle();
	}

	public void displayValues() {
		SmartDashboard.putNumber("Gyro Angle:", getAngle());
	}

}
