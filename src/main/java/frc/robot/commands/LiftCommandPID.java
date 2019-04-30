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
import frc.robot.PIDControllers.PIDController;

public class LiftCommandPID extends Command {
	private boolean isGoingUp = false;
	private int target;
	private PIDController liftPID = new PIDController("l", 0, 0, 0);

	public LiftCommandPID() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.LiftPID);
		liftPID.setOutputRange(-0.7, 1);
		liftPID.setInputRange(0, 910000);
		liftPID.setTolerance(0);
		liftPID.setSetpoint(0);

		liftPID.outputTelemetry();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		if (Robot.m_oi.getController().getXButtonPressed()) {
			Robot.LiftPID.resetEncoder();
		}

		if (Robot.m_oi.getController().getRightTrigger() != 0) {
			Robot.LiftPID.setAuto(false);
			Robot.LiftPID.slide(1);
			isGoingUp = true;

		} else if (Robot.m_oi.getController().getLeftTrigger() != 0) {
			Robot.LiftPID.setAuto(false);
			Robot.LiftPID.slide(-.7);
			isGoingUp = false;

		} else if (Robot.m_oi.getController().getRightBumper() && !Robot.LiftPID.isAuto()) {
			Robot.LiftPID.setAuto(true);
			isGoingUp = true;
			target = Robot.LiftPID.increaseLevel();

		} else if (Robot.m_oi.getController().getLeftBumper()) {
			Robot.LiftPID.setAuto(true);
			isGoingUp = false;
			target = Robot.LiftPID.decreaseLevel();

		} else {
			if (!Robot.LiftPID.isAuto())
				Robot.LiftPID.stop();
		}

		if (Robot.LiftPID.isAuto()) {
			liftPID.setTolerance(RobotMap.Levels[target]);
			double speed;
			if (isGoingUp && Robot.LiftPID.getRotations() < RobotMap.Levels[target]) {
				speed = -liftPID.calculate(Robot.LiftPID.getRotations());
				System.out.println("Should be going up: " + speed);
				Robot.LiftPID.slide(speed);
			} else if (!isGoingUp && Robot.LiftPID.getRotations() > RobotMap.Levels[target]) {
				speed = liftPID.calculate(Robot.LiftPID.getRotations());
				System.out.println("Should be going down: " + speed);
				Robot.LiftPID.slide(speed);
			}
			if (isGoingUp && Robot.LiftPID.getRotations() - RobotMap.Levels[target] >= 0) {
				Robot.LiftPID.stop();
				Robot.LiftPID.setAuto(false);
				System.out.println("END AUTO");
			} else if (!isGoingUp && Robot.LiftPID.getRotations() - RobotMap.Levels[target] <= 0) {
				Robot.LiftPID.stop();
				Robot.LiftPID.setAuto(false);
				System.out.println("END AUTO");

			}
		}

		SmartDashboard.putNumber("Target Level", target);
		Robot.LiftPID.outputDebug();
		Robot.LiftPID.outputTelemetry();
		liftPID.readTelemetry();
		System.out.println(liftPID.getP() + ", " + liftPID.getI() + ", " + liftPID.getD());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.LiftPID.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
