package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class ElevatorSetPercentOutput extends CommandBase {
  private final Elevator mElevator;
  private double mOutput;
  
  /** Creates a new ArmSetPercentOutput. */
  public ElevatorSetPercentOutput(Elevator elevator, double output) {
    mElevator = elevator;
    mOutput = output;
    addRequirements(mElevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (mElevator.getRetractLimit() && mOutput < 0.0) {
      mOutput = 0.0;
    } else if (mElevator.getExtendLimit() && mOutput > 0.0) {
      mOutput = 0.0;
    }

    mElevator.setPercentOutput(mOutput);
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
