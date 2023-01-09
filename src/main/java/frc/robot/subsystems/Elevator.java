package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
    private final int CURRENT_LIMIT = 30; 
    private final CANSparkMax mMotor = new CANSparkMax(Constants.ELEVATOR, CANSparkMax.MotorType.kBrushless);

    private final DigitalInput mRetractLimit = new DigitalInput(Constants.ELEVATOR_RETRACT_LIMIT);
    private final DigitalInput mExtendLimit = new DigitalInput(Constants.ELEVATOR_EXTEND_LIMIT);

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Elevator Retract Limit", getRetractLimit());
        SmartDashboard.putBoolean("Elevator Extend Limit", getExtendLimit());
    }

    public Elevator(){
        mMotor.restoreFactoryDefaults();
        mMotor.setIdleMode(IdleMode.kBrake);
        mMotor.setSmartCurrentLimit(CURRENT_LIMIT);
        mMotor.setInverted(false);
        mMotor.burnFlash();
    }

    public void setPercentOutput(double output) {
        if (output > 1.0) {
            output = 1.0;
        } else if (output < -1.0) {
            output = -1.0;
        }

        mMotor.set(output);
    }

    public boolean getRetractLimit() {
        return !mRetractLimit.get();
    }

    public boolean getExtendLimit() {
        return !mExtendLimit.get();
    }
}

