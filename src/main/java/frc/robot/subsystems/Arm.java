package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Arm extends SubsystemBase {
    private final double TOTAL_GEAR_RATIO = 183.33;
    private final int CURRENT_LIMIT = 30; 
    private final CANSparkMax mMotor = new CANSparkMax(Constants.ARM, CANSparkMax.MotorType.kBrushless);

    private final RelativeEncoder encoder = mMotor.getEncoder();
    private final SparkMaxPIDController pid = mMotor.getPIDController();

    private final DigitalInput mRearLimit = new DigitalInput(Constants.ARM_REAR_LIMIT);
    private final DigitalInput mForwardLimit = new DigitalInput(Constants.ARM_FORWARD_LIMIT);

    private static final double P = 0.8, I = 0, D = 0;

    double Angle;//needs default initial angle
    
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Arm Rear Limit", getRearLimit());
        SmartDashboard.putBoolean("Arm Forward Limit", getForwardLimit());
        SmartDashboard.putNumber("Code Angle:", this.Angle);
        SmartDashboard.putNumber("PID Angle:", encoder.getPosition());
    }
    
    public Arm(){
        mMotor.restoreFactoryDefaults();
        mMotor.setIdleMode(IdleMode.kBrake);
        mMotor.setSmartCurrentLimit(CURRENT_LIMIT);
        mMotor.setInverted(false);
        mMotor.burnFlash();
        pid.setP(P);
        pid.setP(I);
        pid.setP(D);
        encoder.setPositionConversionFactor((TOTAL_GEAR_RATIO * Constants.MAX_COUNTS_PER_REV) * (1 / 360.0));
    }

    public void setPercentOutput(double output) {
        if (output > 1.0) {
            output = 1.0;
        } else if (output < -1.0) {
            output = -1.0;
        }

        mMotor.set(output);
    }

    public double[] MakeArmMovesLegal(double angle, double length){

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
      double Max_Length = 0, Max_Length2 = 0;
      Max_Length = (61 / Math.abs(Math.cos((angle) * (Math.PI / 180)))) - Constants.DefaultMinLength;//Converts to radians
      Max_Length2 = (59/ Math.abs(Math.sin((angle) * (Math.PI / 180)))) - Constants.DefaultMinLength;

      double[] Returned = new double[2];
      if (length > Max_Length || length > Max_Length2){
        
        Returned[0] = angle;
      if (Max_Length >= Max_Length2){
        Returned[1] = Max_Length2;

      }else{
        Returned[1] = Max_Length;
      }
      }
      return Returned;
    }

    public double GetAngle(){
        return this.Angle;
    }

    public void SetAngle(double angle){

    }

    public boolean getRearLimit() {
        return !mRearLimit.get();
    }

    public boolean getForwardLimit() {
        return !mForwardLimit.get();
    }

    public void HardSetPosition(double angle){
        this.Angle = angle;

        pid.setReference(Angle, CANSparkMax.ControlType.kPosition);
    }

    public void setEncoderValueInDegrees(double positionInDegrees) {
        int positionInMAXUnits = (int) ((TOTAL_GEAR_RATIO * Constants.MAX_COUNTS_PER_REV) * (positionInDegrees / 360.0));
        mMotor.getEncoder().setPosition(positionInMAXUnits);
    }
}

