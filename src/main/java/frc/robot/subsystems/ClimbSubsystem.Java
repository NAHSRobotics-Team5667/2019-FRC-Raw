/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.UltrasonicCommand;


/**
 * Add your docs here.
 */
public class ClimbSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Ultrasonic sensor;
	Solenoid climb;

	private final boolean EXTENDED = true;
	private final boolean RETRACTED = false;
	private boolean ostate = false;
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ClimbCommand());
	}

	public ClimbSubsystem(Solenoid climb, Ultrasonic sensor, Talon motor) {
		 this.sensor = sensor;
		 this.climb = climb;
		 this.motor = motor;
	}

	public double getRange() {
		return ultra.getRangeInches();
	}

	public void ClimbExtend() {
		 this.climb.set(EXTENDED);
		 ostate = EXTENDED;
	}
	public void ClimbStop() {
		this.climb.set(RETRACTED);
		ostaye = RETRACTED;
	}
	public void PlatformDrive() {
		this.motor.set(0.3);
		Thread.sleep(1500);
//1.5 seconds given for the robot to drive and get most of itself onto the platform
		this.motor.set(0)
	}

	public void displayValues() {
		SmartDashboard.putNumber("Elevation:", getRange();)
	}

}
