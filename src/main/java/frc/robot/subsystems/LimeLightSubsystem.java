/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.LimeLightCommand;

/**
 * The limelight subsystem
 * 
 * Template from:
 * https://bitbucket.org/4085technicaldifficulties/build-season.git
 */
public class LimeLightSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LimeLightCommand());
	}

	public enum LightMode {
		DEFAULT(0), OFF(1), BLINK(2), ON(3);

		private final int ledMode;

		private LightMode(int ledMode) {
			this.ledMode = ledMode;
		}

		public int getLedMode() {
			return ledMode;
		}
	}

	public enum StreamMode {
		STANDARD(0), MAIN(1), SECONDARY(2);

		private final int mode;

		private StreamMode(int mode) {
			this.mode = mode;
		}

		public int getMode() {
			return mode;
		}
	}

	private NetworkTable table;

	// Frequently used entries to store
	private NetworkTableEntry tx;
	private NetworkTableEntry ty;
	private NetworkTableEntry ta;

	public boolean isAutoAligning = false;

	public LimeLightSubsystem() {
		table = NetworkTableInstance.getDefault().getTable("limelight");

		tx = table.getEntry("tx");
		ty = table.getEntry("ty");
		ta = table.getEntry("ta");

	}

	// Whether camera has any valid targets
	public boolean hasValidTarget() {
		return (table.getEntry("tv").getDouble(0) == 0) ? false : true;
	}

	// Horizontal offset from crosshair to target
	// -27, 27 degrees
	public double getXAngle() {
		return tx.getDouble(0);
	}

	// Vertical offset from crosshair to target
	// -20.5, 20.5 degrees
	public double getYAngle() {
		return ty.getDouble(0);
	}

	public double getArea() {
		return ta.getDouble(0);
	}

	// -90 to 0 degrees. Rotation of the object
	public double getSkew() {
		return table.getEntry("ts").getDouble(0);
	}

	// Latency in ms of the pipeline
	public double getDeltaTime() {
		return table.getEntry("tl").getDouble(0);
	}

	// The length of the shortest side of the bounding box in pixels
	public double getShortLength() {
		return table.getEntry("tshort").getDouble(0);
	}

	// The length of the longest side of the bounding box in pixels
	public double getLongLength() {
		return table.getEntry("tlong").getDouble(0);
	}

	// The length of the horizontal side of the box (0-320 pixels)
	public double getHorizontalLength() {
		return table.getEntry("thor").getDouble(0);
	}

	// The length of the vertical side of the box (0-320 pixels)
	public double getVerticalLength() {
		return table.getEntry("tvert").getDouble(0);
	}

	// Returns the index of the current vision pipeline (0... 9)
	public int getPipeIndex() {
		return (int) table.getEntry("getpipe").getDouble(0);
	}

	public double[] getXCorners() {
		return table.getEntry("tcornx").getDoubleArray(new double[] { 0, 0, 0, 0 });
	}

	public double[] getYCorners() {
		return table.getEntry("tcorny").getDoubleArray(new double[] { 0, 0, 0, 0 });
	}

	// Sets the LEDs to either On, Off, Blinking, or determined by the pipeline
	public void setLightState(int mode) {
		table.getEntry("ledMode").setNumber(mode);
	}

	// True for human use, false for vision pipeline. Starts false
	// Enabling this makes the exposure something you can easily see out of and
	// disables vision processing
	public void setDriveMode(boolean b) {
		if (b) {
			table.getEntry("camMode").setNumber(1);
		} else {
			table.getEntry("camMode").setNumber(0);
		}
	}

	// Sets the limelights current pipeline
	public void setPipeline(int pipeline) {
		table.getEntry("pipeline").setNumber(pipeline);
	}

	// Sets the layout of the cameras viewed at 10.40.85.11:5800
	// Standard is side by side, Main is Limelight big with secondary camera in
	// bottom right, Secondary is vice versa
	public void setStreamMode(StreamMode streamMode) {
		table.getEntry("stream").setNumber(streamMode.getMode());
	}

	// Enables and disables the camera taking snapshots
	// While enabled the camera takes two snapshots evey second
	public void takeSnapshots(boolean b) {
		if (b) {
			table.getEntry("snapshot").setNumber(1);
		} else {
			table.getEntry("snapshot").setNumber(0);
		}
	}

	public void outputTelemetry() {
		SmartDashboard.putBoolean("HasTarget", hasValidTarget());
		SmartDashboard.putNumber("Horizontal Offset", getXAngle());
		SmartDashboard.putNumber("Vertical Offset", getYAngle());
		SmartDashboard.putNumber("Area", getArea());
		SmartDashboard.putNumber("Skew", getSkew());
		SmartDashboard.putString("XCorners", Arrays.toString(getXCorners()));
		SmartDashboard.putString("YCorners", Arrays.toString(getYCorners()));
	}

}
