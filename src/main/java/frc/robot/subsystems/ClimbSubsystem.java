// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved. */
// /* Open Source Software - may be modified and shared by FRC teams. The code
// */
// /* must be accompanied by the FIRST BSD license file in the root directory of
// */
// /* the project. */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.Solenoid;
// import edu.wpi.first.wpilibj.Talon;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.commands.ClimbCommand;

// /**
// * The climb subsystem
// */
// public class ClimbSubsystem extends Subsystem {
// // Put methods for controlling this subsystem
// // here. Call these from Commands.
// // The pistons for the climb
// private Solenoid clawSidePiston;
// private Solenoid panelSidePiston;
// // The climb motor
// private Talon motor;
// // Piston values
// private final boolean EXTENDED = true;
// private final boolean RETRACTED = false;
// // The states of the pistons for toggling
// private boolean frontCurrentState = RETRACTED;
// private boolean backCurrentState = RETRACTED;
// // The motor speed
// private final double speed = 1;

// @Override
// public void initDefaultCommand() {
// // Set the default command for a subsystem here.
// setDefaultCommand(new ClimbCommand());
// }

// /**
// * @param clawSidePiston - The front piston
// * @param panelSidePiston - The back piston
// * @param motor - The motor for the climbs
// */
// public ClimbSubsystem(Solenoid clawSidePiston, Solenoid panelSideSolenoid,
// Talon motor) {
// this.clawSidePiston = clawSidePiston;
// this.panelSidePiston = panelSideSolenoid;
// this.motor = motor;
// }

// /**
// * Close the pistons
// */
// public void closePistons() {
// this.clawSidePiston.set(false);
// this.panelSidePiston.set(backCurrentState);

// frontCurrentState = RETRACTED;
// backCurrentState = RETRACTED;
// }

// /**
// * Open the pistons
// */
// public void openPistons() {
// this.clawSidePiston.set(true);
// this.panelSidePiston.set(true);

// frontCurrentState = EXTENDED;
// backCurrentState = EXTENDED;
// }

// /**
// * Close only the front pistons
// */
// public void closeFront() {
// this.clawSidePiston.set(false);
// frontCurrentState = RETRACTED;
// }

// /**
// * Close only the back pistons
// */
// public void closeBack() {
// this.panelSidePiston.set(false);
// backCurrentState = RETRACTED;
// }

// /**
// * Open only the front pistons
// */
// public void openFront() {
// this.clawSidePiston.set(true);
// frontCurrentState = EXTENDED;
// }

// /**
// * Open the back pistonss
// */
// public void openBack() {
// this.panelSidePiston.set(true);
// backCurrentState = EXTENDED;
// }

// /**
// * Toggle the front pistons
// */
// public void toggleFront() {
// frontCurrentState = !frontCurrentState;
// clawSidePiston.set(frontCurrentState);
// }

// /**
// * Toggle the back pistonss
// */
// public void toggleBack() {
// backCurrentState = !backCurrentState;
// panelSidePiston.set(backCurrentState);
// }

// /**
// * Drive the motor foward
// */
// public void driveMotorFoward() {
// motor.set(speed);
// }

// /**
// * Drive the motor backward
// */
// public void driveMotorBackward() {
// motor.set(-speed);
// }

// /**
// * Stop the motor
// */
// public void stopMotor() {
// motor.stopMotor();
// }

// /**
// * Output telemetry based on the pistons current state
// */
// public void outputTelemetry() {
// SmartDashboard.putBoolean("C_ClawSide", clawSidePiston.get());
// SmartDashboard.putBoolean("C_PanelSide", panelSidePiston.get());
// }

// }
