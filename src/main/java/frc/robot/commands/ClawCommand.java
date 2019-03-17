/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClawCommand extends Command {
	public ClawCommand() {
		requires(Robot.Claw);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.Claw.closeClaw();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.m_oi.getController().getAButtonPressed()) {
			if (Robot.BallIntake.isOpen()) {
				Robot.BallIntake.closePiston();
			}
			Robot.Claw.toggleClaw();
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
		Robot.Claw.closeClaw();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
