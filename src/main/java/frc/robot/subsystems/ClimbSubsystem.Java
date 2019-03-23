/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbCommand;

/**
 * Add your docs here.
 */
public class ClimbSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Solenoid frontPiston;
	private Solenoid backPiston;
	private Talon motor;

	private final boolean EXTENDED = true;
	private final boolean RETRACTED = false;

	private boolean frontCurrentState = RETRACTED;
	private boolean backCurrentState = RETRACTED;

	private final double speed = .3;

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ClimbCommand());
	}

	public ClimbSubsystem(Solenoid frontPiston, Solenoid backPiston, Talon motor) {
		this.frontPiston = frontPiston;
		this.backPiston = backPiston;
		this.motor = motor;
	}

	public void closePistons() {
		this.frontPiston.set(RETRACTED);
		this.backPiston.set(RETRACTED);

		frontCurrentState = RETRACTED;
		backCurrentState = RETRACTED;
	}

	public void openPistons() {
		this.frontPiston.set(EXTENDED);
		this.backPiston.set(EXTENDED);

		frontCurrentState = EXTENDED;
		backCurrentState = EXTENDED;
	}

	public void closeFront() {
		this.frontPiston.set(RETRACTED);
		frontCurrentState = RETRACTED;
	}

	public void closeBack() {
		this.backPiston.set(RETRACTED);
		backCurrentState = RETRACTED;
	}

	public void openFront() {
		this.frontPiston.set(EXTENDED);
		frontCurrentState = EXTENDED;
	}

	public void openBack() {
		this.backPiston.set(EXTENDED);
		backCurrentState = EXTENDED;
	}

	public void toggleFront() {
		frontCurrentState = !frontCurrentState;
		frontPiston.set(frontCurrentState);
	}

	public void toggleBack() {
		backCurrentState = !backCurrentState;
		backPiston.set(backCurrentState);
	}

	public void driveMotorFoward() {
		motor.set(speed);
	}

	public void driveMotorBackward() {
		motor.set(-speed);
	}

	public void stopMotor() {
		motor.stopMotor();
	}

	public void outputTelemetry() {
		SmartDashboard.putBoolean("front", frontCurrentState);
		SmartDashboard.putBoolean("back", backCurrentState);
	}

}
