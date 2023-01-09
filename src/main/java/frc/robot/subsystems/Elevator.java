package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
    private final double TOTAL_GEAR_RATIO = 21.0;
    private final double PULLEY_CIRCUM = Math.PI * 0.75;
    private final int CURRENT_LIMIT = 30; 
    private final CANSparkMax mMotor = new CANSparkMax(Constants.ELEVATOR, CANSparkMax.MotorType.kBrushless);

    public Elevator(){
        mMotor.restoreFactoryDefaults();
        mMotor.setIdleMode(IdleMode.kBrake);
        mMotor.setSmartCurrentLimit(CURRENT_LIMIT);
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

    public void setEncoderValueInInches(double positionInInches) {
        int positionInMAXUnits = (int) ((TOTAL_GEAR_RATIO * Constants.MAX_COUNTS_PER_REV) * (positionInInches / PULLEY_CIRCUM));
        mMotor.getEncoder().setPosition(positionInMAXUnits);
    }
}

