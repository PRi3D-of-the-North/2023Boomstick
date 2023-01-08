package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainTeleOp extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final DoubleSupplier mTranslationXSupplier;
  private final DoubleSupplier mTranslationYSupplier;
  private final DoubleSupplier mRotationSupplier;

  public DrivetrainTeleOp(Drivetrain drivetrainSubsystem, DoubleSupplier translationXSupplier,
      DoubleSupplier translationYSupplier, DoubleSupplier rotationSupplier) {
    mDrivetrain = drivetrainSubsystem;
    mTranslationXSupplier = translationXSupplier;
    mTranslationYSupplier = translationYSupplier;
    mRotationSupplier = rotationSupplier;

    addRequirements(drivetrainSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mDrivetrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(
        mTranslationXSupplier.getAsDouble(),
        mTranslationYSupplier.getAsDouble(),
        mRotationSupplier.getAsDouble(),
        mDrivetrain.getGyroscopeRotation()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mDrivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
