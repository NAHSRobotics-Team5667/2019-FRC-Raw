/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.factories;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * MecanumDrive Factory Class - Used to allow mocking
 */
public class MecanumDriveFactory {

    /**
     * @return MecanumDrive - Returns a new MecanumDrive
     */
    public MecanumDrive createMecanumDrive(PWMTalonSRX r1, PWMTalonSRX r2, PWMTalonSRX l1, PWMTalonSRX l2) {
        return new MecanumDrive(l1, l2, r1, r2);
    }
}
