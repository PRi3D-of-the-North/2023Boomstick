// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.Math;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Arm;
import frc.robot.Constants;

public class LegalArmMovement extends CommandBase {
  /** Creates a new LegalArmMovement. */
  private final Elevator mElevator;
  private final Arm mArm;
  private double mAngleAxis;
  private boolean mTrigger;
  private boolean mThumb;
  public LegalArmMovement(Elevator elevator, Arm arm, double AngleAxis, boolean Trigger, boolean Thumb) {
    // Use addRequirements() here to declare subsystem dependencies.
    mElevator = elevator;
    mArm = arm;
    mAngleAxis = AngleAxis;
    mTrigger = Trigger;
    mThumb = Thumb;

    addRequirements(mElevator);
    addRequirements(mArm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double angle, length;
    length = mElevator.GetLength();
     angle = mArm.GetAngle();

      if (angle < Constants.MinAngle){
          angle = Constants.MinAngle;
      }
      if (angle > Constants.MaxAngle){
          angle = Constants.MaxAngle;
      }
      if (length < Constants.MinLength){
          length = Constants.MinLength;
      }
      if (length > Constants.MaxLength){
          length = Constants.MaxLength;
      }
      double Max_Length, Max_Length2;
      Max_Length = (61 / Math.abs(Math.cos(angle))) - Constants.DefaultMinLength;//make shure that this is degrees and not radians
      Max_Length2 = (59/ Math.abs(Math.sin(angle))) - Constants.DefaultMinLength;

      if (length > Max_Length || length > Max_Length2){
      
      if (Max_Length >= Max_Length2){
          mArm.HardSetPosition(angle);
          mElevator.HardSetPosition(Max_Length2);

      }else{
        mArm.HardSetPosition(angle);
        mElevator.HardSetPosition(Max_Length);
      }
      }
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
