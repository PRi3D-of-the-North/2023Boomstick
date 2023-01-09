package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ArmPreset;
import frc.robot.commands.ArmSetPercentOutput;
import frc.robot.commands.ClawSetState;
import frc.robot.commands.DrivetrainTeleOp;
import frc.robot.commands.ElevatorSetPercentOutput;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.LegalArmMovement;

public class RobotContainer {
	private final CommandXboxController mXbox = new CommandXboxController(0);
	private final CommandJoystick mJoystick = new CommandJoystick(1);

	private final Arm mArm = new Arm();
	private final Claw mClaw = new Claw();
	private final Drivetrain mDrivetrain = new Drivetrain();
	private final Elevator mElevator = new Elevator();
	private final LEDs mLEDs = new LEDs();

	public RobotContainer() {
		mArm.setDefaultCommand(new LegalArmMovement(mElevator, mArm, mJoystick));
		mClaw.setDefaultCommand(new ClawSetState(mClaw, true));
		mDrivetrain.setDefaultCommand(new DrivetrainTeleOp(
				mDrivetrain,
				() -> -modifyAxis(mXbox.getLeftY()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
				() -> -modifyAxis(mXbox.getLeftX()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
				() -> -modifyAxis(mXbox.getRightX()) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND));
		mElevator.setDefaultCommand(new ElevatorSetPercentOutput(mElevator, 0.0));

		configureBindings();
	}

	private void configureBindings() {
		mXbox.back().onTrue(mDrivetrain.zeroGyroscopeCommand());
		
		// mJoystick.button(3).whileTrue(new ArmSetPercentOutput(mArm, -0.5));
		// mJoystick.button(4).whileTrue(new ArmSetPercentOutput(mArm, 0.5));
		mJoystick.button(3).onTrue(new ClawSetState(mClaw, false));
		mJoystick.button(4).onTrue(new ClawSetState(mClaw, true));
		mJoystick.button(5).whileTrue(new ElevatorSetPercentOutput(mElevator, -0.5));
		mJoystick.button(6).whileTrue(new ElevatorSetPercentOutput(mElevator, 0.5));
		mJoystick.button(7).onTrue(new ArmPreset(mElevator, mArm, 7));
		mJoystick.button(9).onTrue(new ArmPreset(mElevator, mArm, 9));
		mJoystick.button(11).onTrue(new ArmPreset(mElevator, mArm, 11));
	}

	public Command getAutonomousCommand() {
		return null;
	}

	private static double deadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}

	private static double modifyAxis(double value) {
		// Deadband
		value = deadband(value, 0.05);
		// Square the axis
		value = Math.copySign(value * value, value);

		return value;
	}

	public void zeroGyro() {
		mDrivetrain.zeroGyroscope();
	}

	public Arm getArm() {
		return mArm;
	}

	public Elevator getElevator() {
		return mElevator;
	}
}