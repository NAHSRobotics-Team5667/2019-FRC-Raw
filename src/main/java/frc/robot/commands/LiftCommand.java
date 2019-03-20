/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LiftCommand extends Command {
	private boolean isGoingUp = false;
	private int target;

	public LiftCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.Lift);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		if (Robot.m_oi.getController().getXButtonPressed()) {
			Robot.Lift.resetEncoder();
		}

		if (Robot.m_oi.getController().getRightTrigger() != 0) {
			if (Robot.Lift.getRotations() < Robot.Lift.getMaxHeight()) {
				Robot.Lift.setAuto(false);
				Robot.Lift.slideUp();
				isGoingUp = true;
			} else {
				Robot.Lift.stop();
			}

		} else if (Robot.m_oi.getController().getLeftTrigger() != 0) {
			Robot.Lift.setAuto(false);
			Robot.Lift.slideDown();
			isGoingUp = false;

		} else if (Robot.m_oi.getController().getRightBumper() && !Robot.Lift.isAuto()) {
			Robot.Lift.setAuto(true);
			isGoingUp = true;
			target = Robot.Lift.increaseLevel();
			System.out.println("AUTO UP");

		} else if (Robot.m_oi.getController().getLeftBumper() && !Robot.Lift.isAuto()) {
			Robot.Lift.setAuto(true);
			isGoingUp = false;
			target = Robot.Lift.decreaseLevel();
			System.out.println("AUTO DOWN");

		} else {
			if (!Robot.Lift.isAuto())
				Robot.Lift.stop();
		}

		if (Robot.Lift.isAuto()) {
			if (isGoingUp && Robot.Lift.getRotations() < RobotMap.Levels[target]
					&& Robot.Lift.getRotations() < Robot.Lift.getMaxHeight()) {
				Robot.Lift.slideUp();
			} else if (!isGoingUp && Robot.Lift.getRotations() > RobotMap.Levels[target]) {
				Robot.Lift.slideDown();
			}
			if (isGoingUp && Robot.Lift.getRotations() - RobotMap.Levels[target] >= 0) {
				Robot.Lift.stop();
				Robot.Lift.setAuto(false);
			} else if (!isGoingUp && Robot.Lift.getRotations() - RobotMap.Levels[target] <= 0) {
				Robot.Lift.stop();
				Robot.Lift.setAuto(false);
			}
		}
		SmartDashboard.putNumber("Robot Level", Robot.Lift.getLevel());
		SmartDashboard.putNumber("Target Level", target);
		SmartDashboard.putNumber("Encoder Rotations", Robot.Lift.getRotations());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.Lift.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
