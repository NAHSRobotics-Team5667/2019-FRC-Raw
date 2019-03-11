package frc.robot;

import java.util.Map;

import edu.wpi.first.wpilibj.XboxController;

public class Controller extends XboxController {

    Robot robot; // Reference to the robot

    private final double kGHOST = .1; // Threshold for blocking ghost signals

    /**
     * Initializes an Xbox controller.
     * 
     * @param port Controller port (default should be 0)
     */

    public Controller(int port) {
        super(port);
    }

    /**
     * Get the current joystick values in a Hashmap
     * 
     * @return Returns stick values - Used in DriveTrain when driving
     */
    public Map<String, Double> getSticks() {
        // Returns a map of the sticks accessible by their keys ie - get("LSX")
        return Map.of("LSX", this.getLeftX(), "LSY", this.getLeftY(), "RSX", this.getRightX(), "RSY", this.getRightY());
    }

    /**
     * Get the joystick's right-x stick values
     * 
     * @return Returns stick value as long as it exceeds the kGhost value -
     *         otherwise we return 0
     */
    public double getRightX() {
        return (super.getRawAxis(RobotMap.sRightX_Port) > kGHOST || super.getRawAxis(RobotMap.sRightX_Port) < -kGHOST)
                ? super.getRawAxis(RobotMap.sRightX_Port)
                : 0;
    }

    /**
     * Get the joystick's right-y stick values
     * 
     * @return Returns stick value as long as it exceeds the kGhost value -
     *         otherwise we return 0
     */
    public double getRightY() {
        return (-super.getRawAxis(RobotMap.sRightY_Port) > kGHOST || -super.getRawAxis(RobotMap.sRightY_Port) < -kGHOST)
                ? -super.getRawAxis(RobotMap.sRightY_Port)
                : 0;
    }

    /**
     * Get the joystick's left-x stick values
     * 
     * @return Returns stick value as long as it exceeds the kGhost value -
     *         otherwise we return 0
     */
    public double getLeftX() {
        return (super.getRawAxis(RobotMap.sLeftX_Port) > kGHOST || super.getRawAxis(RobotMap.sLeftX_Port) < -kGHOST)
                ? super.getRawAxis(RobotMap.sLeftX_Port)
                : 0;
    }

    /**
     * Get the joystick's left-y stick values
     * 
     * @return Returns stick value as long as it exceeds the kGhost value -
     *         otherwise we return 0
     */
    public double getLeftY() {
        return (-super.getRawAxis(RobotMap.sLeftY_Port) > kGHOST || -super.getRawAxis(RobotMap.sLeftY_Port) < -kGHOST)
                ? -super.getRawAxis(RobotMap.sLeftY_Port)
                : 0;
    }

    /**
     * Get the controller's dpad value
     * 
     * @return Returns the Joysticks DPad values
     */
    public int getDPad() {
        return super.getPOV();
    }

    /**
     * Get the Xbox Controller's Right trigger
     * 
     * @return The controller's right trigger value
     */
    public double getRightTrigger() {
        return super.getTriggerAxis(Hand.kRight);
    }

    /**
     * Get the Xbox Controller's Left trigger
     * 
     * @return The controller's left trigger value
     */
    public double getLeftTrigger() {
        return super.getTriggerAxis(Hand.kLeft);
    }

    /**
     * Get the Xbox Controller right hand (right side)
     * 
     * @return The Right side of the controller
     */
    public Hand getRightHand() {
        return Hand.kRight;
    }

    /**
     * Get the Xbox Controller left hand (left side)
     * 
     * @return The left side of the controller
     */
    public Hand getLeftHand() {
        return Hand.kLeft;
    }

    public boolean getRightBumper() {
        return super.getBumper(getRightHand());
    }

    public boolean getLeftBumper() {
        return super.getBumper(getLeftHand());
    }

}