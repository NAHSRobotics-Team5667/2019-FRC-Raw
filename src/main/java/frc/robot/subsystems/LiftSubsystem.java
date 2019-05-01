/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.LiftCommand;
import frc.robot.commands.LiftCommandPID;

/**
 * The lift subsystem
 */
public class LiftSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// Slide MotorControllers
	private PWMTalonSRX slide;
	// Slide encoder
	private Encoder slideEncoder;
	// Current level index
	private int currentLevel = 0;
	// Slide speed
	private final double slideSpeed = 1;
	private final double slideSpeedDown = -.7;
	// Are we in auto-lift?
	private boolean inAuto = false;
	// The linear slide anti-gravity value
	private final double ANTI_GRAV = .09;

	private final int MAX_HEIGHT_ENCODER = 110000000;

	private final DigitalInput limitSwitch;

	private double pulleyCircumference = .102;
	private double gearRatio = 64;
	private double pulsesPerRevolution = 1024;

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new LiftCommandPID());
	}

	/**
	 * A linear slide subsytem
	 * 
	 * @param LiftMotorLeft  The left motor for the slide
	 * @param LiftMotorRight The right motor for the slide
	 * @param motorEncoder   The motor encoder attached to one of the motors
	 */
	public LiftSubsystem(PWMTalonSRX LiftMotor, Encoder motorEncoder, DigitalInput limitSwitch) {
		this.slide = LiftMotor;
		this.slideEncoder = motorEncoder;
		this.slideEncoder.reset();
		this.slideEncoder.setReverseDirection(true);
		this.limitSwitch = limitSwitch;
	}

	/**
	 * Move the linear slide up
	 */
	public void slideUp() {
		this.slide.set(slideSpeed);
	}

	/**
	 * Move the linear slide down
	 */
	public void slideDown() {
		this.slide.set(slideSpeedDown);
	}

	/**
	 * Move the linear slide based on the speed passed in
	 * 
	 * @param speed - The speed
	 */

	public void slide(double speed) {
		this.slide.set(speed);
	}

	/**
	 * Stop the linear slide
	 */
	public void stop() {
		this.slide.stopMotor();
	}

	/**
	 * The encoder raw rotations
	 * 
	 * @return The encoder raw rotations
	 */
	public int getRotations() {
		return this.slideEncoder.get();
	}

	/**
	 * Increase the level we should be on
	 * 
	 * @return The new level
	 */
	public int increaseLevel() {
		currentLevel = (currentLevel + 1 < RobotMap.Levels.length) ? currentLevel + 1 : RobotMap.Levels.length - 1;
		inAuto = true;
		return currentLevel;
	}

	/**
	 * Decrease the level we should be on
	 * 
	 * @return The new level
	 */
	public int decreaseLevel() {
		currentLevel = (currentLevel - 1 < 0) ? 0 : currentLevel - 1;
		inAuto = true;
		return currentLevel;
	}

	/**
	 * Get the current auto-lift state
	 * 
	 * @return Are we in auto? (True = yes)
	 */
	public boolean isAuto() {
		return inAuto;
	}

	/**
	 * Set the auto mode
	 * 
	 * @param val Boolean (True = yes)
	 */
	public void setAuto(boolean val) {
		this.inAuto = val;
	}

	/**
	 * Get the current level
	 * 
	 * @return The current level
	 */
	public int getLevel() {
		return currentLevel;
	}

	/**
	 * The linear slide anti-gravity shoutout to ajay who wrote this
	 */
	public void setAntiGrav(boolean val) {
		this.slide.set(val ? ANTI_GRAV : 0);
	}

	/**
	 * Get the max height
	 * 
	 * @return the max height
	 */
	public int getMaxHeight() {
		return MAX_HEIGHT_ENCODER;
	}

	/**
	 * Reset the encoder value
	 */
	public void resetEncoder() {
		this.slideEncoder.reset();
	}

	/**
	 * The switch value
	 */
	public boolean getSwitchValue() {
		return this.limitSwitch.get();
	}

	public double getHeight() {
		// currentEncoderPulses / (pulesesPerRevolution * gear ratio) *
		// circumferenceOfPulley
		return (this.getRotations() / (pulsesPerRevolution * gearRatio)) * pulleyCircumference;
	}

	/**
	 * Output information to the driver graphically
	 */
	public void outputTelemetry() {
		SmartDashboard.putNumber("Robot Level", getLevel());
		SmartDashboard.putNumber("Encoder Rotations", getRotations());
		SmartDashboard.putNumber("Lift height", getHeight());
	}
}
