package frc.robot.commands;

import java.lang.Math;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Arm;
import frc.robot.Constants;

public class LegalArmMovement extends CommandBase {
  /** Creates a new LegalArmMovement. */
  private final Elevator mElevator;
  private final Arm mArm;
  private final CommandJoystick mJoystick;

  public LegalArmMovement(Elevator elevator, Arm arm, CommandJoystick joystick) {
    mElevator = elevator;
    mArm = arm;
    mJoystick = joystick;
    
    addRequirements(mElevator, mArm);
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

     angle += mJoystick.getRawAxis(1);
     if (mJoystick.button(1).getAsBoolean()) { // TRIGGER
      length += 0.2;
     }
     if (mJoystick.button(2).getAsBoolean()){ // THUMB
      length -= 0.2;
     }

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
      Max_Length = (61 / Math.abs(Math.cos((angle) * (Math.PI / 180)))) - Constants.DefaultMinLength;//make shure that this is degrees and not radians
      Max_Length2 = (59/ Math.abs(Math.sin((angle) * (Math.PI / 180)))) - Constants.DefaultMinLength;

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
