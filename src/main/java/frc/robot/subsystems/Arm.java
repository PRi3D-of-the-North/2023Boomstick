package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
    private final int CURRENT_LIMIT = 30; 
    private final CANSparkMax mMotor = new CANSparkMax(Constants.ARM, CANSparkMax.MotorType.kBrushless);

    private final DigitalInput mRearLimit = new DigitalInput(Constants.ARM_REAR_LIMIT);
    private final DigitalInput mForwardLimit = new DigitalInput(Constants.ARM_FORWARD_LIMIT);
    
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Arm Rear Limit", getRearLimit());
        SmartDashboard.putBoolean("Arm Forward Limit", getForwardLimit());
    }
    
    public Arm(){
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

    public boolean getRearLimit() {
        return !mRearLimit.get();
    }

    public boolean getForwardLimit() {
        return !mForwardLimit.get();
    }
}

