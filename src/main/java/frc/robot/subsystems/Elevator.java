package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
    private final double TOTAL_GEAR_RATIO = 21.0;
    private final double PULLEY_CIRCUM = Math.PI * 0.75;
    private final int CURRENT_LIMIT = 30; 
    private final CANSparkMax mMotor = new CANSparkMax(Constants.ELEVATOR, CANSparkMax.MotorType.kBrushless);

    private final DigitalInput mRetractLimit = new DigitalInput(Constants.ELEVATOR_RETRACT_LIMIT);
    private final DigitalInput mExtendLimit = new DigitalInput(Constants.ELEVATOR_EXTEND_LIMIT);

    RelativeEncoder encoder = mMotor.getEncoder();
    SparkMaxPIDController pid = mMotor.getPIDController();

    private static final double P = 0.8, I = 0, D = 0;

    double Length;

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Elevator Retract Limit", getRetractLimit());
        SmartDashboard.putBoolean("Elevator Extend Limit", getExtendLimit());
        SmartDashboard.putNumber("Code Length:", this.Length);
        SmartDashboard.putNumber("PID Length:", encoder.getPosition());
    }

    public Elevator(){
        mMotor.restoreFactoryDefaults();
        mMotor.setIdleMode(IdleMode.kBrake);
        mMotor.setSmartCurrentLimit(CURRENT_LIMIT);
        mMotor.setInverted(false);
        mMotor.burnFlash();
        pid.setP(P);
        pid.setP(I);
        pid.setP(D);
        encoder.setPositionConversionFactor((TOTAL_GEAR_RATIO * Constants.MAX_COUNTS_PER_REV) * (1 / PULLEY_CIRCUM));
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

    public double GetLength(){
        return this.Length;
    }

    public void HardSetPosition(double length){
        this.Length = length;

        pid.setReference(Length, CANSparkMax.ControlType.kPosition);
    }

    public void setEncoderValueInInches(double positionInInches) {
        int positionInMAXUnits = (int) ((TOTAL_GEAR_RATIO * Constants.MAX_COUNTS_PER_REV) * (positionInInches / PULLEY_CIRCUM));
        mMotor.getEncoder().setPosition(positionInMAXUnits);
    }
}

