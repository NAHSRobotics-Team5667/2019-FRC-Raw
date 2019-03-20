/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ClawCommand;

/**
 * The claw subsystem for the robot
 */
public class ClawSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	Solenoid claw;

	private final boolean OPEN = true;
	private final boolean CLOSED = !OPEN;
	private boolean currentState = CLOSED;

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ClawCommand());
	}

	/**
	 * The robot claw
	 * 
	 * @param claw - Solenoid
	 */
	public ClawSubsystem(Solenoid claw) {
		this.claw = claw;
	}

	/**
	 * Open the robot claw
	 */
	public void openClaw() {
		this.claw.set(OPEN);
	}

	/**
	 * Close the robot claw
	 */
	public void closeClaw() {
		this.claw.set(CLOSED);
	}

	/**
	 * Toggle the robot claw based on the last state
	 */
	public void toggleClaw() {
		this.currentState = !currentState;
		this.claw.set(currentState);
	}
<<<<<<< HEAD
=======

	public boolean isOpen() {
		return this.claw.get() == OPEN;
	}
>>>>>>> f0aba9e0c0b80d0dd681e8c8bc763f50e8359e5e
}
