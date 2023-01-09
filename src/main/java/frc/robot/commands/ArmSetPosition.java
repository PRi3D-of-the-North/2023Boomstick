package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class ArmSetPosition extends CommandBase {
  private final Arm mArm;
  private double mAngle;

  /** Creates a new ArmSetPercentOutput. */
  public ArmSetPosition(Arm arm, double angle) {
    mArm = arm;
    mAngle = angle;

    addRequirements(mArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double targetPosition = mAngle;
    SmartDashboard.putNumber("Target Arm Pos", targetPosition);
    SmartDashboard.putNumber("Actual Position", mArm.getEncoderDegrees());

      if (mArm.getRearLimit() && mArm.getVelocity() < -Constants.EPSILON) {
        mArm.setPercentOutput(0.0);
      } else if (mArm.getForwardLimit() && mArm.getVelocity() > Constants.EPSILON) {
        mArm.setPercentOutput(0.0);
      } else {
        mArm.setAngleInDegrees(targetPosition);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
