package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {
  private final DoubleSolenoid mPiston = new DoubleSolenoid(1, PneumaticsModuleType.REVPH, Constants.CLAW_FORWARD, Constants.CLAW_REVERSE);
  private final Compressor mCompressor = new Compressor(1, PneumaticsModuleType.REVPH);
  public Claw() {}

  @Override
  public void periodic() {
    mCompressor.enableDigital();
  }

  public void setPistonState(boolean grab) {
    if (grab) {
      mPiston.set(Value.kReverse);
    } else {
      mPiston.set(Value.kForward);
    }
  }
}
