/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ClimbCommand;

/**
 * The climb subsystem
 */
public class ClimbSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// The pistons for the climb
	private Solenoid frontPiston;
	private Solenoid backPiston;
	// The climb motor
	private Talon motor;
	// Piston values
	private final boolean EXTENDED = true;
	private final boolean RETRACTED = false;
	// The states of the pistons for toggling
	private boolean frontCurrentState = RETRACTED;
	private boolean backCurrentState = RETRACTED;
	// The motor speed
	private final double speed = .3;

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ClimbCommand());
	}

	/**
	 * @param frontPiston - The front piston
	 * @param backPiston  - The back piston
	 * @param motor       - The motor for the climbs
	 */
	public ClimbSubsystem(Solenoid frontPiston, Solenoid backPiston, Talon motor) {
		this.frontPiston = frontPiston;
		this.backPiston = backPiston;
		this.motor = motor;
	}

	/**
	 * Close the pistons
	 */
	public void closePistons() {
		this.frontPiston.set(RETRACTED);
		this.backPiston.set(RETRACTED);

		frontCurrentState = RETRACTED;
		backCurrentState = RETRACTED;
	}

	/**
	 * Open the pistons
	 */
	public void openPistons() {
		this.frontPiston.set(EXTENDED);
		this.backPiston.set(EXTENDED);

		frontCurrentState = EXTENDED;
		backCurrentState = EXTENDED;
	}

	/**
	 * Close only the front pistons
	 */
	public void closeFront() {
		this.frontPiston.set(RETRACTED);
		frontCurrentState = RETRACTED;
	}

	/**
	 * Close only the back pistons
	 */
	public void closeBack() {
		this.backPiston.set(RETRACTED);
		backCurrentState = RETRACTED;
	}

	/**
	 * Open only the front pistons
	 */
	public void openFront() {
		this.frontPiston.set(EXTENDED);
		frontCurrentState = EXTENDED;
	}

	/**
	 * Open the back pistonss
	 */
	public void openBack() {
		this.backPiston.set(EXTENDED);
		backCurrentState = EXTENDED;
	}

	/**
	 * Toggle the front pistons
	 */
	public void toggleFront() {
		frontCurrentState = !frontCurrentState;
		frontPiston.set(frontCurrentState);
	}

	/**
	 * Toggle the back pistonss
	 */
	public void toggleBack() {
		backCurrentState = !backCurrentState;
		backPiston.set(backCurrentState);
	}

	/**
	 * Drive the motor foward
	 */
	public void driveMotorFoward() {
		motor.set(speed);
	}

	/**
	 * Drive the motor backward
	 */
	public void driveMotorBackward() {
		motor.set(-speed);
	}

	/**
	 * Stop the motor
	 */
	public void stopMotor() {
		motor.stopMotor();
	}

	/**
	 * Output telemetry based on the pistons current state
	 */
	public void outputTelemetry() {
		SmartDashboard.putBoolean("front", frontPiston.get());
		SmartDashboard.putBoolean("back", backPiston.get());
	}

}
