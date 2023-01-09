package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {
  private final DoubleSolenoid mPiston = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLAW_FORWARD, Constants.CLAW_REVERSE);

  public Claw() {}

  @Override
  public void periodic() {
  }

  public void setPistonState(boolean grab) {
    if (grab) {
      mPiston.set(Value.kReverse);
    } else {
      mPiston.set(Value.kForward);
    }
  }
}
