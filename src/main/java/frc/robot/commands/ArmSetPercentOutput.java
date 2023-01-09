package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmSetPercentOutput extends CommandBase {
  private final Arm mArm;
  private double mOutput;

  /** Creates a new ArmSetPercentOutput. */
  public ArmSetPercentOutput(Arm arm, double output) {
    mArm = arm;
    mOutput = output;

    addRequirements(mArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double targetOutput = mOutput;
    
    if (mArm.getRearLimit() && targetOutput < 0.0) {
      targetOutput = 0.0;
    }

    if (mArm.getForwardLimit() && targetOutput > 0.0) {
      targetOutput = 0.0;
    }

    mArm.setPercentOutput(targetOutput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
