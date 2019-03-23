/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbCommand extends Command {
	public ClimbCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.Climb);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.Climb.closePistons();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// Toggle the front pistons
		if (Robot.m_oi.getController().getStartButtonPressed()) {
			Robot.Climb.toggleFront();
		}

		// Toggle the back pistons
		if (Robot.m_oi.getController().getBackButtonPressed()) {
			Robot.Climb.toggleBack();
		}

		// Open both pistons
		if (Robot.m_oi.getController().getDPad() == 270) {
			Robot.Climb.openPistons();
		}

		// Drive the robot towards the hab (climb)
		if (Robot.m_oi.getController().getDPad() == 0) {
			Robot.Climb.driveMotorFoward();
		} else {
			Robot.Climb.stopMotor();
		}
		// Drive the motor away from the hab (stop climb)
		if (Robot.m_oi.getController().getDPad() == 180) {
			Robot.Climb.driveMotorBackward();
		} else {
			Robot.Climb.stopMotor();
		}

		Robot.Climb.outputTelemetry();

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.Climb.closePistons();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
