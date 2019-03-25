/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.BallCommand;

/**
 * The ball subsystem
 */
public class BallSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// The cone piston
	private Solenoid piston;
	// Piston values
	private final boolean OPEN = true;
	private final boolean CLOSED = !OPEN;
	// The current state for auto togglings
	private boolean currentState = CLOSED;

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new BallCommand());
	}

	/**
	 * The robot piston
	 * 
	 * @param piston - Solenoid
	 */
	public BallSubsystem(Solenoid piston) {
		this.piston = piston;
	}

	/**
	 * Open the robot piston
	 */
	public void openPiston() {
		this.piston.set(OPEN);
	}

	/**
	 * Close the robot piston
	 */
	public void closePiston() {
		this.piston.set(CLOSED);
	}

	/**
	 * Toggle the robot piston based on the last state
	 */
	public void togglePiston() {
		this.currentState = !currentState;
		this.piston.set(currentState);
	}

	/**
	 * Check if the piston is currently opened
	 */
	public boolean isOpen() {
		return this.piston.get() == OPEN;
	}

	/**
	 * Allow the driver to see if the piston is open graphically
	 */
	public void outputTelemetry() {
		SmartDashboard.putBoolean("b", isOpen());
	}
}
