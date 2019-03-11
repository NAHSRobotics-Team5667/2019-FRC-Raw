/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.LimeLightCommand;

/**
 * Add your docs here.
 */
public class LimeLightSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	// tv - Whether the limelight has any valid targets (0 or 1)
	private NetworkTableEntry tv = table.getEntry("tv");
	// tx - Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
	private NetworkTableEntry tx = table.getEntry("tx");
	// ty - Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
	private NetworkTableEntry ty = table.getEntry("ty");
	// ta - Target Area (0% of image to 100% of image)
	private NetworkTableEntry ta = table.getEntry("ta");

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new LimeLightCommand());
	}

	public double hasTarget() {
		return tv.getDouble(0.0);
	}

	public double getHorizontalOffset() {
		return tx.getDouble(0.0);
	}

	public double getVerticalOffset() {
		return ty.getDouble(0.0);

	}

	public double getArea() {
		return ta.getDouble(0.0);
	}

	public void displayValues() {
		SmartDashboard.putString("Limelight has Target", hasTarget() == 1 ? "Yes" : "No");
		SmartDashboard.putNumber("Horizontal Offset:", getHorizontalOffset());
		SmartDashboard.putNumber("Vertical Offset", getVerticalOffset());
		SmartDashboard.putNumber("Limelight Area:", getArea());
	}

}
