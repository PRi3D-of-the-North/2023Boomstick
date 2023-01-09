// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.LEDs;

public class LEDChange extends CommandBase {
  private final LEDs mLEDs;
  private boolean mBUTTON9, mBUTTON10, mBUTTON8;
  private double mTHROTTLE;
  /** Creates a new LEDChange. */
  public LEDChange(LEDs leds, boolean BUTTON8, boolean BUTTON10, boolean BUTTON9, double THROTTLE) {
    mLEDs = leds;
    mBUTTON10 = BUTTON10;
    mBUTTON9 = BUTTON9;
    mBUTTON8 = BUTTON8;
    mTHROTTLE = THROTTLE;
    addRequirements(mLEDs);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int rChange = 0; int gChange = 0; int bChange = 0;

    if (mBUTTON8){
      rChange = (int)(20 * mTHROTTLE);
    }
    if (mBUTTON10){
      gChange = (int)(20 * mTHROTTLE);
    }
    if (mBUTTON9){
      bChange = (int)(20 * mTHROTTLE);
    }
    mLEDs.ChangeLEDS(rChange , gChange, bChange);
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
