/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.PIDControllers.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * LimeLight command
 * 
 * Template from:
 * https://bitbucket.org/4085technicaldifficulties/build-season/src/d19340e662db82479fab48541813011542d01380/BuildSeasonRobot/src/main/java/frc/robot/Robot.java?at=master&fileviewer=file-view-default
 * 
 */
public class LimeLightCommand extends Command {

	PIDController distanceController = new PIDController(0.1);
	PIDController strafingController = new PIDController(0.1, 0.001);
	PIDController rotationController = new PIDController(0.009);

	public LimeLightCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.LimeLight);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		distanceController.setOutputRange(-0.3, 0.3);
		strafingController.setOutputRange(-0.3, 0.3);
		rotationController.setOutputRange(-0.3, 0.3);

		distanceController.setInputRange(-20.5, 20.5);
		strafingController.setInputRange(-27, 27);
		rotationController.setInputRange(-90, 0);

		distanceController.setTolerance(0.3);
		strafingController.setTolerance(0.3);
		rotationController.setTolerance(2);

		distanceController.setSetpoint(0);
		strafingController.setSetpoint(0);
		rotationController.setSetpoint(0);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// Robot.LimeLight.outputTelemetry();

		if (Robot.m_oi.getController().getYButtonPressed()) {
			Robot.LimeLight.toggleLight();
		}

		if (Robot.m_oi.getController().getDPad() == 90) {
			Robot.LimeLight.isAutoAligning = true;
		}

		Robot.m_oi.getController().getSticks().forEach((sticks, value) -> {
			if (value > Robot.m_oi.getController().kGHOST || value < -Robot.m_oi.getController().kGHOST) {
				Robot.LimeLight.isAutoAligning = false;
				Robot.LimeLight.turnLightOff();
			}
		});

		if (Robot.LimeLight.isAutoAligning) {
			if (Robot.LimeLight.hasValidTarget()) {
				double[] yCorners = Robot.LimeLight.getYCorners();

				double leftCorner = yCorners[1];
				double rightCorner = yCorners[0];

				double strafingSpeed = -strafingController.calculate(Robot.LimeLight.getXAngle());
				double distanceSpeed = -distanceController.calculate(Robot.LimeLight.getYAngle());
				double rotationSpeed = rotationController.calculate(Robot.LimeLight.getSkew());

				if (leftCorner < rightCorner) {
					rotationSpeed *= -1;
				}

				// Strafe to the indicated position
				Robot.DriveTrain.drive(strafingSpeed, distanceSpeed, rotationSpeed);
			}
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.LimeLight.isAutoAligning = false;
		Robot.LimeLight.setLightState(1);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
