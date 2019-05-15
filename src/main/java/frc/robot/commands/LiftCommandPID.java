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

public class LiftCommandPID extends Command {
	private boolean isGoingUp = false;
	private int target;

	private double kP = 3.5;
	private double kF = .1;
	private double kFDown = .02;

	public LiftCommandPID() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.Lift);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		SmartDashboard.putNumber("kP", kP);
		SmartDashboard.putNumber("kF", kF);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		if (Robot.m_oi.getController().getXButtonPressed()) {
			Robot.Lift.resetEncoder();
		}

		if (Robot.m_oi.getController().getRightTrigger() != 0) {
			Robot.Lift.setAuto(false);
			Robot.Lift.slide(1);
			isGoingUp = true;

		} else if (Robot.m_oi.getController().getLeftTrigger() != 0) {
			Robot.Lift.setAuto(false);
			Robot.Lift.slide(-.7);
			isGoingUp = false;

		} else if (Robot.m_oi.getController().getRightBumper()) {
			Robot.Lift.setAuto(true);
			isGoingUp = true;
			target = Robot.Lift.increaseLevel();

		} else if (Robot.m_oi.getController().getLeftBumper()) {
			Robot.Lift.setAuto(true);
			isGoingUp = false;
			target = Robot.Lift.decreaseLevel();

		} else {
			if (!Robot.Lift.isAuto()) {
				Robot.Lift.stop();
			}
		}

		if (Robot.Lift.isAuto()) {
			double speed = (isGoingUp ? kF : -kFDown) + ((RobotMap.Levels[target] - Robot.Lift.getHeight()) * kP);
			Robot.Lift.slide(speed);
			// if (isGoingUp && Robot.Lift.getHeight() < RobotMap.Levels[target]) {
			// speed = (RobotMap.Levels[target]);
			// System.out.println("Should be going up: " + speed);
			// Robot.Lift.slide(speed);

			// } else if (!isGoingUp && Robot.Lift.getHeight() > RobotMap.Levels[target]) {
			// speed = 0;
			// System.out.println("Should be going down: " + speed);
			// Robot.Lift.slide(speed);
			// }

			// if (isGoingUp && Robot.Lift.getHeight() - RobotMap.Levels[target] >= 0) {
			// Robot.Lift.stop();
			// Robot.Lift.setAuto(false);
			// } else if (!isGoingUp && Robot.Lift.getHeight() - RobotMap.Levels[target] <=
			// 0) {
			// Robot.Lift.stop();
			// Robot.Lift.setAuto(false);

			// }
			SmartDashboard.putNumber("Slide Speed", speed);

		}

		SmartDashboard.putNumber("Target Level", target);
		kP = SmartDashboard.getNumber("kP", kP);
		SmartDashboard.getNumber("kF", kF);
		Robot.Lift.outputTelemetry();
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
